package com.seatsure.payment;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.seatsure.audit.AuditService;
import com.seatsure.booking.*;
import com.seatsure.common.exception.*;
import com.seatsure.common.util.SecurityUtils;
import com.seatsure.hold.HoldStatus;
import com.seatsure.hold.SeatHold;
import com.seatsure.hold.SeatHoldRepository;
import com.seatsure.outbox.OutboxEvent;
import com.seatsure.outbox.OutboxEventRepository;
import com.seatsure.payment.dto.PaymentRequest;
import com.seatsure.payment.dto.PaymentResponse;
import com.seatsure.payment.dto.WebhookPaymentRequest;
import com.seatsure.showtime.ShowSeat;
import com.seatsure.showtime.ShowSeatRepository;
import com.seatsure.showtime.ShowSeatStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import java.time.Instant;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@Service
public class PaymentService {
    private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(PaymentService.class);
    private final PaymentRepository paymentRepository;
    private final BookingRepository bookingRepository;
    private final BookingSeatRepository bookingSeatRepository;
    private final ShowSeatRepository showSeatRepository;
    private final SeatHoldRepository seatHoldRepository;
    private final SimulatedPaymentProvider paymentProvider;
    private final OutboxEventRepository outboxEventRepository;
    private final AuditService auditService;
    private final ObjectMapper objectMapper;

    public PaymentService(PaymentRepository paymentRepository, BookingRepository bookingRepository, BookingSeatRepository bookingSeatRepository, ShowSeatRepository showSeatRepository, SeatHoldRepository seatHoldRepository, SimulatedPaymentProvider paymentProvider, OutboxEventRepository outboxEventRepository, AuditService auditService, ObjectMapper objectMapper) {
        this.paymentRepository = paymentRepository;
        this.bookingRepository = bookingRepository;
        this.bookingSeatRepository = bookingSeatRepository;
        this.showSeatRepository = showSeatRepository;
        this.seatHoldRepository = seatHoldRepository;
        this.paymentProvider = paymentProvider;
        this.outboxEventRepository = outboxEventRepository;
        this.auditService = auditService;
        this.objectMapper = objectMapper;
    }

