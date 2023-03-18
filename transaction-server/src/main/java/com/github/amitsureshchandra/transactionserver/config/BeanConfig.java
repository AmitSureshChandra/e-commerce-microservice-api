package com.github.amitsureshchandra.transactionserver.config;

import org.springframework.amqp.core.TopicExchange;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfig {

    @Value("${transaction-event-topic}")
    private String trxTopic;

    @Bean
    TopicExchange topic(){
        return new TopicExchange(trxTopic);
    }
}
