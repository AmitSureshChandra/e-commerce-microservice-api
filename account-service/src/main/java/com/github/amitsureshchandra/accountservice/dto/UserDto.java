package com.github.amitsureshchandra.accountservice.dto;

import com.github.amitsureshchandra.accountservice.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    private String username;
    private Double balance;

    public UserDto(User u) {
        this.username = u.getUsername();
        this.balance = u.getBalance();
    }
}
