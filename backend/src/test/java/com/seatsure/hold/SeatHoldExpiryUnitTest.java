package com.seatsure.hold;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.Instant;

import static org.assertj.core.api.Assertions.assertThat;

class SeatHoldExpiryUnitTest {

    @Test
    @DisplayName("Should detect expired seat hold when current time exceeds expiresAt")
    void testHoldIsExpired() {
        SeatHold hold = SeatHold.builder()
                .id(1L)
                .status(HoldStatus.ACTIVE)
                .createdAt(Instant.now().minusSeconds(400))
                .expiresAt(Instant.now().minusSeconds(100))
                .build();

        assertThat(hold.isExpired()).isTrue();
    }

    @Test
    @DisplayName("Should confirm seat hold is still valid when current time is before expiresAt")
    void testHoldIsNotExpired() {
        SeatHold hold = SeatHold.builder()
                .id(2L)
                .status(HoldStatus.ACTIVE)
                .createdAt(Instant.now())
                .expiresAt(Instant.now().plusSeconds(300))
                .build();

        assertThat(hold.isExpired()).isFalse();
    }
}
