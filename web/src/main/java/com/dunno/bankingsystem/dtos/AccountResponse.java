package com.dunno.bankingsystem.dtos;

import java.math.BigDecimal;

public record AccountResponse(
        Long accountId,
        BigDecimal balance
) {}
