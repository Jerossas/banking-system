package com.dunno.bankingsystem.models.enums;

import com.dunno.bankingsystem.exceptions.InvalidFieldException;

public enum TransactionStatus {
    PENDING, COMPLETED, FAILED;

    public static TransactionStatus from(String value){
        if (value == null) {
            throw new InvalidFieldException("transactionStatus", "Transaction Status cannot be null.");
        }
        try {
            return TransactionStatus.valueOf(value.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new InvalidFieldException("transactionStatus", "Invalid Transaction Status value: " + value);
        }
    }
}
