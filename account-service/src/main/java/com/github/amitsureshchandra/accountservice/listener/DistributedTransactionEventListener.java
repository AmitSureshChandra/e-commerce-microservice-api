package com.github.amitsureshchandra.accountservice.listener;

import com.github.amitsureshchandra.accountservice.dto.DistributedTransaction;
import com.github.amitsureshchandra.accountservice.service.EventBus;
import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class DistributedTransactionEventListener {

    final EventBus eventBus;

    public DistributedTransactionEventListener(EventBus eventBus) {
        this.eventBus = eventBus;
    }

    @RabbitListener(bindings = @QueueBinding(exchange = @Exchange(type = ExchangeTypes.TOPIC, name = "trx-events"), value = @Queue("trx-events-account")))
    public void onMessage(DistributedTransaction transaction){
        eventBus.sendTransaction(transaction);
    }
}
