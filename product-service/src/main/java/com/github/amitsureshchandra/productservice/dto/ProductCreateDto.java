package com.github.amitsureshchandra.productservice.dto;

import com.sun.istack.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import java.util.UUID;

@Data @NoArgsConstructor(force = true)
public class ProductCreateDto {

    @NotNull @NotBlank
    private String name;
    private String desc;

    @NotNull @NotBlank
    private String category;

    @NotNull
    private Long sellerId;

    @Positive
    private Double price;
}
