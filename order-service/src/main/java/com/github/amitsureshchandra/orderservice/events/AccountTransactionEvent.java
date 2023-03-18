package com.github.amitsureshchandra.orderservice.events;

import com.github.amitsureshchandra.orderservice.dto.UserDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccountTransactionEvent {
    private String transactionId;
    private UserDto user;
}
