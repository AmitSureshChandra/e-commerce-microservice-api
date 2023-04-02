package com.github.amitsureshchandra.accountservice.consumer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.amitsureshchandra.accountservice.dto.DistributedTransactionListDto;
import com.github.amitsureshchandra.accountservice.service.EventBus;
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
    public void receive(String transaction) throws IOException {
        System.out.println("Received message: " + transaction);
        System.out.println("Got message : " + transaction);

        DistributedTransactionListDto dto = objectMapper.readValue(transaction.getBytes(), DistributedTransactionListDto.class);
        System.out.println(dto);
        eventBus.addTransaction(dto);
        // process the received message
    }
}
