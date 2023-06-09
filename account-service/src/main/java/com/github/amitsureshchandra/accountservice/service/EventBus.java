package com.github.amitsureshchandra.accountservice.service;

import com.github.amitsureshchandra.accountservice.events.AccountTransactionEvent;
import com.github.amitsureshchandra.accountservice.dto.DistributedTransaction;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class EventBus {
    private final Map<String, DistributedTransaction> transactions = new HashMap<>();
    private final Map<String, AccountTransactionEvent> events = new HashMap<>();

    public void addTransaction(DistributedTransaction transaction){
        transactions.put(transaction.getId(), transaction);
    }

    public DistributedTransaction removeTransaction(String transactionId){
        return transactions.remove(transactionId);
    }

    public void addEvent(AccountTransactionEvent event) {
        events.put(event.getTransactionId(), event);
    }

    public AccountTransactionEvent removeEvent(String eventId){
        return events.remove(eventId);
    }
}
