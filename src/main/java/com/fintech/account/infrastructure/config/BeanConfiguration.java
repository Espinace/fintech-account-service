package com.fintech.account.infrastructure.config;

import com.fintech.account.domain.ports.out.AccountRepository;
import com.fintech.account.domain.ports.out.EventPublisher;
import com.fintech.account.domain.service.AccountService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfiguration {

    @Bean
    public AccountService accountService(AccountRepository accountRepository,
                                         EventPublisher eventPublisher) {
        return new AccountService(accountRepository, eventPublisher);
    }
}