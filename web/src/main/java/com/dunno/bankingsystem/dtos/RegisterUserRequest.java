package com.dunno.bankingsystem.dtos;

public record RegisterUserRequest(
        String email,
        String password,
        String passwordConfirmation
) {}
