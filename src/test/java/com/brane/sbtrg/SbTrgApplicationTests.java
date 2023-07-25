package com.brane.sbtrg;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import com.brane.sbtrg.entity.Product;
import com.brane.sbtrg.model.ProductRequest;
import com.brane.sbtrg.model.ProductResponse;
import com.brane.sbtrg.service.ProductService;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY)
@DisplayName("Product application tests")
public class SbTrgApplicationTests {

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private ProductService productService;

    @Test
    @DisplayName("should return application status as up")
    public void contextLoads() {
    }

    @Test
    @DisplayName("should return product by input id")
    public void getProductById() {
    	
        
        ProductRequest req = new ProductRequest();
        req.setName("Product");
        req.setDescription("Product Description");
        
        
        final ProductResponse savedProduct = productService.createProduct(req);

        ResponseEntity<Product> response = this.restTemplate.exchange("/products/" + savedProduct.getId(),
                HttpMethod.GET,
                null,
                Product.class);

        assertEquals(savedProduct, response.getBody());
    }
}