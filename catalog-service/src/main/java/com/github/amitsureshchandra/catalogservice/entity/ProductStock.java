package com.github.amitsureshchandra.catalogservice.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.envers.Audited;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity @Data
@NoArgsConstructor @Audited
public class ProductStock {
    @Id @GeneratedValue
    private Long id;

    private Long productId;
    private Long quantity;
}
