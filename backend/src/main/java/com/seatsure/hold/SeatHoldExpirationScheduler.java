package com.seatsure.hold;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import java.time.Instant;
import java.util.List;

@Component
public class SeatHoldExpirationScheduler {
    private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(SeatHoldExpirationScheduler.class);
    private final SeatHoldRepository seatHoldRepository;
    private final SeatHoldService seatHoldService;

    public SeatHoldExpirationScheduler(SeatHoldRepository seatHoldRepository, SeatHoldService seatHoldService) {
        this.seatHoldRepository = seatHoldRepository;
        this.seatHoldService = seatHoldService;
    }

    /**
     * Periodic background worker to identify and expire overdue seat holds.
     * Guarantees eventual consistency and inventory recovery if a client disconnects.
     */
    @Scheduled(fixedDelay = 10000)
    public void cleanupExpiredHolds() {
        try {
            List<SeatHold> expiredHolds = seatHoldRepository.findExpiredActiveHolds(Instant.now());
            if (!expiredHolds.isEmpty()) {
                log.debug("Found {} expired seat holds to release", expiredHolds.size());
                for (SeatHold hold : expiredHolds) {
                    try {
                        seatHoldService.expireHold(hold.getId());
                    } catch (Exception e) {
                        log.error("Failed to expire hold #{}: {}", hold.getId(), e.getMessage());
                    }
                }
            }
        } catch (Exception e) {
            log.error("Error executing seat hold expiration scheduler: {}", e.getMessage());
        }
    }
}
