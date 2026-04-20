package com.dunno.bankingsystem.commands;

public record LoginCommand(
        String email,
        String password
) {}
