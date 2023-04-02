package com.github.amitsureshchandra.accountservice.service;

import com.github.amitsureshchandra.accountservice.events.AccountTransactionEvent;
import com.github.amitsureshchandra.accountservice.dto.DistributedTransactionListDto;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class EventBus {
    private final Map<Long, DistributedTransactionListDto> transactions = new HashMap<>();
    private final Map<Long, AccountTransactionEvent> events = new HashMap<>();

    public void addTransaction(DistributedTransactionListDto transaction){
        transactions.put(transaction.getId(), transaction);
    }

    public DistributedTransactionListDto removeTransaction(Long transactionId){
        return transactions.remove(transactionId);
    }

    public DistributedTransactionListDto getTransaction(Long transactionId){
        return transactions.get(transactionId);
    }

    public void addEvent(AccountTransactionEvent event) {
        events.put(event.getTransactionId(), event);
    }

    public AccountTransactionEvent removeEvent(String eventId){
        return events.remove(eventId);
    }
}
