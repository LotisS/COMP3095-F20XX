package com.lotissacayan.productservice.dto;
import com.lotissacayan.productservice.service.ProductService;

import java.math.BigDecimal;
import java.util.prefs.Preferences;

public record ProductRequest(
        String id,
        String name,
        String description,
        BigDecimal price


) {
    public static Preferences builder() {

        return Preferences.userNodeForPackage(ProductService.class);
    }
}
