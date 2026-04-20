package com.dunno.bankingsystem.exceptions;

public class InvalidCredentialsException extends DomainException {

    private static final int HTTP_CODE = 401;

    public InvalidCredentialsException(String message) {
        super(message, 401);
    }
}
