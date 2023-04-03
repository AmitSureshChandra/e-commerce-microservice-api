package com.github.amitsureshchandra.orderservice.consumer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.amitsureshchandra.common.dto.transaction.DistributedTransactionListDto;
import com.github.amitsureshchandra.orderservice.service.EventBus;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class KafkaConsumer {
    final ObjectMapper objectMapper;

    final EventBus eventBus;

    public KafkaConsumer(ObjectMapper objectMapper, EventBus eventBus) {
        this.objectMapper = objectMapper;
        this.eventBus = eventBus;
    }

    @KafkaListener(topics = "trx")
    public void receive(DistributedTransactionListDto transaction) throws IOException {
        System.out.println("Received message: " + transaction);
        // process the received message
        eventBus.addTransaction(transaction);
    }
}
