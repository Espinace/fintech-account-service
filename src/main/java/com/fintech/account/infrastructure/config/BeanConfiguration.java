package com.fintech.account.infrastructure.config;

import com.fintech.account.domain.ports.out.AccountRepository;
import com.fintech.account.domain.ports.out.EventPublisher;
import com.fintech.account.domain.service.AccountService;
import com.fintech.account.infrastructure.messaging.KafkaEventPublisher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.KafkaTemplate;

@Configuration
public class BeanConfiguration {

    @Bean
    public EventPublisher eventPublisher(KafkaTemplate<String, Object> kafkaTemplate) {
        return new KafkaEventPublisher(kafkaTemplate);
    }

    @Bean
    public AccountService accountService(AccountRepository accountRepository,
                                         EventPublisher eventPublisher) {
        return new AccountService(accountRepository, eventPublisher);
    }
}