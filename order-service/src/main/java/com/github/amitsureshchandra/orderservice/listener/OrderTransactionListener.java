package com.github.amitsureshchandra.orderservice.listener;

import com.github.amitsureshchandra.common.dto.transaction.DistributedTransactionListDto;
import com.github.amitsureshchandra.common.enums.DistributedTransactionStatus;
import com.github.amitsureshchandra.orderservice.events.OrderTransactionEvent;
import com.github.amitsureshchandra.orderservice.exception.OrderProcessingException;
import com.github.amitsureshchandra.orderservice.service.EventBus;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;
import org.springframework.web.client.RestTemplate;

@Component
public class OrderTransactionListener {
    final RestTemplate restTemplate;
    final EventBus eventBus;

    public OrderTransactionListener(RestTemplate restTemplate, EventBus eventBus) {
        this.restTemplate = restTemplate;
        this.eventBus = eventBus;
    }

    @TransactionalEventListener(phase = TransactionPhase.BEFORE_COMMIT)
    public void handleEventBeforeCommit(OrderTransactionEvent event) throws InterruptedException, OrderProcessingException {
        eventBus.addEvent(event);

        restTemplate.put("http://transaction-service/transactions/api/v1/transactions/"+ event.getTransactionId()+"/participants/order-service/PREPARE", null);
        DistributedTransactionListDto transaction = null;

        for (int i = 0; i < 100; i++) {
            transaction = eventBus.getTransaction(event.getTransactionId());

            // wait for commit msg
            if(transaction != null && transaction.getStatus().equals(DistributedTransactionStatus.COMMIT)) break;
            Thread.sleep(100);
        }

        System.out.println("trx : " + transaction);

        if(transaction == null || transaction.getStatus() != DistributedTransactionStatus.COMMIT)
            throw new OrderProcessingException("Some error occurred while processing");
    }

    @TransactionalEventListener(phase = TransactionPhase.AFTER_ROLLBACK)
    public void handleAfterRollback(OrderTransactionEvent event){
        restTemplate.put("http://transaction-service/transactions/api/v1/transactions/"+ event.getTransactionId()+"/participants/order-service/TO_ROLLBACK", null);
    }

    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMPLETION)
    public void handleAfterCompletion(OrderTransactionEvent event){
        restTemplate.put(
                "http://transaction-service/transactions/api/v1/transactions/"+event.getTransactionId()+"/participants/order-service/CONFIRMED"
                , null);
    }
}
