package com.github.amitsureshchandra.orderservice.service;

import com.github.amitsureshchandra.orderservice.dto.DistributedTransaction;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class TransactionClientService {
    final RestTemplate restTemplate;

    public TransactionClientService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public DistributedTransaction createTransaction() {
        return restTemplate.postForObject("http://transaction-service/transactions/api/v1/transactions",
                new DistributedTransaction(), DistributedTransaction.class);
    }
}
