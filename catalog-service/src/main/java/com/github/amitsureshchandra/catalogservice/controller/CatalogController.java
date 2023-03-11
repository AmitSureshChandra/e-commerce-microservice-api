package com.github.amitsureshchandra.catalogservice.controller;

import com.github.amitsureshchandra.catalogservice.enums.StockUpdateEnum;
import com.github.amitsureshchandra.catalogservice.service.CatalogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/catalogs")
public class CatalogController {

    private final CatalogService catalogService;

    public CatalogController(CatalogService catalogService) {
        this.catalogService = catalogService;
    }

    @GetMapping("/{prodId}")
    Integer getStock(@PathVariable UUID prodId){
        return catalogService.getStock(prodId);
    }

    @PutMapping("/{prodId}/{type}/{quantity}")
    void updateStock(@PathVariable UUID prodId, @PathVariable StockUpdateEnum type,@PathVariable Integer quantity ){
        catalogService.updateStock(prodId, type, quantity);
    }
}
