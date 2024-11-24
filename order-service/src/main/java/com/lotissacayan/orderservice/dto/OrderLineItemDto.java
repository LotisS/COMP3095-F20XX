package com.lotissacayan.orderservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.math.BigDecimal;

public class OrderLineItemDto {

    private String skuCode;
    private BigDecimal price;
    private int quantity;

}
