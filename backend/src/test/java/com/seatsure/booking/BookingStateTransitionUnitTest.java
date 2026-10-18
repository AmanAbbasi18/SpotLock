package com.seatsure.booking;

import com.seatsure.common.exception.InvalidStateTransitionException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class BookingStateTransitionUnitTest {

    private void validateTransition(BookingStatus current, BookingStatus target) {
        Set<BookingStatus> allowedTargets = switch (current) {
            case PENDING -> Set.of(BookingStatus.CONFIRMED, BookingStatus.PAYMENT_FAILED, BookingStatus.EXPIRED, BookingStatus.CANCELLED);
            case CONFIRMED -> Set.of(BookingStatus.CANCELLED);
            case PAYMENT_FAILED, EXPIRED, CANCELLED -> Set.of();
        };

        if (!allowedTargets.contains(target)) {
            throw new InvalidStateTransitionException("Booking", current.name(), target.name());
        }
    }

    @Test
    @DisplayName("Should allow valid booking lifecycle transitions")
    void testValidTransitions() {
        assertThatCode(() -> validateTransition(BookingStatus.PENDING, BookingStatus.CONFIRMED)).doesNotThrowAnyException();
        assertThatCode(() -> validateTransition(BookingStatus.PENDING, BookingStatus.PAYMENT_FAILED)).doesNotThrowAnyException();
        assertThatCode(() -> validateTransition(BookingStatus.PENDING, BookingStatus.EXPIRED)).doesNotThrowAnyException();
        assertThatCode(() -> validateTransition(BookingStatus.CONFIRMED, BookingStatus.CANCELLED)).doesNotThrowAnyException();
    }

    @Test
    @DisplayName("Should reject invalid booking lifecycle transitions with domain exception")
    void testInvalidTransitions() {
        assertThatThrownBy(() -> validateTransition(BookingStatus.CONFIRMED, BookingStatus.PENDING))
                .isInstanceOf(InvalidStateTransitionException.class)
                .hasMessageContaining("Cannot transition Booking from status 'CONFIRMED' to 'PENDING'");

        assertThatThrownBy(() -> validateTransition(BookingStatus.CANCELLED, BookingStatus.CONFIRMED))
                .isInstanceOf(InvalidStateTransitionException.class);

        assertThatThrownBy(() -> validateTransition(BookingStatus.EXPIRED, BookingStatus.CONFIRMED))
                .isInstanceOf(InvalidStateTransitionException.class);
    }
}
