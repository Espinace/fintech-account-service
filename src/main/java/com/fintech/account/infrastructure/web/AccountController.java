package com.fintech.account.infrastructure.web;

import com.fintech.account.application.command.CreateAccountCommand;
import com.fintech.account.application.command.DepositCommand;
import com.fintech.account.application.command.WithdrawCommand;
import com.fintech.account.application.query.GetAccountQuery;
import com.fintech.account.domain.model.Account;
import com.fintech.account.domain.model.Money;
import com.fintech.account.domain.ports.in.CreateAccountUseCase;
import com.fintech.account.domain.ports.in.DepositUseCase;
import com.fintech.account.domain.ports.in.GetAccountUseCase;
import com.fintech.account.domain.ports.in.WithdrawUseCase;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/accounts")
public class AccountController {

    private final DepositUseCase depositUseCase;
    private final WithdrawUseCase withdrawUseCase;
    private final GetAccountUseCase getAccountUseCase;
    private final CreateAccountUseCase createAccountUseCase;

    public AccountController(DepositUseCase depositUseCase,
                             WithdrawUseCase withdrawUseCase,
                             GetAccountUseCase getAccountUseCase,
                             CreateAccountUseCase createAccountUseCase) {
        this.depositUseCase = depositUseCase;
        this.withdrawUseCase = withdrawUseCase;
        this.getAccountUseCase = getAccountUseCase;
        this.createAccountUseCase = createAccountUseCase;
    }

    @PostMapping
    public ResponseEntity<AccountResponse> createAccount(@Valid @RequestBody CreateAccountRequest request) {
        Account account = createAccountUseCase.create(new CreateAccountCommand(request.holder()));
        return ResponseEntity.status(201).body(AccountResponse.from(account));
    }

    @PostMapping("/{id}/deposit")
    public ResponseEntity<Void> deposit(@PathVariable UUID id,
                                        @Valid @RequestBody DepositRequest request) {
        depositUseCase.deposit(new DepositCommand(id, Money.of(request.amount())));
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{id}/withdraw")
    public ResponseEntity<Void> withdraw(@PathVariable UUID id,
                                         @Valid @RequestBody WithdrawRequest request) {
        withdrawUseCase.withdraw(new WithdrawCommand(id, Money.of(request.amount())));
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<AccountResponse> getAccount(@PathVariable UUID id) {
        Account account = getAccountUseCase.getAccount(new GetAccountQuery(id));
        return ResponseEntity.ok(AccountResponse.from(account));
    }
}