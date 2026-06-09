package com.fintech.account.application.command;

import com.fintech.account.domain.model.Money;
import java.util.UUID;

public record WithdrawCommand(UUID accountId, Money amount) {}