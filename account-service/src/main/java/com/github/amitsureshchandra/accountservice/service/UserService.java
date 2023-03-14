package com.github.amitsureshchandra.accountservice.service;

import com.github.amitsureshchandra.accountservice.events.AccountTransactionEvent;
import com.github.amitsureshchandra.accountservice.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class UserService {

    @Autowired
    ApplicationEventPublisher eventPublisher;

    Map<String, UserDto> users = new HashMap<String, UserDto>(){{
        put("920245ce-21e8-4e3b-8055-dc104fd5c0c8", new UserDto("seller", 0d));
        put("f0a28cb4-175b-436b-92e1-1e937736e616", new UserDto("buyer", 1000d));
    }};

    public UserDto findById(String userId) {
        return users.get(userId);
    }

    public void payment(String userId, Double amount, String transactionId) {
        transfer(userId, amount, transactionId);
    }

    public void withdraw(String userId, Double amount, String transactionId) {
        transfer(userId, (-1) * amount, transactionId);
    }

    private void transfer(String userId, Double amount, String transactionId){
        UserDto userDto = findById(userId);
        userDto.setBalance(userDto.getBalance() + amount);
        eventPublisher.publishEvent(new AccountTransactionEvent(transactionId, userDto));
    }
}
