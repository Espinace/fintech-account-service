package com.fintech.account.domain.ports.in;

import com.fintech.account.application.query.GetAccountQuery;
import com.fintech.account.domain.model.Account;

public interface GetAccountUseCase {
    Account getAccount(GetAccountQuery query);
}