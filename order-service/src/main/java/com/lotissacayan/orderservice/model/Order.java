package com.lotissacayan.orderservice.model;

import com.lotissacayan.orderservice.controller.OrderLineItem;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name ="orders")
@Data@NoArgsConstructor
@AllArgsConstructor


public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String orderNumber;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "order_id", referencedColumnName = "id")
    private List<OrderLineItem> orderLineItems;

    public static Object builder() {
    }
}
