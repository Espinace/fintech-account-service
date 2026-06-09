package com.fintech.account.domain.model;

import java.time.Instant;
import java.util.UUID;

public record Transaction(
        UUID id,
        TransactionType type,
        Money amount,
        Instant createdAt
) {

    public static Transaction deposit(Money amount) {
        return new Transaction(UUID.randomUUID(), TransactionType.DEPOSIT, amount, Instant.now());
    }

    public static Transaction withdrawal(Money amount) {
        return new Transaction(UUID.randomUUID(), TransactionType.WITHDRAWAL, amount, Instant.now());
    }
}