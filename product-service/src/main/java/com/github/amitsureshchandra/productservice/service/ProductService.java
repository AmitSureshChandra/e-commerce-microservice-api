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

    public ProductService(ProductRepo productRepo, ApplicationContext applicationContext) {
        this.productRepo = productRepo;
        this.applicationContext = applicationContext;
    }

    public List<ProductDto> searchProducts(String slug) {
        return productRepo.searchByNameOrDesc(slug.toLowerCase());
    }

    @Override
    public ApplicationContext getAppContext() {
        return applicationContext;
    }

    @Override
    public JpaRepository<Product, Long> getRepo() {
        return productRepo;
    }
}
