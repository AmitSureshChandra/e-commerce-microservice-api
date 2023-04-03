package com.github.amitsureshchandra.orderservice.events;

import com.github.amitsureshchandra.common.dto.account.UserDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderTransactionEvent {
    private Long transactionId;
    private UserDto user;
}
