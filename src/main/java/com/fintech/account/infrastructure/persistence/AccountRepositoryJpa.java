package com.fintech.account.infrastructure.persistence;

import com.fintech.account.domain.model.Account;
import com.fintech.account.domain.ports.out.AccountRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Repository
@Transactional
public class AccountRepositoryJpa implements AccountRepository {

    private final AccountJpaRepository jpaRepository;

    public AccountRepositoryJpa(AccountJpaRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public void save(Account account) {
        jpaRepository.save(AccountMapper.toEntity(account));
    }

    @Override
    public Optional<Account> findById(UUID id) {
        return jpaRepository.findById(id)
                .map(AccountMapper::toDomain);
    }
}