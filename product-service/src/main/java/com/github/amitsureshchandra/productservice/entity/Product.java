package com.github.amitsureshchandra.productservice.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "products") @NoArgsConstructor
@Getter @Setter @AllArgsConstructor
public class Product {
    @Id @GeneratedValue
    private Long id;

    private String name;

    @Column(name = "description")
    private String desc;
    private String category;

    private UUID sellerId;
    private Double price;
}
