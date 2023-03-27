package com.github.amitsureshchandra.productservice.dto;

import com.sun.istack.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import java.util.UUID;

@Data
@NoArgsConstructor
public class ProductUpdateDto {
    @NotNull
    @NotBlank
    private String name;
    private String desc;

    @NotNull @NotBlank
    private String category;

    @NotNull @NotBlank
    private UUID sellerId;

    @Positive
    private Double price;
}
