package com.github.amitsureshchandra.orderservice.dto;

import com.github.amitsureshchandra.orderservice.entity.Order;
import com.github.amitsureshchandra.orderservice.enums.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDto {
    private Long id;
    private ItemDto itemDto;
    private OrderStatus status;

    public OrderDto(Order o, OrderReq orderReq) {
        this.id = o.getId();
        this.status = o.getStatus();
        this.itemDto = orderReq.getItem();
    }
}
