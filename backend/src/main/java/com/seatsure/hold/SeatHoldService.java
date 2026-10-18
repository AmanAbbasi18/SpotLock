package com.seatsure.hold;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.seatsure.common.exception.*;
import com.seatsure.common.util.SecurityUtils;
import com.seatsure.hold.dto.CreateHoldRequest;
import com.seatsure.hold.dto.SeatHoldResponse;
import com.seatsure.outbox.OutboxEvent;
import com.seatsure.outbox.OutboxEventRepository;
import com.seatsure.showtime.*;
import com.seatsure.user.User;
import com.seatsure.user.UserRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import java.math.BigDecimal;
import java.time.Duration;
import java.time.Instant;
import java.util.*;

@Service
public class SeatHoldService {
    private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(SeatHoldService.class);
    private final SeatHoldRepository seatHoldRepository;
    private final ShowRepository showRepository;
    private final ShowSeatRepository showSeatRepository;
    private final UserRepository userRepository;
    private final OutboxEventRepository outboxEventRepository;
    private final ObjectMapper objectMapper;

    public SeatHoldService(SeatHoldRepository seatHoldRepository, ShowRepository showRepository, ShowSeatRepository showSeatRepository, UserRepository userRepository, OutboxEventRepository outboxEventRepository, ObjectMapper objectMapper) {
        this.seatHoldRepository = seatHoldRepository;
        this.showRepository = showRepository;
        this.showSeatRepository = showSeatRepository;
        this.userRepository = userRepository;
        this.outboxEventRepository = outboxEventRepository;
        this.objectMapper = objectMapper;
    }

    @Value("${seatsure.booking.hold-duration-seconds:300}")
    private long holdDurationSeconds;

