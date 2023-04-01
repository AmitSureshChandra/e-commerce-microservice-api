package com.github.amitsureshchandra.orderservice.entity;

import com.github.amitsureshchandra.orderservice.dto.ItemDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.envers.Audited;

import javax.persistence.*;

@Entity @Getter @Setter @NoArgsConstructor @Audited
public class OrderItem {
    @Id @GeneratedValue
    private Long id;

    private Long itemId;
    private Integer quantity;
    private Double amount;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id", referencedColumnName = "id")
    private Order order;

    public OrderItem(ItemDto item, Double amount) {
        this.itemId = item.getItemId();
        this.quantity = item.getQuantity();
        this.amount = amount;
    }
}
