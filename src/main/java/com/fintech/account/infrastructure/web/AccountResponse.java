package com.fintech.account.infrastructure.web;

import com.fintech.account.domain.model.Account;
import java.math.BigDecimal;
import java.util.UUID;

public record AccountResponse(
        UUID id,
        String holder,
        BigDecimal balance
) {
    public static AccountResponse from(Account account) {
        return new AccountResponse(
                account.getId(),
                account.getHolder(),
                account.getBalance().amount()
        );
    }
}