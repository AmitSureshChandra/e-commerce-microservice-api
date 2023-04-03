package com.github.amitsureshchandra.accountservice.events;

import com.github.amitsureshchandra.accountservice.dto.UserDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccountTransactionEvent {
    private Long transactionId;
    private UserDto user;
}
