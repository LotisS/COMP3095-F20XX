package com.lotissacayan.productservice.service;

import com.lotissacayan.productservice.dto.ProductRequest;
import com.lotissacayan.productservice.dto.ProductResponse;
import com.lotissacayan.productservice.model.Product;
import com.lotissacayan.productservice.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
@Slf4j
public class ProductService {
    private final ProductRepository productRepository;

    public ProductResponse createProduct(ProductRequest productRequest) {
        Product product = Product.builder()
                .id(productRequest.id())
                .name(productRequest.name())
                .description(productRequest.description())
                .price(productRequest.price())
                .build();

        productRepository.save(product);
        log.info("Product {} has been created", product.getId());


        return new ProductResponse(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getPrice()
        );
    }


    @GetMapping
    public List<ProductResponse> getAllProducts(){
        List<Product> products = productRepository.findAll();

        if(products.isEmpty()) {
            log.info("No products found in database.");
        }
        return products.stream()
                .map(product -> new ProductResponse(
                        product.getId(),
                        product.getName(),
                        product.getDescription(),
                        product.getPrice()
                ))
                .collect(Collectors.toList());
    }

    public void updateProduct(String id, ProductRequest productRequest) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Product ID " + id + " not found"));

        product.setName(productRequest.name());
        product.setDescription(productRequest.description());
        product.setPrice(productRequest.price());

        productRepository.save(product);




        log.info("Product {} has been updated", product.getId());
    }

    public void deleteProduct(String id) {
        if(!productRepository.existsById(id)) {
            throw  new IllegalArgumentException(("Product with ID" + id + " not found"));

        }

        productRepository.deleteById(id);
        log.info("Product  ID {} has been deleted ", id);
    }



}
