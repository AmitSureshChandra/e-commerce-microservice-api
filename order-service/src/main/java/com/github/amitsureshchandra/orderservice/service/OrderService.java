package com.github.amitsureshchandra.orderservice.service;

import com.github.amitsureshchandra.orderservice.dto.*;
import com.github.amitsureshchandra.orderservice.entity.Order;
import com.github.amitsureshchandra.orderservice.entity.OrderItem;
import com.github.amitsureshchandra.orderservice.enums.OrderStatus;
import com.github.amitsureshchandra.orderservice.exception.ValidationException;
import com.github.amitsureshchandra.orderservice.repo.OrderRepo;
import org.modelmapper.ModelMapper;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Service
public class OrderService {
    final CatalogClientService catalogClientService;
    final AccountClientService accountClientService;
    final ProductClientService productClientService;

    final TransactionClientService transactionClientService;
    final OrderRepo orderRepo;
    final ModelMapper modelMapper;
    final RestTemplate restTemplate;

    private final Long buyerUserId = 1l;

    Map<UUID, OrderDto> orders = new HashMap<>();

    final ApplicationEventPublisher applicationEventPublisher;

    public OrderService(CatalogClientService catalogClientService, AccountClientService accountClientService, ProductClientService productClientService, TransactionClientService transactionClientService, OrderRepo orderRepo, ModelMapper modelMapper, RestTemplate restTemplate, ApplicationEventPublisher applicationEventPublisher) {
        this.catalogClientService = catalogClientService;
        this.accountClientService = accountClientService;
        this.productClientService = productClientService;
        this.transactionClientService = transactionClientService;
        this.orderRepo = orderRepo;
        this.modelMapper = modelMapper;
        this.restTemplate = restTemplate;
        this.applicationEventPublisher = applicationEventPublisher;
    }

    public OrderDto placeOrder(OrderReq orderReq) {

        // verify item in stock
        if(!catalogClientService.checkStock(orderReq)) {
            throw new ValidationException("product not in stock");
        }

        // verify users has coins
        // pending

        //create distributed transaction
        DistributedTransaction transaction = transactionClientService.createTransaction();

        // decrement stocks
        assert transaction != null;

        if(!catalogClientService.decrementStocks(orderReq, transaction)) {
            // rollback transaction
            throw new ValidationException("failed to process order");
        }

        ProductDto product = productClientService.getProductDetail(orderReq);
        assert product != null;

        double cost = product.getPrice() * orderReq.getItem().getQuantity();

        // deduct coins
        if(!accountClientService.decrementAmount(orderReq, transaction, product, cost, buyerUserId)) {
            // rollback transaction
            throw new ValidationException("failed to process order");
        }

//        applicationEventPublisher.publishEvent(new AccountTransactionEvent());

//        if(new Random().nextInt() % 2 == 0)
//            throw new OrderProcessingException("failed manually");

        // order created
        Order order = createOrder(orderReq, cost);

        return new OrderDto(order, orderReq);
    }

    private Order createOrder(OrderReq orderReq, double cost) {

        Order order = new Order();
        order.setAmount(cost);
        order.setStatus(OrderStatus.CREATED);

        List<OrderItem> items = new ArrayList<>();

        OrderItem orderItem = new OrderItem(orderReq.getItem(), cost);
        items.add(orderItem);
        order.addItems(items);
        return  orderRepo.save(order);
    }

    public Map<UUID, OrderDto> findAll() {
        return orders;
    }
}
