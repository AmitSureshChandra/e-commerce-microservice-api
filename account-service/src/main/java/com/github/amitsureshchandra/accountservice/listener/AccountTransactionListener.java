package com.github.amitsureshchandra.accountservice.listener;

import com.github.amitsureshchandra.accountservice.dto.DistributedTransaction;
import com.github.amitsureshchandra.accountservice.enums.DistributedTransactionStatus;
import com.github.amitsureshchandra.accountservice.events.AccountTransactionEvent;
import com.github.amitsureshchandra.accountservice.exception.AccountProcessingException;
import com.github.amitsureshchandra.accountservice.service.EventBus;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;
import org.springframework.web.client.RestTemplate;

@Component
public class AccountTransactionListener {
    final RestTemplate restTemplate;

    final EventBus eventBus;

    @Value("${transaction.server.url}")
    String transactionServerUrl;

    public AccountTransactionListener(RestTemplate restTemplate, EventBus eventBus) {
        this.restTemplate = restTemplate;
        this.eventBus = eventBus;
    }

    @TransactionalEventListener(phase = TransactionPhase.BEFORE_COMMIT)
    public void handleEventBeforeCommit(AccountTransactionEvent event) throws InterruptedException, AccountProcessingException {
        eventBus.addEvent(event);
        DistributedTransaction transaction = null;

        for (int i = 0; i < 100; i++) {
            transaction = eventBus.removeTransaction(event.getTransactionId());
            if(transaction == null) {
                Thread.sleep(100);
            }else break;
        }

        if(transaction == null || transaction.getStatus() != DistributedTransactionStatus.CONFIRMED)
            throw new AccountProcessingException();
    }

    @TransactionalEventListener(phase = TransactionPhase.AFTER_ROLLBACK)
    public void handleAfterRollback(AccountTransactionEvent event){
        restTemplate.put("http://transaction-service/transactions/api/v1/transactions/"+ event.getTransactionId()+"/participants/account-service/TO_ROLLBACK", null);
    }

    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMPLETION)
    public void handleAfterCompletion(AccountTransactionEvent event){
        restTemplate.put(
                "http://transaction-service/transactions/api/v1/transactions/"+event.getTransactionId()+"/participants/account-service/CONFIRMED"
                , null);
    }
}
