package com.seatsure.common.exception;

import org.springframework.http.HttpStatus;

public class PaymentProcessingException extends SeatSureException {
    public PaymentProcessingException(String message) {
        super("PAYMENT_PROCESSING_FAILED", message, HttpStatus.UNPROCESSABLE_ENTITY);
    }
}
