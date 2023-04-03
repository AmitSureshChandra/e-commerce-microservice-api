package com.github.amitsureshchandra.accountservice.controller;

import com.github.amitsureshchandra.accountservice.dto.UserCreateDto;
import com.github.amitsureshchandra.accountservice.dto.UserDto;
import com.github.amitsureshchandra.accountservice.dto.UserUpdateDto;
import com.github.amitsureshchandra.accountservice.entity.User;
import com.github.amitsureshchandra.accountservice.service.EventBus;
import com.github.amitsureshchandra.accountservice.service.UserService;
import com.github.amitsureshchandra.commonfeature.controller.feature.IBaseCRUController;
import com.github.amitsureshchandra.commonfeature.service.feature.IBaseCRUService;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/users")
@EnableDiscoveryClient
public class UserController implements IBaseCRUController<User, Long, UserCreateDto, UserUpdateDto> {

    private final EventBus eventBus;
    private final UserService userService;

    public UserController(UserService userService, EventBus eventBus) {
        this.userService = userService;
        this.eventBus = eventBus;
    }

//    @GetMapping
//    String ping(){
//        return "pong";
//    }

//    @GetMapping("/{userId}")
//    UserDto findById(@PathVariable String userId){
//        return userService.findById(userId);
//    }

    @PutMapping("/{userId}/payment/{amount}")
    void payment(@PathVariable Long userId, @PathVariable Double amount, @RequestHeader("X-Transaction-ID") String transactionId){
        userService.payment(userId, amount, transactionId);
//        eventBus.removeEvent(transactionId);
    }

    @PutMapping("/{userId}/withdrawal/{amount}")
    void withdrawal(@PathVariable Long userId, @PathVariable Double amount, @RequestHeader("X-Transaction-ID") String transactionId){
        userService.withdraw(userId, amount, transactionId);
//        eventBus.removeEvent(transactionId);
    }

    @Override
    public IBaseCRUService<User, Long, UserCreateDto, UserUpdateDto> getCRUService() {
        return userService;
    }
}
