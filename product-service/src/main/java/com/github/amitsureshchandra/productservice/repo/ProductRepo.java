package com.github.amitsureshchandra.productservice.repo;

import com.github.amitsureshchandra.productservice.dto.ProductDto;
import com.github.amitsureshchandra.productservice.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductRepo extends JpaRepository<Product, Long> {
    @Query("SELECT new com.github.amitsureshchandra.productservice.dto.ProductDto(p) FROM Product p WHERE LOWER(p.name) LIKE %:slug% OR LOWER(p.desc) LIKE %:slug%")
    List<ProductDto> searchByNameOrDesc(String slug);
}
