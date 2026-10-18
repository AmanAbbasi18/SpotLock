package com.seatsure.common.exception;

import org.springframework.http.HttpStatus;

public class UnauthorizedAccessException extends SeatSureException {
    public UnauthorizedAccessException(String message) {
        super("UNAUTHORIZED_ACCESS", message, HttpStatus.FORBIDDEN);
    }
}
