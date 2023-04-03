package com.github.amitsureshchandra.transactionserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
@EnableKafka
public class TransactionServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(TransactionServerApplication.class, args);
    }
}
