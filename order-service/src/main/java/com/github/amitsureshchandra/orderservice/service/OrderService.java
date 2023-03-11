package com.github.amitsureshchandra.orderservice.service;

import com.github.amitsureshchandra.orderservice.dto.OrderDto;
import com.github.amitsureshchandra.orderservice.dto.OrderReq;
import com.github.amitsureshchandra.orderservice.enums.OrderStatus;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
public class OrderService {

    Map<UUID, OrderDto> orders = new HashMap<>();

    public OrderDto placeOrder(OrderReq orderReq) {
        UUID oid = UUID.randomUUID();
        return orders.put(oid, new OrderDto(
                oid, orderReq.getItems(), OrderStatus.CREATED
        ));
    }

    public Map<UUID, OrderDto> findAll() {
        return orders;
    }
}
