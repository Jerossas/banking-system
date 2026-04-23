package com.dunno.bankingsystem.exceptions;

public class UserNotFoundException extends DomainException {

    private static final int HTTP_CODE = 404;

    public UserNotFoundException(String message) {
        super(message, HTTP_CODE);
    }
}
