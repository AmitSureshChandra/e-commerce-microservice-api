package com.github.amitsureshchandra.catalogservice.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity @Data
@NoArgsConstructor
public class ProductStock {
    @Id @GeneratedValue
    private Long id;

    private Long productId;
    private Long quantity;
}
