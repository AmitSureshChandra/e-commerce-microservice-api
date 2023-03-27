package com.github.amitsureshchandra.productservice.dto;

import com.github.amitsureshchandra.productservice.entity.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDto {
    private Long id;
    private String name;
    private String desc;
    private String category;

    private UUID sellerId;
    private Double price;

    public ProductDto(Product product){
        this.id = product.getId();
        this.name = product.getName();
        this.desc = product.getDesc();
        this.category = product.getCategory();
        this.sellerId = product.getSellerId();
        this.price = product.getPrice();
    }
}
