package com.github.amitsureshchandra.accountservice.controller;

import com.github.amitsureshchandra.accountservice.dto.UserDto;
import com.github.amitsureshchandra.accountservice.service.EventBus;
import com.github.amitsureshchandra.accountservice.service.UserService;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/users")
@EnableDiscoveryClient
public class UserController {

    private final EventBus eventBus;
    private final UserService userService;

    public UserController(UserService userService, EventBus eventBus) {
        this.userService = userService;
        this.eventBus = eventBus;
    }

    @GetMapping
    String ping(){
        return "pong";
    }

    @GetMapping("/{userId}")
    UserDto findById(@PathVariable String userId){
        return userService.findById(userId);
    }

    @PutMapping("/{userId}/payment/{amount}")
    void payment(@PathVariable String userId, @PathVariable Double amount, @RequestHeader("X-Transaction-ID") String transactionId){
        userService.payment(userId, amount, transactionId);
        eventBus.removeEvent(transactionId);
    }

    @PutMapping("/{userId}/withdrawal/{amount}")
    void withdrawal(@PathVariable String userId, @PathVariable Double amount, @RequestHeader("X-Transaction-ID") String transactionId){
        userService.withdraw(userId, amount, transactionId);
        eventBus.removeEvent(transactionId);
    }
}
