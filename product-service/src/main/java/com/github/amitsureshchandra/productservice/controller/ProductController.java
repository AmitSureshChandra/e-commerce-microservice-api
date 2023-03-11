package com.github.amitsureshchandra.productservice.controller;

import com.github.amitsureshchandra.productservice.dto.ProductDto;
import com.github.amitsureshchandra.productservice.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
public class ProductController {

    final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/{slug}")
    List<ProductDto> searchProduct(@PathVariable String slug){
        return productService.searchProducts(slug);
    }
}
