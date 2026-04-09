package com.dunno.bankingsystem.exceptions;

public class NotEnoughBalanceException extends DomainException {

    private static final int HTTP_CODE = 400;

    public NotEnoughBalanceException(String message) {
        super(message, HTTP_CODE);
    }
}
