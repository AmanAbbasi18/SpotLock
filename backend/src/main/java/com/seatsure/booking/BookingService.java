package com.seatsure.booking;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.seatsure.audit.AuditService;
import com.seatsure.booking.dto.BookingResponse;
import com.seatsure.booking.dto.CreateBookingRequest;
import com.seatsure.common.exception.*;
import com.seatsure.common.response.PagedResponse;
import com.seatsure.common.util.SecurityUtils;
import com.seatsure.hold.HoldStatus;
import com.seatsure.hold.SeatHold;
import com.seatsure.hold.SeatHoldRepository;
import com.seatsure.outbox.OutboxEvent;
import com.seatsure.outbox.OutboxEventRepository;
import com.seatsure.payment.Payment;
import com.seatsure.payment.PaymentRepository;
import com.seatsure.payment.PaymentStatus;
import com.seatsure.showtime.ShowSeat;
import com.seatsure.showtime.ShowSeatRepository;
import com.seatsure.showtime.ShowSeatStatus;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import java.math.BigDecimal;
import java.time.Duration;
import java.time.Instant;
import java.util.*;

@Service
public class BookingService {
    private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(BookingService.class);
    private final BookingRepository bookingRepository;
    private final BookingSeatRepository bookingSeatRepository;
    private final SeatHoldRepository seatHoldRepository;
    private final ShowSeatRepository showSeatRepository;
    private final PaymentRepository paymentRepository;
    private final OutboxEventRepository outboxEventRepository;
    private final AuditService auditService;
    private final ObjectMapper objectMapper;

    public BookingService(BookingRepository bookingRepository, BookingSeatRepository bookingSeatRepository, SeatHoldRepository seatHoldRepository, ShowSeatRepository showSeatRepository, PaymentRepository paymentRepository, OutboxEventRepository outboxEventRepository, AuditService auditService, ObjectMapper objectMapper) {
        this.bookingRepository = bookingRepository;
        this.bookingSeatRepository = bookingSeatRepository;
        this.seatHoldRepository = seatHoldRepository;
        this.showSeatRepository = showSeatRepository;
        this.paymentRepository = paymentRepository;
        this.outboxEventRepository = outboxEventRepository;
        this.auditService = auditService;
        this.objectMapper = objectMapper;
    }

    @Value("${seatsure.booking.cancellation-window-hours:2}")
    private long cancellationWindowHours;

    @Transactional(isolation = Isolation.READ_COMMITTED)
    public BookingResponse createBooking(CreateBookingRequest request) {
        Long userId = SecurityUtils.getCurrentUserId();
        SeatHold hold = seatHoldRepository.findByIdForUpdate(request.getHoldId()).orElseThrow(() -> new ResourceNotFoundException("SeatHold", "id", request.getHoldId()));
        if (!hold.getUser().getId().equals(userId)) {
            throw new UnauthorizedAccessException("You can only book seats from your own hold");
        }
        if (hold.getStatus() != HoldStatus.ACTIVE || hold.isExpired()) {
            throw new HoldExpiredException(hold.getId());
        }
        List<ShowSeat> heldSeats = showSeatRepository.findByCurrentHoldId(hold.getId());
        if (heldSeats.isEmpty()) {
            throw new SeatUnavailableException("No held seats associated with hold #" + hold.getId());
        }
        BigDecimal serverCalculatedTotal = heldSeats.stream().map(ShowSeat::getPrice).reduce(BigDecimal.ZERO, BigDecimal::add);
        String reference = "BK-" + UUID.randomUUID().toString().replace("-", "").substring(0, 10).toUpperCase();
        Booking booking = Booking.builder().bookingReference(reference).user(hold.getUser()).show(hold.getShow()).status(BookingStatus.PENDING).totalAmount(serverCalculatedTotal).currency("INR").expiresAt(hold.getExpiresAt()).build();
        booking = bookingRepository.save(booking);
        List<BookingSeat> bookingSeats = new ArrayList<>(heldSeats.size());
        for (ShowSeat ss : heldSeats) {
            bookingSeats.add(BookingSeat.builder().booking(booking).showSeat(ss).priceAtBooking(ss.getPrice()).build());
        }
        bookingSeats = bookingSeatRepository.saveAll(bookingSeats);
        recordOutboxEvent("Booking", booking.getId().toString(), "BookingCreated", Map.of("bookingId", booking.getId(), "bookingReference", booking.getBookingReference(), "userId", userId, "showId", hold.getShow().getId(), "totalAmount", serverCalculatedTotal, "expiresAt", booking.getExpiresAt().toString()));
        auditService.recordAsync(userId, "BOOKING_CREATED", "Booking", booking.getId().toString(), "Created pending booking for show #" + hold.getShow().getId(), null);
        log.info("Created pending booking #{} with ref {} for user #{}", booking.getId(), reference, userId);
        return BookingResponse.fromEntity(booking, bookingSeats);
    }

