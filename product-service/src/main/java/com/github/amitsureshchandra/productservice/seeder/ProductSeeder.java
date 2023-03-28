package com.github.amitsureshchandra.productservice.seeder;

import com.github.amitsureshchandra.productservice.entity.Product;
import com.github.amitsureshchandra.productservice.repo.ProductRepo;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.UUID;

@Service
public class ProductSeeder {

    final ProductRepo productRepo;

    public ProductSeeder(ProductRepo productRepo) {
        this.productRepo = productRepo;
    }

    @PostConstruct
    void init(){
        seed();
    }

    private void seed() {
        if(productRepo.count() > 0) return;
        Product laptop = new Product(
                null,
                "Asus Laptop",
                "Intel i3 12th gen, 1220p processor, 512 SSD, 8 GB ram",
                "laptop",
                UUID.fromString("920245ce-21e8-4e3b-8055-dc104fd5c0c8"),
                15d
        );

        productRepo.save(laptop);
    }
}
