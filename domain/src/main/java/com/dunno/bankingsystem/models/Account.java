package com.dunno.bankingsystem.models;

import com.dunno.bankingsystem.exceptions.InvalidFieldException;
import com.dunno.bankingsystem.exceptions.NotEnoughBalanceException;

import java.math.BigDecimal;

public class Account {

    private Long id;
    private final Long userId;
    private BigDecimal balance;

    public Account(Long userId){

        if(userId == null) {
            throw new InvalidFieldException("userId", "Cannot create an account without an account holder");
        }

        this.userId = userId;
        this.balance = new BigDecimal("0.00");
    }

    private Account(Long id, Long userId, BigDecimal balance){
        this.id = id;
        this.userId = userId;
        this.balance = balance;
    }

    public Long getId() {
        return id;
    }

    public Long getAccountHolder() {
        return userId;
    }

    public void modifyBalance(BigDecimal balance){
        BigDecimal newBalance = this.balance.add(balance);

        if(newBalance.compareTo(BigDecimal.ZERO) < 0) {
            throw new NotEnoughBalanceException("Not enough balance to complete this operation.");
        }

        this.balance = newBalance;
    }

    public static Account restore(Long id, Long userId, BigDecimal balance){
        return new Account(id, userId, balance);
    }
}
