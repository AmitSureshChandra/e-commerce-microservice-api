package com.github.amitsureshchandra.transactionserver.service;

import com.github.amitsureshchandra.transactionserver.dto.DistributedTransaction;
import com.github.amitsureshchandra.transactionserver.dto.DistributedTransactionParticipant;
import com.github.amitsureshchandra.transactionserver.enums.DistributedTransactionStatus;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
public class TransactionService {

    @Value("${transaction-event-topic}")
    private String trxTopic;
    final
    RabbitTemplate rabbitTemplate;
    Map<String, DistributedTransaction> transactions = new HashMap<>();

    public TransactionService(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public DistributedTransaction add(DistributedTransaction transaction) {
        String tid = UUID.randomUUID().toString();
        transaction.setId(tid);
        transactions.put(tid, transaction);
        return transactions.get(tid);
    }

    public DistributedTransaction findById(String tid) {
        return transactions.get(tid);
    }

    public void finish(String tid, DistributedTransactionStatus status) {
        DistributedTransaction transaction = findById(tid);
        if (transaction != null) {
            transaction.setStatus(status);
            rabbitTemplate.convertAndSend(trxTopic, transaction);
        }
    }

    public void addParticipants(String tid, DistributedTransactionParticipant participant) {
        if (!transactions.containsKey(tid)) return;
        transactions.get(tid).getParticipants().add(participant);
    }

    public void updateParticipants(String tid, String serviceId, DistributedTransactionStatus status) {
        if (!transactions.containsKey(tid)) return;

        for (DistributedTransactionParticipant participant : transactions.get(tid).getParticipants()) {
            if(participant.getServiceId().equals(serviceId)) {
                participant.setStatus(status);
                rabbitTemplate.convertAndSend(trxTopic, transactions.get(tid));
                return;
            }
        }
    }

    public Object getAll() {
        return transactions;
    }
}
