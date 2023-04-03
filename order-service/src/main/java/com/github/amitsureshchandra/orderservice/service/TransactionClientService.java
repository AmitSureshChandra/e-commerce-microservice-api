package com.github.amitsureshchandra.orderservice.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.amitsureshchandra.common.dto.transaction.DistributedTransactionParticipantDto;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class TransactionClientService {
    final RestTemplate restTemplate;
    final ObjectMapper objectMapper;

    public TransactionClientService(RestTemplate restTemplate, ObjectMapper objectMapper) {
        this.restTemplate = restTemplate;
        this.objectMapper = objectMapper;
    }

    public Long createTransaction(List<DistributedTransactionParticipantDto> participants) {
        return restTemplate.postForObject("http://transaction-service/transactions/api/v1/transactions",
                participants, Long.class);
    }

    public void addCoordination(Long txId) {
        restTemplate.postForObject("http://transaction-service/transactions/api/v1/transactions/" + txId + "/coordinate",
                null, Void.class);

        System.out.println("Coordinator activated for txid : " + txId);
    }
}
