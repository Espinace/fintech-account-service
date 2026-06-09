package com.fintech.account.domain.event;

import com.fintech.account.domain.model.Money;
import java.util.UUID;

public class MoneyDepositedEvent extends DomainEvent {
    private final UUID accountId;
    private final Money amount;

    public MoneyDepositedEvent(UUID accountId, Money amount) {
        super();
        this.accountId = accountId;
        this.amount = amount;
    }

    public UUID getAccountId() { return accountId; }
    public Money getAmount() { return amount; }
}