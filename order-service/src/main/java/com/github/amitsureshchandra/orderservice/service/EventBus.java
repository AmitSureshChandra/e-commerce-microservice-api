package com.github.amitsureshchandra.orderservice.service;

import com.github.amitsureshchandra.orderservice.dto.DistributedTransaction;
import com.github.amitsureshchandra.orderservice.events.OrderTransactionEvent;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class EventBus {
    private final Map<String, DistributedTransaction> transactions = new HashMap<>();
    private final Map<String, OrderTransactionEvent> events = new HashMap<>();

    public void addTransaction(DistributedTransaction transaction){
        transactions.put(transaction.getId(), transaction);
    }

    public DistributedTransaction removeTransaction(String transactionId){
        return transactions.remove(transactionId);
    }

    public void addEvent(OrderTransactionEvent event) {
        events.put(event.getTransactionId(), event);
    }

    public OrderTransactionEvent removeEvent(String eventId){
        return events.remove(eventId);
    }
}
