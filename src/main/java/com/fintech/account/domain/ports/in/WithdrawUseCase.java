package com.fintech.account.domain.ports.in;

import com.fintech.account.application.command.WithdrawCommand;

public interface WithdrawUseCase {
    void withdraw(WithdrawCommand command);
}