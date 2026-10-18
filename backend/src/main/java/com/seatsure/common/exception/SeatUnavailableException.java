package com.seatsure.common.exception;

import org.springframework.http.HttpStatus;

import java.util.List;

public class SeatUnavailableException extends SeatSureException {
    public SeatUnavailableException(String message) {
        super("SEAT_UNAVAILABLE", message, HttpStatus.CONFLICT);
    }

    public SeatUnavailableException(List<Long> unavailableSeatIds) {
        super("SEAT_UNAVAILABLE",
                String.format("One or more requested seats are no longer available: %s", unavailableSeatIds),
                HttpStatus.CONFLICT);
    }
}
