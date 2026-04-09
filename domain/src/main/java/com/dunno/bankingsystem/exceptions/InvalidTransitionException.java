package com.dunno.bankingsystem.exceptions;

public class InvalidTransitionException extends DomainException {

    private static final int HTTP_CODE = 409;

    public InvalidTransitionException(String message) {
        super(message, HTTP_CODE);
    }
}
