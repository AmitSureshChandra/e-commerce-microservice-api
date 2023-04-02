package com.github.amitsureshchandra.transactionserver.consumer;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class KafkaConsumer {
    @KafkaListener(topics = "trx")
    public void receive(String message) {
        System.out.println("Received message: " + message);
        // process the received message
    }
}
