package com.github.amitsureshchandra.catalogservice.service;

import com.github.amitsureshchandra.catalogservice.entity.ProductStock;
import com.github.amitsureshchandra.catalogservice.enums.StockUpdateEnum;
import com.github.amitsureshchandra.catalogservice.exception.ValidationException;
import com.github.amitsureshchandra.catalogservice.repo.PsRepo;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class CatalogService {
    final PsRepo psRepo;

    public CatalogService(PsRepo psRepo) {
        this.psRepo = psRepo;
    }

    public Long getStock(Long prodId) {
        return psRepo.findQuantityByProductId(prodId);
    }

    ProductStock getPSByProdId(Long pid) {
        return psRepo.findByProductId(pid);
    }

    public void updateStock(Long prodId, StockUpdateEnum stockUpdateEnum, Integer quantity) {
        ProductStock ps = getPSByProdId(prodId);
        if(stockUpdateEnum == StockUpdateEnum.INCR) {
            ps.setQuantity(ps.getQuantity() + quantity);
            psRepo.save(ps);
            return;
        }
        if(ps != null && ps.getQuantity() >= quantity)
        {
            ps.setQuantity(ps.getQuantity() - quantity);
            psRepo.save(ps);
            return;
        }

        throw new ValidationException("not enough stock");
    }

    public void addStock(Long prodId, Long quantity) {
        // check if stock not exists

        if(psRepo.existsByProductId(prodId)) throw new ValidationException("already exists");

        // save
        ProductStock ps = new ProductStock();
        ps.setProductId(prodId);
        ps.setQuantity(quantity);
        psRepo.save(ps);
    }
}
