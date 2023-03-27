package com.github.amitsureshchandra.catalogservice.repo;

import com.github.amitsureshchandra.catalogservice.entity.ProductStock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PsRepo extends JpaRepository<ProductStock, Long> {
    @Query("SELECT ps.quantity FROM ProductStock ps WHERE ps.productId = :pid")
    Long findQuantityByProductId(Long pid);

    ProductStock findByProductId(Long pid);

    Boolean existsByProductId(Long pid);
}
