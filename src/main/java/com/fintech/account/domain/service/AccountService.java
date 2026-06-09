package com.fintech.account.domain.service;

import com.fintech.account.application.command.DepositCommand;
import com.fintech.account.application.command.WithdrawCommand;
import com.fintech.account.application.query.GetAccountQuery;
import com.fintech.account.domain.event.MoneyDepositedEvent;
import com.fintech.account.domain.model.Account;
import com.fintech.account.domain.ports.in.DepositUseCase;
import com.fintech.account.domain.ports.in.GetAccountUseCase;
import com.fintech.account.domain.ports.in.WithdrawUseCase;
import com.fintech.account.domain.ports.out.AccountRepository;
import com.fintech.account.domain.ports.out.EventPublisher;

public class AccountService implements DepositUseCase, WithdrawUseCase, GetAccountUseCase {

    private final AccountRepository accountRepository;
    private final EventPublisher eventPublisher;

    public AccountService(AccountRepository accountRepository, EventPublisher eventPublisher) {
        this.accountRepository = accountRepository;
        this.eventPublisher = eventPublisher;
    }

    @Override
    public void deposit(DepositCommand command) {
        Account account = accountRepository.findById(command.accountId())
                .orElseThrow(() -> new IllegalArgumentException("Account not found: " + command.accountId()));

        account.deposit(command.amount());
        accountRepository.save(account);
        eventPublisher.publish(new MoneyDepositedEvent(account.getId(), command.amount()));
    }

    @Override
    public void withdraw(WithdrawCommand command) {
        Account account = accountRepository.findById(command.accountId())
                .orElseThrow(() -> new IllegalArgumentException("Account not found: " + command.accountId()));

        account.withdraw(command.amount());
        accountRepository.save(account);
    }

    @Override
    public Account getAccount(GetAccountQuery query) {
        return accountRepository.findById(query.accountId())
                .orElseThrow(() -> new IllegalArgumentException("Account not found: " + query.accountId()));
    }
}