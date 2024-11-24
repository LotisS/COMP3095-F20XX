package com.lotissacayan.orderservice.service;

import com.lotissacayan.orderservice.dto.OrderRequest;
import com.lotissacayan.orderservice.model.Order;
import com.lotissacayan.orderservice.model.OrderLineItem;
import com.lotissacayan.orderservice.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;


@Service
@Transactional
@RequiredArgsConstructor
public class OrderService {


    private final OrderRepository orderRepository;

    public String placeOrder(OrderRequest orderRequest){
        Order order = new Order();
        order.setOrderNumber(UUID.randomUUID().toString());

        orderRepository.save(order);
        return "Order placed Succesfully!";
    }

}
