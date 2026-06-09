package com.fintech.account.domain.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

public class Account {

    private final UUID id;
    private final String holder;
    private Money balance;
    private final List<Transaction> transactions;

    public Account(UUID id, String holder, Money balance) {
        if (holder == null || holder.isBlank()) {
            throw new IllegalArgumentException("Account holder cannot be blank");
        }
        this.id = id;
        this.holder = holder;
        this.balance = balance;
        this.transactions = new ArrayList<>();
    }

    public static Account open(String holder) {
        return new Account(UUID.randomUUID(), holder, Money.of("0.00"));
    }

    public void deposit(Money amount) {
        this.balance = this.balance.add(amount);
        this.transactions.add(Transaction.deposit(amount));
    }

    public void withdraw(Money amount) {
        if (amount.isGreaterThan(this.balance)) {
            throw new IllegalStateException("Insufficient balance");
        }
        this.balance = this.balance.subtract(amount);
        this.transactions.add(Transaction.withdrawal(amount));
    }

    public UUID getId() { return id; }
    public String getHolder() { return holder; }
    public Money getBalance() { return balance; }

    public List<Transaction> getTransactions() {
        return Collections.unmodifiableList(transactions);
    }
}