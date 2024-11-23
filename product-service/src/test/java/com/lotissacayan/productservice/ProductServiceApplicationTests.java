package com.lotissacayan.productservice;

import com.lotissacayan.productservice.dto.ProductRequest;
import org.junit.Before;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.ResultMatcher;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.shaded.com.fasterxml.jackson.databind.ObjectMapper;

import java.math.BigDecimal;

import static io.restassured.RestAssured.*;
import static jdk.javadoc.internal.doclets.toolkit.util.DocPath.empty;
import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Testcontainers
class ProductServiceApplicationTests {

    @Container
    static MongoDBContainer mongoDBContainer = new com.lotissacayan.productservice.MongoDBContainer("mongo:6.0");

    @DynamicPropertySource
    static void setProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.data.mongodb.uri", mongoDBContainer::getReplicaSetUrl);
    }

    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    void SetUp() {

    }
    @Test
    void shouldCreateProduct() {

        String productRequest = """
                {
                    "id": "1",
                    "name": "Test Product",
                    "description": "This is a test product",
                    "price": 99.99
                }
                """;

        given()
                .header("Content-Type", "application/json")
                .body(productRequest)
                .when()
                .post("/api/product")
                .then()
                .statusCode(201);
    }

    @Test
    void shouldGetAllProducts()  throws Exception {

        ResultActions response = mockMvc.perform((RequestBuilder) get("/api/product"))
                .andExpect(status().isOk())
                .andExpect((ResultMatcher) jsonPath("$", not(empty())));
    }

    private String asJsonString(Object obj) {
        try{
            return new com.fasterxml.jackson.databind.ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}