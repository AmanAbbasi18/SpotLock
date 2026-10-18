package com.seatsure.common.exception;

import org.springframework.http.HttpStatus;

public class SeatSureException extends RuntimeException {
    private final String errorCode;
    private final HttpStatus status;

    public SeatSureException(String errorCode, String message, HttpStatus status) {
        super(message);
        this.errorCode = errorCode;
        this.status = status;
    }

    public SeatSureException(String errorCode, String message) {
        this(errorCode, message, HttpStatus.BAD_REQUEST);
    }

    public String getErrorCode() {
        return this.errorCode;
    }

    public HttpStatus getStatus() {
        return this.status;
    }
}
