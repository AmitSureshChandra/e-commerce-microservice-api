package com.github.amitsureshchandra.productservice.service;

import com.github.amitsureshchandra.commonfeature.service.feature.IBaseCRUService;
import com.github.amitsureshchandra.productservice.dto.ProductCreateDto;
import com.github.amitsureshchandra.productservice.dto.ProductDto;
import com.github.amitsureshchandra.productservice.dto.ProductUpdateDto;
import com.github.amitsureshchandra.productservice.entity.Product;
import com.github.amitsureshchandra.productservice.repo.ProductRepo;
import org.springframework.context.ApplicationContext;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService implements IBaseCRUService<Product, Long, ProductCreateDto, ProductUpdateDto> {

    final ProductRepo productRepo;

    final ApplicationContext applicationContext;

//    Map<String, ProductDto> map = Map.of(
//            "laptop", new ProductDto(
//                    UUID.fromString("e0138992-6a06-4841-ba74-618f58d000e8"),
//                    "Asus Laptop",
//                    "Intel i3 12th gen, 1220p processor, 512 SSD, 8 GB ram",
//                    "laptop",
//                    UUID.fromString("920245ce-21e8-4e3b-8055-dc104fd5c0c8"),
//                    15d
//            )
//    );

    public ProductService(ProductRepo productRepo, ApplicationContext applicationContext) {
        this.productRepo = productRepo;
        this.applicationContext = applicationContext;
    }

    public List<ProductDto> searchProducts(String slug) {
//        if(!map.containsKey(slug)) return List.of(map.get("laptop"));
//        // search & return the product
//        return List.of(map.get(slug));
        return productRepo.searchByNameOrDesc(slug.toLowerCase());
    }

//    public ProductDto getById(UUID prodId) {
//        return map.get("laptop");
//    }

    @Override
    public ApplicationContext getAppContext() {
        return applicationContext;
    }

    @Override
    public JpaRepository<Product, Long> getRepo() {
        return productRepo;
    }
}
