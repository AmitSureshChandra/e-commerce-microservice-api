package com.github.amitsureshchandra.orderservice.entity;

import com.github.amitsureshchandra.common.enums.OrderStatus;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.envers.Audited;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity @Getter @Setter @Audited @Table(name = "orders")
public class Order {
    @Id @GeneratedValue
    private Long id;

    @OneToMany(mappedBy = "order", fetch = FetchType.EAGER, orphanRemoval = true, cascade = CascadeType.ALL)
    List<OrderItem> items = new ArrayList<>();

    private OrderStatus status;
    private Double amount;

    public void addItems(List<OrderItem> items) {
        items.forEach(item -> item.setOrder(this));
        this.items.addAll(items);
    }
}
