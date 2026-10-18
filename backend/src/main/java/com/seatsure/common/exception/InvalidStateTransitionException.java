package com.seatsure.common.exception;

import org.springframework.http.HttpStatus;

public class InvalidStateTransitionException extends SeatSureException {
    public InvalidStateTransitionException(String entityName, String fromState, String toState) {
        super("INVALID_STATE_TRANSITION",
                String.format("Cannot transition %s from status '%s' to '%s'", entityName, fromState, toState),
                HttpStatus.UNPROCESSABLE_ENTITY);
    }

    public InvalidStateTransitionException(String message) {
        super("INVALID_STATE_TRANSITION", message, HttpStatus.UNPROCESSABLE_ENTITY);
    }
}