    @Transactional(readOnly = true)
    public BookingResponse getBookingById(Long bookingId) {
        Booking booking = bookingRepository.findById(bookingId).orElseThrow(() -> new ResourceNotFoundException("Booking", "id", bookingId));
        Long currentUserId = SecurityUtils.getCurrentUserId();
        if (!booking.getUser().getId().equals(currentUserId) && !SecurityUtils.isCurrentUserAdmin()) {
            throw new UnauthorizedAccessException("Not authorized to view this booking");
        }
        List<BookingSeat> seats = bookingSeatRepository.findByBookingIdWithDetails(bookingId);
        return BookingResponse.fromEntity(booking, seats);
    }

    @Transactional(readOnly = true)
    public BookingResponse getBookingByReference(String reference) {
        Booking booking = bookingRepository.findByBookingReference(reference).orElseThrow(() -> new ResourceNotFoundException("Booking", "bookingReference", reference));
        Long currentUserId = SecurityUtils.getCurrentUserId();
        if (!booking.getUser().getId().equals(currentUserId) && !SecurityUtils.isCurrentUserAdmin()) {
            throw new UnauthorizedAccessException("Not authorized to view this booking");
        }
        List<BookingSeat> seats = bookingSeatRepository.findByBookingIdWithDetails(booking.getId());
        return BookingResponse.fromEntity(booking, seats);
    }

    @Transactional(readOnly = true)
    public PagedResponse<BookingResponse> getCurrentUserBookings(Pageable pageable) {
        Long userId = SecurityUtils.getCurrentUserId();
        Page<Booking> page = bookingRepository.findByUserIdOrderByCreatedAtDesc(userId, pageable);
        List<BookingResponse> content = page.getContent().stream().map(b -> {
            List<BookingSeat> seats = bookingSeatRepository.findByBookingIdWithDetails(b.getId());
            return BookingResponse.fromEntity(b, seats);
        }).toList();
        return PagedResponse.<BookingResponse>builder().content(content).pageNumber(page.getNumber()).pageSize(page.getSize()).totalElements(page.getTotalElements()).totalPages(page.getTotalPages()).isLast(page.isLast()).build();
    }

    @Transactional(isolation = Isolation.READ_COMMITTED)
    public BookingResponse cancelBooking(Long bookingId) {
        Long userId = SecurityUtils.getCurrentUserId();
        Booking booking = bookingRepository.findByIdForUpdate(bookingId).orElseThrow(() -> new ResourceNotFoundException("Booking", "id", bookingId));
        if (!booking.getUser().getId().equals(userId) && !SecurityUtils.isCurrentUserAdmin()) {
            throw new UnauthorizedAccessException("You are not authorized to cancel this booking");
        }
        if (booking.getStatus() != BookingStatus.CONFIRMED) {
            throw new InvalidStateTransitionException("Booking", booking.getStatus().name(), BookingStatus.CANCELLED.name());
        }
        // Validate cancellation cutoff policy (e.g. at least 2 hours before showtime)
        Instant cutoffTime = booking.getShow().getStartTime().minus(Duration.ofHours(cancellationWindowHours));
        if (Instant.now().isAfter(cutoffTime)) {
            throw new SeatSureException("CANCELLATION_DEADLINE_PASSED", String.format("Bookings can only be cancelled up to %d hours before show start time", cancellationWindowHours));
        }
        booking.setStatus(BookingStatus.CANCELLED);
        bookingRepository.save(booking);
        // Restore booked seats back to AVAILABLE inventory
        List<BookingSeat> bookingSeats = bookingSeatRepository.findByBookingIdWithDetails(bookingId);
        List<Long> showSeatIds = bookingSeats.stream().map(bs -> bs.getShowSeat().getId()).toList();
        List<ShowSeat> showSeats = showSeatRepository.findByIdInForUpdate(showSeatIds);
        for (ShowSeat ss : showSeats) {
            ss.setStatus(ShowSeatStatus.AVAILABLE);
            ss.setCurrentHoldId(null);
        }
        showSeatRepository.saveAll(showSeats);
        // Process simulated refund
        List<Payment> payments = paymentRepository.findByBookingId(bookingId);
        for (Payment payment : payments) {
            if (payment.getStatus() == PaymentStatus.SUCCESS) {
                payment.setStatus(PaymentStatus.REFUNDED);
                paymentRepository.save(payment);
            }
        }
        recordOutboxEvent("Booking", booking.getId().toString(), "BookingCancelled", Map.of("bookingId", booking.getId(), "bookingReference", booking.getBookingReference(), "userId", userId, "refundAmount", booking.getTotalAmount()));
        auditService.recordAsync(userId, "BOOKING_CANCELLED", "Booking", booking.getId().toString(), "Cancelled confirmed booking with refund of " + booking.getTotalAmount(), null);
        log.info("Cancelled booking #{} and restored {} seats", booking.getId(), showSeats.size());
        return BookingResponse.fromEntity(booking, bookingSeats);
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
