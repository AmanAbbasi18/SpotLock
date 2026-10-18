package com.seatsure.common.exception;

import org.springframework.http.HttpStatus;

public class ShowNotBookingOpenException extends SeatSureException {
    public ShowNotBookingOpenException(String message) {
        super("SHOW_NOT_OPEN_FOR_BOOKING", message, HttpStatus.BAD_REQUEST);
    }
}