    /**
     * Processes payment for a booking idempotently.
     * Guarantees transactional consistency between Booking, ShowSeat inventory, and Payment.
     */
    @Transactional(isolation = Isolation.READ_COMMITTED)
    public PaymentResponse processPayment(Long bookingId, PaymentRequest request) {
        Long userId = SecurityUtils.getCurrentUserId();
        // 1. Idempotency Check: if request with this idempotency key was already processed, return existing result
        Optional<Payment> existingPayment = paymentRepository.findByIdempotencyKey(request.getIdempotencyKey());
        if (existingPayment.isPresent()) {
            Payment payment = existingPayment.get();
            log.info("Returning idempotent payment response for key: {}", request.getIdempotencyKey());
            return PaymentResponse.fromEntity(payment);
        }
        // 2. Lock and validate booking
        Booking booking = bookingRepository.findByIdForUpdate(bookingId).orElseThrow(() -> new ResourceNotFoundException("Booking", "id", bookingId));
        if (!booking.getUser().getId().equals(userId) && !SecurityUtils.isCurrentUserAdmin()) {
            throw new UnauthorizedAccessException("You are not authorized to pay for this booking");
        }
        // Validate booking state
        if (booking.getStatus() == BookingStatus.CONFIRMED) {
            throw new InvalidStateTransitionException("Booking is already confirmed");
        }
        if (booking.getStatus() == BookingStatus.CANCELLED) {
            throw new InvalidStateTransitionException("Cannot pay for a cancelled booking");
        }
        if (booking.getStatus() == BookingStatus.EXPIRED) {
            throw new HoldExpiredException("Booking has expired. Please re-select your seats.");
        }
        // 3. Lock held seats and check availability
        List<BookingSeat> bookingSeats = bookingSeatRepository.findByBookingIdWithDetails(bookingId);
        List<Long> showSeatIds = bookingSeats.stream().map(bs -> bs.getShowSeat().getId()).toList();
        List<ShowSeat> showSeats = showSeatRepository.findByIdInForUpdate(showSeatIds);
        // Check hold expiration race condition
        boolean anySeatUnavailable = showSeats.stream().anyMatch(ss -> ss.getStatus() == ShowSeatStatus.BOOKED);
        if (anySeatUnavailable || booking.isExpired()) {
            // Safe Compensation Policy: Seats were taken or hold expired
            log.warn("Payment attempt on expired or already booked seats for booking #{}", bookingId);
            booking.setStatus(BookingStatus.EXPIRED);
            bookingRepository.save(booking);
            Payment failedPayment = Payment.builder().booking(booking).paymentReference("PAY_FAIL_" + UUID.randomUUID().toString().replace("-", "").substring(0, 10)).idempotencyKey(request.getIdempotencyKey()).amount(booking.getTotalAmount()).status(PaymentStatus.FAILED).failureReason("HOLD_EXPIRED_SEATS_UNAVAILABLE").build();
            failedPayment = paymentRepository.save(failedPayment);
            throw new HoldExpiredException("Seat hold expired before payment could be finalized. Seats released.");
        }
        // 4. Create Initial Payment record
        String paymentRef = "PAY_" + UUID.randomUUID().toString().replace("-", "").substring(0, 12).toUpperCase();
        Payment payment = Payment.builder().booking(booking).paymentReference(paymentRef).idempotencyKey(request.getIdempotencyKey()).amount(booking.getTotalAmount()).currency("INR").status(PaymentStatus.INITIATED).build();
        payment = paymentRepository.save(payment);
        // 5. Invoke Simulated Payment Gateway
        SimulatedPaymentProvider.ProviderResult result = paymentProvider.processPayment(paymentRef, booking.getTotalAmount(), request.getSimulationOutcome());
        payment.setProviderReference(result.getProviderReference());
        // 6. Apply Outcome Safely within Transaction
        if (result.isSuccess()) {
            payment.setStatus(PaymentStatus.SUCCESS);
            booking.setStatus(BookingStatus.CONFIRMED);
            // Finalize seat inventory to BOOKED
            for (ShowSeat ss : showSeats) {
                ss.setStatus(ShowSeatStatus.BOOKED);
            }
            showSeatRepository.saveAll(showSeats);
            // Update associated SeatHold if exists
            if (!showSeats.isEmpty() && showSeats.get(0).getCurrentHoldId() != null) {
                seatHoldRepository.findByIdForUpdate(showSeats.get(0).getCurrentHoldId()).ifPresent(hold -> {
                    hold.setStatus(HoldStatus.CONFIRMED);
                    seatHoldRepository.save(hold);
                });
            }
            recordOutboxEvent("Payment", payment.getId().toString(), "PaymentSucceeded", Map.of("paymentId", payment.getId(), "paymentReference", payment.getPaymentReference(), "bookingId", booking.getId(), "bookingReference", booking.getBookingReference(), "amount", payment.getAmount(), "userId", userId));
            recordOutboxEvent("Booking", booking.getId().toString(), "BookingConfirmed", Map.of("bookingId", booking.getId(), "bookingReference", booking.getBookingReference(), "userId", userId, "showId", booking.getShow().getId(), "seatCount", showSeats.size(), "totalAmount", booking.getTotalAmount()));
            auditService.recordAsync(userId, "PAYMENT_SUCCESS", "Payment", payment.getId().toString(), "Payment confirmed for booking " + booking.getBookingReference(), null);
            log.info("Payment SUCCESS for booking #{}. Booking CONFIRMED", bookingId);
        } else if (result.isTimeout()) {
            payment.setStatus(PaymentStatus.INITIATED);
            payment.setFailureReason("GATEWAY_TIMEOUT");
            log.warn("Payment TIMEOUT for booking #{}. Remains INITIATED pending webhook/reconciliation", bookingId);
        } else {
            payment.setStatus(PaymentStatus.FAILED);
            payment.setFailureReason(result.getErrorMessage());
            booking.setStatus(BookingStatus.PAYMENT_FAILED);
            // Release seats back to available upon explicit payment failure
            for (ShowSeat ss : showSeats) {
                ss.setStatus(ShowSeatStatus.AVAILABLE);
                ss.setCurrentHoldId(null);
            }
            showSeatRepository.saveAll(showSeats);
            recordOutboxEvent("Payment", payment.getId().toString(), "PaymentFailed", Map.of("paymentId", payment.getId(), "paymentReference", payment.getPaymentReference(), "bookingId", booking.getId(), "reason", result.getErrorMessage()));
            auditService.recordAsync(userId, "PAYMENT_FAILED", "Payment", payment.getId().toString(), "Payment failed: " + result.getErrorMessage(), null);
            log.info("Payment FAILED for booking #{}. Seats released back to inventory", bookingId);
        }
        payment = paymentRepository.save(payment);
        bookingRepository.save(booking);
        return PaymentResponse.fromEntity(payment);
    }

