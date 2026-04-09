package com.dunno.bankingsystem.models;

import com.dunno.bankingsystem.exceptions.InvalidFieldException;
import com.dunno.bankingsystem.exceptions.InvalidTransitionException;
import com.dunno.bankingsystem.models.enums.TransactionStatus;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Transaction {

    private Long id;
    private final Long sourceAccountId;
    private final Long targetAccountId;
    private final BigDecimal amount;
    private TransactionStatus status;
    private LocalDateTime createdAt;

    public Transaction(Long sourceAccountId, Long targetAccountId, BigDecimal amount){

        if(sourceAccountId == null) {
            throw new InvalidFieldException("sourceAccountId", "Source Account Id cannot be null");
        }

        if(targetAccountId == null) {
            throw new InvalidFieldException("targetAccountId", "Target Account Id cannot be null");
        }

        if(amount == null) {
            throw new InvalidFieldException("amount", "Source Account Id cannot be null");
        }

        this.sourceAccountId = sourceAccountId;
        this.targetAccountId = targetAccountId;
        this.amount = amount;

        this.status = TransactionStatus.PENDING;
    }

    private Transaction(Long id, Long sourceAccountId, Long targetAccountId, BigDecimal amount, TransactionStatus status, LocalDateTime createdAt){
        this.id = id;
        this.sourceAccountId = sourceAccountId;
        this.targetAccountId = targetAccountId;
        this.amount = amount;
        this.status = status;
        this.createdAt = createdAt;
    }

    public Long getId() {
        return id;
    }

    public Long getSourceAccountId() {
        return sourceAccountId;
    }

    public Long getTargetAccountId() {
        return targetAccountId;
    }

    public BigDecimal getTransactionAmount(){
        return this.amount;
    }

    public LocalDateTime getTransactionDate() {
        return createdAt;
    }

    public void changeStatus(TransactionStatus status){
        if(status == null) {
            throw new InvalidFieldException("status", "Status cannot be null");
        }

        if(this.status != TransactionStatus.PENDING && status.equals(TransactionStatus.PENDING)) {
            throw new InvalidTransitionException("Transaction cannot go from " + this.status.name() + " to " + status.name());
        }

        this.status = status;
    }

    public static Transaction restore(Long id, Long sourceAccountId, Long targetAccountId, BigDecimal amount, TransactionStatus status, LocalDateTime createdAt){
        return new Transaction(id, sourceAccountId, targetAccountId, amount, status, createdAt);
    }
}
