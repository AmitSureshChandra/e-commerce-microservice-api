package com.github.amitsureshchandra.orderservice.service;

import com.github.amitsureshchandra.orderservice.dto.DistributedTransaction;
import com.github.amitsureshchandra.orderservice.dto.OrderReq;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

@Service
public class CatalogClientService {

    final RestTemplate restTemplate;

    public CatalogClientService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public boolean checkStock(OrderReq orderReq) {
        ResponseEntity<Integer> resp = restTemplate.exchange(
                "http://catalog-service/catalogs/api/v1/catalogs/" + orderReq.getItem().getItemId(),
                HttpMethod.GET,
                null,
                Integer.class
        );

        Integer availableStock = resp.getBody();
        System.out.println("availableStock : " + availableStock);

        return resp.getStatusCode().is2xxSuccessful() &&
                availableStock != null &&
                availableStock >= orderReq.getItem().getQuantity();
    }

    public boolean decrementStocks(OrderReq orderReq, Long transaction) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("X-Transaction-ID", String.valueOf(transaction));

        HttpEntity<?> httpEntity = new HttpEntity<>(headers);

        try{
            ResponseEntity<Void> res = restTemplate.exchange("http://catalog-service/catalogs/api/v1/catalogs/" + orderReq.getItem().getItemId()+"/DECR/" + orderReq.getItem().getQuantity(), HttpMethod.PUT, httpEntity, Void.class);
            if(!res.getStatusCode().is2xxSuccessful()) return false;
        }catch (RestClientException exception) {
            System.out.println(exception.getMessage());
            return false;
        }
        return true;
    }
}
