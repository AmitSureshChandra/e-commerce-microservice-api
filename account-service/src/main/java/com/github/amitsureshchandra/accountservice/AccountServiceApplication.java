package com.github.amitsureshchandra.accountservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.kafka.annotation.EnableKafka;


@ComponentScan({
        "com.github.amitsureshchandra.commonfeature",
        "com.github.amitsureshchandra.accountservice"
})
@SpringBootApplication
@EnableKafka
public class AccountServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(AccountServiceApplication.class, args);
    }

}
