package com.github.amitsureshchandra.orderservice.repo;

import com.github.amitsureshchandra.orderservice.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepo extends JpaRepository<Order, Long> {
}
