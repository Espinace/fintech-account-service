package com.fintech.account.domain.ports.in;

import com.fintech.account.application.command.CreateAccountCommand;
import com.fintech.account.domain.model.Account;

public interface CreateAccountUseCase {
    Account create(CreateAccountCommand command);
}