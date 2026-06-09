package com.fintech.account.domain.ports.out;

import com.fintech.account.domain.event.DomainEvent;

public interface EventPublisher {
    void publish(DomainEvent event);
}