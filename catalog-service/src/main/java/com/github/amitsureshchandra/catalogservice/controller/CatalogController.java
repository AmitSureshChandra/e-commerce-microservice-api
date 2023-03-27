package com.github.amitsureshchandra.catalogservice.controller;

import com.github.amitsureshchandra.catalogservice.enums.StockUpdateEnum;
import com.github.amitsureshchandra.catalogservice.service.CatalogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/catalogs")
@EnableDiscoveryClient
public class CatalogController {

    private final CatalogService catalogService;

    public CatalogController(CatalogService catalogService) {
        this.catalogService = catalogService;
    }

    @GetMapping
    String ping(){
        return "pong";
    }

    @PostMapping("/{prodId}/{quantity}")
    void addStock(@PathVariable Long prodId, @PathVariable Long quantity) {
        catalogService.addStock(prodId, quantity);
    }

    @GetMapping("/{prodId}")
    Integer getStock(@PathVariable Long prodId){
        return catalogService.getStock(prodId).intValue();
    }

    @PutMapping("/{prodId}/{type}/{quantity}")
    void updateStock(@PathVariable Long prodId, @PathVariable StockUpdateEnum type,@PathVariable Integer quantity ){
        catalogService.updateStock(prodId, type, quantity);
    }
}
