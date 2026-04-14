package com.dunno.bankingsystem.exceptions;

import java.time.LocalDateTime;

public record ErrorResponse(
        String field,
        String message,
        int status,
        LocalDateTime timestamp
) {}
