package com.seatsure.common.exception;

import org.springframework.http.HttpStatus;

public class DuplicateResourceException extends SeatSureException {
    public DuplicateResourceException(String message) {
        super("DUPLICATE_RESOURCE", message, HttpStatus.CONFLICT);
    }
}
