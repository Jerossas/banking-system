package com.dunno.bankingsystem.dtos;

public record LoginRequest(
        String email,
        String password
) {}
