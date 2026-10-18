package com.seatsure.common.exception;

import org.springframework.http.HttpStatus;

public class HoldExpiredException extends SeatSureException {
    public HoldExpiredException(String message) {
        super("HOLD_EXPIRED", message, HttpStatus.GONE);
    }

    public HoldExpiredException(Long holdId) {
        super("HOLD_EXPIRED", String.format("Seat hold #%d has expired and seats have been released", holdId), HttpStatus.GONE);
    }
}
