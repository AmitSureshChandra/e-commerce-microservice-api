package com.github.amitsureshchandra.orderservice.controller;

import com.github.amitsureshchandra.orderservice.dto.OrderDto;
import com.github.amitsureshchandra.orderservice.dto.OrderReq;
import com.github.amitsureshchandra.orderservice.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.validation.Valid;
import java.util.Map;
import java.util.UUID;

@RequestMapping("/api/v1/orders")
@RestController
public class OrderController {

    @Autowired
    RestTemplate restTemplate;

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/test")
    String test(){
        return  restTemplate.exchange("http://catalog-service/catalogs/api/v1/catalogs", HttpMethod.GET, null, String.class).getBody();
    }

    @PostMapping
    OrderDto createOrder(@Valid @RequestBody OrderReq orderReq) {
        return orderService.placeOrder(orderReq);
    }

    @GetMapping
    Map<UUID, OrderDto> getAllOrders() {
        return orderService.findAll();
    }
}
