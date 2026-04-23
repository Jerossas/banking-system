package com.dunno.bankingsystem.commands;

public record GetAccountBalanceCommand(
        String userEmail
) {
}
