package com.dunno.bankingsystem.commands;

public record RegisterUserCommand(
        String email,
        String password,
        String passwordConfirmation
) {}
