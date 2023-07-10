package com.brane.sbtrg.controller;

import com.brane.sbtrg.entity.Product;
import com.brane.sbtrg.exception.ProductNotFoundException;
import com.brane.sbtrg.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping(value = "/products")
public class ProductController {

	@Autowired
	private ProductService service;

	@GetMapping(value = "/{id}")
	ResponseEntity<Product> getProductById(@PathVariable long id) throws ProductNotFoundException {

		Product product = service.getProductById(id);

		return new ResponseEntity<>(product, OK);
	}

	@GetMapping
	ResponseEntity<Iterable<Product>> getAllProducts() {
		return new ResponseEntity<>(service.getAllProducts(), OK);
	}

	@PostMapping()
	ResponseEntity<Product> createProduct(@RequestBody Product product) {
		return new ResponseEntity<>(service.createProduct(product), OK);
	}

	@PutMapping
	ResponseEntity<Product> updateProduct(@RequestBody Product product) throws ProductNotFoundException {

		return new ResponseEntity<>(service.updateProduct(product), OK);

	}

	@DeleteMapping(value = "/{id}")
	ResponseEntity<String> deleteProduct(@PathVariable Integer id) throws ProductNotFoundException {
		return new ResponseEntity<>(service.deleteProduct(id), OK);
	}
}
