package com.github.amitsureshchandra.productservice.controller;

import com.github.amitsureshchandra.commonfeature.controller.feature.IBaseCRUController;
import com.github.amitsureshchandra.commonfeature.service.feature.IBaseCRUService;
import com.github.amitsureshchandra.productservice.dto.ProductCreateDto;
import com.github.amitsureshchandra.productservice.dto.ProductDto;
import com.github.amitsureshchandra.productservice.dto.ProductUpdateDto;
import com.github.amitsureshchandra.productservice.entity.Product;
import com.github.amitsureshchandra.productservice.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/products")
@EnableDiscoveryClient
public class ProductController implements IBaseCRUController<Product, Long, ProductCreateDto, ProductUpdateDto> {

    final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

//    @GetMapping("/{prodId}")
//    ProductDto getProduct(@PathVariable UUID prodId){
//        return productService.getById(prodId);
//    }

    @GetMapping("/search/{slug}")
    List<ProductDto> searchProduct(@PathVariable String slug){
        return productService.searchProducts(slug);
    }

    @Override
    public IBaseCRUService<Product, Long, ProductCreateDto, ProductUpdateDto> getCRUService() {
        return productService;
    }
}
