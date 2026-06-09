package com.fintech.account.infrastructure.messaging;

import com.fintech.account.domain.event.DomainEvent;
import com.fintech.account.domain.event.MoneyDepositedEvent;
import com.fintech.account.domain.ports.out.EventPublisher;
import org.springframework.kafka.core.KafkaTemplate;

public class KafkaEventPublisher implements EventPublisher {

    private static final String ACCOUNT_EVENTS_TOPIC = "account-events";

    private final KafkaTemplate<String, Object> kafkaTemplate;

    public KafkaEventPublisher(KafkaTemplate<String, Object> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @Override
    public void publish(DomainEvent event) {
        if (event instanceof MoneyDepositedEvent depositedEvent) {
            kafkaTemplate.send(ACCOUNT_EVENTS_TOPIC,
                    depositedEvent.getAccountId().toString(),
                    depositedEvent);
        }
    }
}