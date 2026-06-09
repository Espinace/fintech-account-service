package com.fintech.account.infrastructure.persistence;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table(name = "accounts")
public class AccountEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private String holder;

    @Column(nullable = false)
    private BigDecimal balance;

    public AccountEntity() {}

    public AccountEntity(UUID id, String holder, BigDecimal balance) {
        this.id = id;
        this.holder = holder;
        this.balance = balance;
    }

    public UUID getId() { return id; }
    public String getHolder() { return holder; }
    public BigDecimal getBalance() { return balance; }

    public void setBalance(BigDecimal balance) { this.balance = balance; }
}