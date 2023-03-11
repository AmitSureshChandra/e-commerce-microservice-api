package com.github.amitsureshchandra.orderservice.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class OrderReq {
    @NotEmpty
    private List<ItemDto> items;
}
