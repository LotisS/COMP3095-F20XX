package com.lotissacayan.orderservice.controller;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "orders")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String orderNumber;

    @OneToMany(cascade =CascadeTYPE.ALL, fetch = fetchType.LAZY)
    @JoinColun(name = "order_id")
    private List<OrderLineItem> orderLineItems;

}
