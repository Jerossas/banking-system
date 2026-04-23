package com.dunno.bankingsystem.exceptions;

public class UserNotAuthenticatedException extends DomainException {

    private static final int HTTP_CODE = 401;

    public UserNotAuthenticatedException(String message) {
        super(message, HTTP_CODE);
    }
}
