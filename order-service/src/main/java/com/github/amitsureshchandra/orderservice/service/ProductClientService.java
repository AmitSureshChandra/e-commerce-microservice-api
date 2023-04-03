package com.github.amitsureshchandra.orderservice.service;

import com.github.amitsureshchandra.common.dto.order.OrderReq;
import com.github.amitsureshchandra.common.dto.product.ProductDto;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ProductClientService {
    final RestTemplate restTemplate;

    public ProductClientService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public ProductDto getProductDetail(OrderReq orderReq) {
        return restTemplate.getForEntity("http://product-service/products/api/v1/products/"+ orderReq.getItem().getItemId(), ProductDto.class).getBody();
    }
}