    @Transactional(readOnly = true)
    public PaymentResponse getPaymentById(Long paymentId) {
        Payment payment = paymentRepository.findById(paymentId).orElseThrow(() -> new ResourceNotFoundException("Payment", "id", paymentId));
        Long currentUserId = SecurityUtils.getCurrentUserId();
        if (!payment.getBooking().getUser().getId().equals(currentUserId) && !SecurityUtils.isCurrentUserAdmin()) {
            throw new UnauthorizedAccessException("Not authorized to view this payment");
        }
        return PaymentResponse.fromEntity(payment);
    }

    /**
     * Simulated payment webhook for asynchronous provider notifications.
     * Idempotent: safe against duplicate deliveries.
     */
    @Transactional(isolation = Isolation.READ_COMMITTED)
    public void processWebhook(WebhookPaymentRequest request) {
        log.info("Received payment webhook for ref: {} status: {}", request.getPaymentReference(), request.getProviderStatus());
        Optional<Payment> optionalPayment = paymentRepository.findByPaymentReference(request.getPaymentReference());
        if (optionalPayment.isEmpty()) {
            log.warn("Payment not found for webhook reference: {}", request.getPaymentReference());
            return;
        }
        Payment payment = optionalPayment.get();
        if (payment.getStatus() == PaymentStatus.SUCCESS || payment.getStatus() == PaymentStatus.REFUNDED) {
            log.info("Webhook duplicate ignored: payment {} already in terminal state {}", payment.getId(), payment.getStatus());
            return;
        }
        Booking booking = bookingRepository.findByIdForUpdate(payment.getBooking().getId()).orElseThrow(() -> new ResourceNotFoundException("Booking", "id", payment.getBooking().getId()));
        if ("SUCCESS".equalsIgnoreCase(request.getProviderStatus())) {
            List<BookingSeat> bookingSeats = bookingSeatRepository.findByBookingIdWithDetails(booking.getId());
            List<Long> showSeatIds = bookingSeats.stream().map(bs -> bs.getShowSeat().getId()).toList();
            List<ShowSeat> showSeats = showSeatRepository.findByIdInForUpdate(showSeatIds);
            // Safe compensation check
            boolean unavailable = showSeats.stream().anyMatch(ss -> ss.getStatus() == ShowSeatStatus.BOOKED && !ss.getShow().getId().equals(booking.getShow().getId()));
            if (unavailable || booking.isExpired()) {
                payment.setStatus(PaymentStatus.REFUND_PENDING);
                payment.setFailureReason("SEATS_NO_LONGER_AVAILABLE_AUTO_REFUND");
                paymentRepository.save(payment);
                log.warn("Late webhook success arrived after expiration. Triggered REFUND_PENDING compensation.");
                return;
            }
            payment.setStatus(PaymentStatus.SUCCESS);
            booking.setStatus(BookingStatus.CONFIRMED);
            for (ShowSeat ss : showSeats) {
                ss.setStatus(ShowSeatStatus.BOOKED);
            }
            showSeatRepository.saveAll(showSeats);
        } else {
            payment.setStatus(PaymentStatus.FAILED);
            booking.setStatus(BookingStatus.PAYMENT_FAILED);
        }
        paymentRepository.save(payment);
        bookingRepository.save(booking);
    }

    private void recordOutboxEvent(String aggregateType, String aggregateId, String eventType, Map<String, Object> payloadMap) {
        try {
            String payloadJson = objectMapper.writeValueAsString(payloadMap);
            OutboxEvent outboxEvent = OutboxEvent.builder().id(UUID.randomUUID().toString()).aggregateType(aggregateType).aggregateId(aggregateId).eventType(eventType).payload(payloadJson).published(false).build();
            outboxEventRepository.save(outboxEvent);
        } catch (Exception e) {
            log.error("Failed to serialize outbox event: {}", e.getMessage());
        }
    }
}
