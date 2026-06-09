package com.fintech.account.domain.ports.out;

import com.fintech.account.domain.model.Account;
import java.util.Optional;
import java.util.UUID;

public interface AccountRepository {
    void save(Account account);
    Optional<Account> findById(UUID id);
}