package com.github.amitsureshchandra.orderservice.dto;

import com.github.amitsureshchandra.orderservice.enums.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDto {
    private UUID id;
    private ItemDto itemDto;

    private OrderStatus status;
}
