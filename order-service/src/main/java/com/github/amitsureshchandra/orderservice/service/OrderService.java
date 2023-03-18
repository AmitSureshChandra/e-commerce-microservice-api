package com.github.amitsureshchandra.orderservice.service;

import com.github.amitsureshchandra.orderservice.dto.DistributedTransaction;
import com.github.amitsureshchandra.orderservice.dto.OrderDto;
import com.github.amitsureshchandra.orderservice.dto.OrderReq;
import com.github.amitsureshchandra.orderservice.dto.ProductDto;
import com.github.amitsureshchandra.orderservice.enums.OrderStatus;
import com.github.amitsureshchandra.orderservice.events.AccountTransactionEvent;
import com.github.amitsureshchandra.orderservice.exception.OrderProcessingException;
import com.github.amitsureshchandra.orderservice.exception.ValidationException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.UUID;

@Service
public class OrderService {
    final RestTemplate restTemplate;

    @Value("${transactions.service.url}")
    String transactionUrl;

    @Value("${catalogs.service.url}")
    String catalogServiceUrl;

    @Value("${products.service.url}")
    String productServiceUrl;

    @Value("${accounts.service.url}")
    String accountServiceUrl;

    private UUID buyerUserId = UUID.fromString("f0a28cb4-175b-436b-92e1-1e937736e616");

    Map<UUID, OrderDto> orders = new HashMap<>();

    final ApplicationEventPublisher applicationEventPublisher;

    public OrderService(RestTemplate restTemplate, ApplicationEventPublisher applicationEventPublisher) {
        this.restTemplate = restTemplate;
        this.applicationEventPublisher = applicationEventPublisher;
    }

    public OrderDto placeOrder(OrderReq orderReq) {

        // verify item in stock
        if(!inStock(orderReq)) {
            throw new ValidationException("product not in stock");
        }

        // verify users has coins
        // pending

        //create distributed transaction
        DistributedTransaction transaction = restTemplate.postForObject(transactionUrl + "/api/v1/transactions",
               new DistributedTransaction(), DistributedTransaction.class);

        System.out.println(transaction);

        UUID orderId = createOrder(orderReq);

        // decrement stocks
        assert transaction != null;
        if(!decrementStocks(orderReq, transaction)) {
            // rollback transaction
            throw new ValidationException("failed to process order");
        }

        // deduct coins
        if(!decrementAmount(orderReq, transaction, orderId)) {
            // rollback transaction
            throw new ValidationException("failed to process order");
        }

        applicationEventPublisher.publishEvent(new AccountTransactionEvent());

        if(new Random().nextInt() % 2 == 0)
            throw new OrderProcessingException("failed manually");

        return orders.get(orderId);
    }

    private boolean decrementAmount(OrderReq orderReq, DistributedTransaction transaction, UUID orderId) {
        System.out.println(" orderReq.getItem().getItemId() : " +  orderReq.getItem().getItemId());
        ProductDto product = restTemplate.getForEntity(productServiceUrl + "/api/v1/products/"+ orderReq.getItem().getItemId(), ProductDto.class).getBody();
        assert product != null;
        double cost = product.getPrice() * orderReq.getItem().getQuantity();
        HttpHeaders headers = new HttpHeaders();
        headers.set("X-Transaction-ID", transaction.getId());
        HttpEntity<?> httpEntity = new HttpEntity<>(headers);
        System.out.println(accountServiceUrl+"/api/v1/users/" + buyerUserId+ "/payment/"+ (int)cost);
        restTemplate.exchange(accountServiceUrl+"/api/v1/users/" + buyerUserId+ "/payment/"+ (int)cost, HttpMethod.PUT, httpEntity,Void.class);
        return true;
    }

    private UUID createOrder(OrderReq orderReq) {
        UUID oid = UUID.randomUUID();

        // order created
        orders.put(oid, new OrderDto(
                oid, orderReq.getItem(), OrderStatus.CREATED
        ));

        return oid;
    }

    private boolean decrementStocks(OrderReq orderReq, DistributedTransaction transaction) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("X-Transaction-ID", transaction.getId());

        HttpEntity<?> httpEntity = new HttpEntity<>(headers);

        try{
            ResponseEntity<Void> res = restTemplate.exchange(catalogServiceUrl + "/api/v1/catalogs/" + orderReq.getItem().getItemId()+"/DECR/" + orderReq.getItem().getQuantity(), HttpMethod.PUT, httpEntity, Void.class);
            if(!res.getStatusCode().is2xxSuccessful()) return false;
        }catch (RestClientException exception) {
            System.out.println(exception.getMessage());
            return false;
        }
        return true;
    }

    private boolean inStock(OrderReq orderReq) {
        ResponseEntity<Integer> resp = restTemplate.exchange(
                catalogServiceUrl + "/api/v1/catalogs/" + orderReq.getItem().getItemId(),
                HttpMethod.GET,
                null,
                Integer.class
        );

        Integer availableStock = resp.getBody();
        System.out.println("availableStock : " + availableStock);

        if(
                !resp.getStatusCode().is2xxSuccessful() ||
                        availableStock == null ||
                        availableStock < orderReq.getItem().getQuantity()
        ) return false;
        return true;
    }

    public Map<UUID, OrderDto> findAll() {
        return orders;
    }
}
