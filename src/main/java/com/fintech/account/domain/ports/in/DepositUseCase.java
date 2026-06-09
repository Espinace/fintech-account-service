package com.fintech.account.domain.ports.in;

import com.fintech.account.application.command.DepositCommand;

public interface DepositUseCase {
    void deposit(DepositCommand command);
}