package com.fintech.account.infrastructure.persistence;

import com.fintech.account.domain.model.Account;
import com.fintech.account.domain.model.Money;

public class AccountMapper {

    private AccountMapper() {}

    public static Account toDomain(AccountEntity entity) {
        return new Account(
                entity.getId(),
                entity.getHolder(),
                Money.of(entity.getBalance())
        );
    }

    public static AccountEntity toEntity(Account account) {
        return new AccountEntity(
                account.getId(),
                account.getHolder(),
                account.getBalance().amount()
        );
    }
}