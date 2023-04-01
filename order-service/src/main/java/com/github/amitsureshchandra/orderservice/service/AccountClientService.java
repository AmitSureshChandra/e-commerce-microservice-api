package com.github.amitsureshchandra.orderservice.service;

import com.github.amitsureshchandra.orderservice.dto.DistributedTransaction;
import com.github.amitsureshchandra.orderservice.dto.OrderReq;
import com.github.amitsureshchandra.orderservice.dto.ProductDto;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class AccountClientService {

    final RestTemplate restTemplate;

    public AccountClientService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public boolean decrementAmount(OrderReq orderReq, DistributedTransaction transaction, ProductDto product, Double cost, Long buyerUserId) {
        System.out.println(" orderReq.getItem().getItemId() : " +  orderReq.getItem().getItemId());

        HttpHeaders headers = new HttpHeaders();
        headers.set("X-Transaction-ID", transaction.getId());
        HttpEntity<?> httpEntity = new HttpEntity<>(headers);
        System.out.println("http://account-service/users/api/v1/users/" + buyerUserId+ "/payment/"+ cost);
        restTemplate.exchange("http://account-service/users/api/v1/users/" + buyerUserId+ "/payment/"+ cost, HttpMethod.PUT, httpEntity,Void.class);
        return true;
    }
}
