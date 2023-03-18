package com.github.amitsureshchandra.orderservice.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
public class OrderReq {

    @NotNull
    ItemDto item;
}
