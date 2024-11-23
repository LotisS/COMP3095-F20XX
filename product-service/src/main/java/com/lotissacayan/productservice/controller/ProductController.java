package com.lotissacayan.productservice.controller;

import com.lotissacayan.productservice.dto.ProductRequest;
import com.lotissacayan.productservice.dto.ProductResponse;
import com.lotissacayan.productservice.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/product")
@RequiredArgsConstructor

public class ProductController {

    private final ProductService productService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProductResponse createProduct(@RequestBody ProductRequest productRequest) {
        productService.createProduct(productRequest);
    }
    @GetMapping
    public List<ProductResponse> getAllProducts(){
        productService.getAllProducts();
    }

    @PutMapping("/{id}")
    public void updateProduct(@PathVariable String id, @RequestBody ProductRequest productRequest) {
        productService.updateProduct(id, productRequest);

    }

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable String id) {
        productService.deleteProduct(id);
    }

}
