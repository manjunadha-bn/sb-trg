package com.brane.sbtrg.controller;

import static org.springframework.http.HttpStatus.OK;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.brane.sbtrg.entity.Product;
import com.brane.sbtrg.exception.ProductNotFoundException;
import com.brane.sbtrg.request.ProductRequest;
import com.brane.sbtrg.service.ProductService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@Tag(name = "Products", description = "Products management APIs")
@RequestMapping(value = "/products")
public class ProductController {

	@Autowired
	private ProductService service;

	
	
	@Operation(
		      summary = "Retrieve a Products by Id",
		      description = "Get a Product object by specifying its id. The response is Product object with id, title, description and published status.",
		      tags = { "products", "get" })
		  @ApiResponses({
		      @ApiResponse(responseCode = "200", content = { @Content(schema = @Schema(implementation = ProductRequest.class), mediaType = "application/json") }),
		      @ApiResponse(responseCode = "404", content = { @Content(schema = @Schema()) }),
		      @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }) })
	
	
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
