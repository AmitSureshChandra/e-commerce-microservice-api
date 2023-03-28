package com.github.amitsureshchandra.accountservice.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity @Table(name = "users")
@Data @NoArgsConstructor
public class User {
    @Id @GeneratedValue
    private Long id;

    private String username;
    private Double balance;
    private String email;
    private String city;
    private String state;
    private String country;
    private String pincode;
}
