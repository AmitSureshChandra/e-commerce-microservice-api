package com.github.amitsureshchandra.catalogservice.service;

import com.github.amitsureshchandra.catalogservice.enums.StockUpdateEnum;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
public class CatalogService {
    Map<UUID, Integer> map = new HashMap<>(){
        {
            put(UUID.fromString("e0138992-6a06-4841-ba74-618f58d000e8"), 3);
        }
    };

    public Integer getStock(UUID prodId) {
        return map.getOrDefault(prodId ,0);
    }

    public void updateStock(UUID prodId, StockUpdateEnum stockUpdateEnum, Integer quantity) {
        if(stockUpdateEnum == StockUpdateEnum.INCR) {
            System.out.println(map.getOrDefault(prodId, 0));
            map.put(prodId, map.getOrDefault(prodId, 0) + quantity);
            return;
        }
        if(map.containsKey(prodId) && map.get(prodId) > quantity)
        {
            map.put(prodId, map.get(prodId) - quantity);
            return;
        }

        throw new RuntimeException("not enough stock");
    }
}
