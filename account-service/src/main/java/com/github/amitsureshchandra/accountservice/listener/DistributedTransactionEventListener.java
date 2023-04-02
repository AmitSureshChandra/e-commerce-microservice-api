package com.github.amitsureshchandra.accountservice.listener;

import com.github.amitsureshchandra.accountservice.service.EventBus;
import org.springframework.stereotype.Component;

@Component
public class DistributedTransactionEventListener {

    final EventBus eventBus;

    public DistributedTransactionEventListener(EventBus eventBus) {
        this.eventBus = eventBus;
    }

//    @RabbitListener(bindings = @QueueBinding(exchange = @Exchange(type = ExchangeTypes.TOPIC, name = "trx-events"), value = @Queue("trx-events-account")))
//    public void onMessage(DistributedTransaction transaction){
//        System.out.println("Got message : " + transaction);
//        eventBus.addTransaction(transaction);
//    }
}
