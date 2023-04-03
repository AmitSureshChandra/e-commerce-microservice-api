package com.github.amitsureshchandra.transactionserver.service;

import com.github.amitsureshchandra.common.dto.transaction.DistributedTransactionListDto;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaService {
    final KafkaTemplate<String, DistributedTransactionListDto> kafkaTemplate;
    private final String TOPIC_NAME = "trx";

    public KafkaService(KafkaTemplate<String, DistributedTransactionListDto> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendMessage(DistributedTransactionListDto msg) {
        kafkaTemplate.send(KafkaService.this.TOPIC_NAME, msg);
    }

}
