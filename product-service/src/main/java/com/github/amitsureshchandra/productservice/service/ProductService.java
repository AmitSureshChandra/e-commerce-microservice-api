package com.github.amitsureshchandra.productservice.service;

import com.github.amitsureshchandra.productservice.dto.ProductDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
public class ProductService {
    Map<String, ProductDto> map = Map.of(
            "laptop", new ProductDto(
                    UUID.fromString("e0138992-6a06-4841-ba74-618f58d000e8"),
                    "Asus Laptop",
                    "Intel i3 12th gen, 1220p processor, 512 SSD, 8 GB ram",
                    "laptop",
                    UUID.fromString("920245ce-21e8-4e3b-8055-dc104fd5c0c8"),
                    15d
            )
    );

    public List<ProductDto> searchProducts(String slug) {
        if(!map.containsKey(slug)) return List.of(map.get("laptop"));
        // search & return the product
        return List.of(map.get(slug));
    }

    public ProductDto getById(UUID prodId) {
        return map.get("laptop");
    }
}