    /**
     * Acquires a temporary hold on the requested seats for a show.
     * Prevents double-booking via pessimistic row-level locking (SELECT ... FOR UPDATE)
     * with deterministic seat ID ordering to guarantee deadlock prevention.
     */
    @Transactional(isolation = Isolation.READ_COMMITTED)
    public SeatHoldResponse createHold(Long showId, CreateHoldRequest request) {
        Long userId = SecurityUtils.getCurrentUserId();
        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));
        List<Long> requestedSeatIds = request.getSeatIds();
        if (requestedSeatIds == null || requestedSeatIds.isEmpty()) {
            throw new IllegalArgumentException("Seat IDs cannot be empty");
        }
        // 1. Reject duplicate seat IDs within the request
        Set<Long> uniqueSeatIds = new HashSet<>(requestedSeatIds);
        if (uniqueSeatIds.size() != requestedSeatIds.size()) {
            throw new IllegalArgumentException("Duplicate seat IDs found in hold request");
        }
        // 2. Verify that the show exists and is open for booking
        Show show = showRepository.findById(showId).orElseThrow(() -> new ResourceNotFoundException("Show", "id", showId));
        if (show.getStatus() != ShowStatus.SCHEDULED) {
            throw new ShowNotBookingOpenException("Show is not scheduled for booking: status is " + show.getStatus());
        }
        if (show.getStartTime().isBefore(Instant.now())) {
            throw new ShowNotBookingOpenException("Cannot book seats for a past or ongoing show");
        }
        // 3. Deterministic row locking: Order seat IDs ascending to prevent deadlocks
        List<Long> sortedSeatIds = requestedSeatIds.stream().sorted().toList();
        List<ShowSeat> showSeats = showSeatRepository.findByShowIdAndSeatIdInForUpdate(showId, sortedSeatIds);
        // 4. Validate all requested seats belong to this show
        if (showSeats.size() != sortedSeatIds.size()) {
            List<Long> foundSeatIds = showSeats.stream().map(ss -> ss.getSeat().getId()).toList();
            List<Long> missingSeatIds = sortedSeatIds.stream().filter(id -> !foundSeatIds.contains(id)).toList();
            throw new ResourceNotFoundException("Seats not found for show " + showId + ": " + missingSeatIds);
        }
        // 5. Verify all requested seats are currently AVAILABLE
        List<Long> unavailableSeats = new ArrayList<>();
        BigDecimal totalAmount = BigDecimal.ZERO;
        for (ShowSeat ss : showSeats) {
            if (ss.getStatus() != ShowSeatStatus.AVAILABLE) {
                unavailableSeats.add(ss.getSeat().getId());
            } else {
                totalAmount = totalAmount.add(ss.getPrice());
            }
        }
        if (!unavailableSeats.isEmpty()) {
            throw new SeatUnavailableException(unavailableSeats);
        }
        // 6. Create authoritative hold record in database with configurable expiration
        Instant now = Instant.now();
        Instant expiresAt = now.plusSeconds(holdDurationSeconds);
        SeatHold seatHold = SeatHold.builder().user(user).show(show).status(HoldStatus.ACTIVE).createdAt(now).expiresAt(expiresAt).build();
        seatHold = seatHoldRepository.save(seatHold);
        // 7. Update all show seats atomically under lock
        for (ShowSeat ss : showSeats) {
            ss.setStatus(ShowSeatStatus.HELD);
            ss.setCurrentHoldId(seatHold.getId());
        }
        showSeatRepository.saveAll(showSeats);
        // 8. Publish outbox event
        recordOutboxEvent("SeatHold", seatHold.getId().toString(), "SeatHoldCreated", Map.of("holdId", seatHold.getId(), "userId", userId, "showId", showId, "seatIds", sortedSeatIds, "totalAmount", totalAmount, "expiresAt", expiresAt.toString()));
        log.info("Successfully acquired hold #{} for user #{} on show #{}, seats: {}", seatHold.getId(), userId, showId, sortedSeatIds);
        return SeatHoldResponse.builder().holdId(seatHold.getId()).showId(showId).userId(userId).seatIds(sortedSeatIds).totalAmount(totalAmount).status(seatHold.getStatus()).createdAt(seatHold.getCreatedAt()).expiresAt(seatHold.getExpiresAt()).expiresInSeconds(Duration.between(Instant.now(), expiresAt).getSeconds()).build();
    }

    @Transactional(readOnly = true)
    public SeatHoldResponse getHoldById(Long holdId) {
        SeatHold hold = seatHoldRepository.findById(holdId).orElseThrow(() -> new ResourceNotFoundException("SeatHold", "id", holdId));
        Long currentUserId = SecurityUtils.getCurrentUserId();
        if (!hold.getUser().getId().equals(currentUserId) && !SecurityUtils.isCurrentUserAdmin()) {
            throw new UnauthorizedAccessException("You are not authorized to view this seat hold");
        }
        List<ShowSeat> heldSeats = showSeatRepository.findByCurrentHoldId(holdId);
        List<Long> seatIds = heldSeats.stream().map(ss -> ss.getSeat().getId()).toList();
        BigDecimal totalAmount = heldSeats.stream().map(ShowSeat::getPrice).reduce(BigDecimal.ZERO, BigDecimal::add);
        long remainingSeconds = Math.max(0, Duration.between(Instant.now(), hold.getExpiresAt()).getSeconds());
        return SeatHoldResponse.builder().holdId(hold.getId()).showId(hold.getShow().getId()).userId(hold.getUser().getId()).seatIds(seatIds).totalAmount(totalAmount).status(hold.getStatus()).createdAt(hold.getCreatedAt()).expiresAt(hold.getExpiresAt()).expiresInSeconds(remainingSeconds).build();
    }

    @Transactional
    public void releaseHold(Long holdId) {
        SeatHold hold = seatHoldRepository.findByIdForUpdate(holdId).orElseThrow(() -> new ResourceNotFoundException("SeatHold", "id", holdId));
        Long currentUserId = SecurityUtils.getCurrentUserId();
        if (!hold.getUser().getId().equals(currentUserId) && !SecurityUtils.isCurrentUserAdmin()) {
            throw new UnauthorizedAccessException("You are not authorized to release this seat hold");
        }
        if (hold.getStatus() == HoldStatus.ACTIVE) {
            hold.setStatus(HoldStatus.RELEASED);
            hold.setReleasedAt(Instant.now());
            seatHoldRepository.save(hold);
            showSeatRepository.releaseSeatsForHold(holdId);
            recordOutboxEvent("SeatHold", holdId.toString(), "SeatHoldReleased", Map.of("holdId", holdId, "userId", currentUserId, "showId", hold.getShow().getId()));
            log.info("Released seat hold #{} by user #{}", holdId, currentUserId);
        }
    }

    @Transactional
    public void expireHold(Long holdId) {
        Optional<SeatHold> optionalHold = seatHoldRepository.findByIdForUpdate(holdId);
        if (optionalHold.isEmpty()) {
            return;
        }
        SeatHold hold = optionalHold.get();
        if (hold.getStatus() == HoldStatus.ACTIVE) {
            hold.setStatus(HoldStatus.EXPIRED);
            hold.setReleasedAt(Instant.now());
            seatHoldRepository.save(hold);
            showSeatRepository.releaseSeatsForHold(holdId);
            recordOutboxEvent("SeatHold", holdId.toString(), "SeatHoldExpired", Map.of("holdId", holdId, "showId", hold.getShow().getId(), "userId", hold.getUser().getId()));
            log.info("Auto-expired seat hold #{} and released seats back to inventory", holdId);
        }
    }

    private void recordOutboxEvent(String aggregateType, String aggregateId, String eventType, Map<String, Object> payloadMap) {
        try {
            String payloadJson = objectMapper.writeValueAsString(payloadMap);
            OutboxEvent outboxEvent = OutboxEvent.builder().id(UUID.randomUUID().toString()).aggregateType(aggregateType).aggregateId(aggregateId).eventType(eventType).payload(payloadJson).published(false).build();
            outboxEventRepository.save(outboxEvent);
        } catch (Exception e) {
            log.error("Failed to serialize outbox event payload: {}", e.getMessage());
        }
    }
}
