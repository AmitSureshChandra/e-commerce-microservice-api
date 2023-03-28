package com.github.amitsureshchandra.accountservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data @NoArgsConstructor @AllArgsConstructor
public class UserCreateDto {
    @NotNull @NotBlank
    private String username;

    @NotNull @NotBlank
    private String email;
    private String city;
    private String state;
    private String country;

    @NotNull @NotBlank
    private String pincode;
}
