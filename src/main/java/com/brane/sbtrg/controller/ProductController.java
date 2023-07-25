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
import com.brane.sbtrg.model.ProductRequest;
import com.brane.sbtrg.model.ProductResponse;
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

	@Operation(summary = "Retrieve a Product by Id", description = "Get a Product object by specifying its id. The response is Product object with id, title, description")
	@ApiResponses({ @ApiResponse(responseCode = "200", content = {
			@Content(schema = @Schema(implementation = ProductResponse.class), mediaType = "application/json") }),
			@ApiResponse(responseCode = "404", content = { @Content(schema = @Schema()) }),
			@ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }) })
	@GetMapping(value = "/{id}")
	ResponseEntity<ProductResponse> getProductById(@PathVariable long id) throws ProductNotFoundException {

		ProductResponse productRes = service.getProductById(id);
		return new ResponseEntity<>(productRes, OK);
	}

	@Operation(summary = "Retrieve List of All Products ", description = "Get List of Product objects. The response is List of Product objects with id, name, description.")
	@ApiResponses({ @ApiResponse(responseCode = "200", content = {
			@Content(schema = @Schema(implementation = ProductRequest.class), mediaType = "application/json") }),
			@ApiResponse(responseCode = "404", content = { @Content(schema = @Schema()) }),
			@ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }) })
	@GetMapping
	ResponseEntity<Iterable<Product>> getAllProducts() {
		return new ResponseEntity<>(service.getAllProducts(), OK);
	}

	@Operation(summary = "Creates a Product ", description = "Create a Product. The response is Product object with id, name, description.")
	@ApiResponses({ @ApiResponse(responseCode = "200", content = {
			@Content(schema = @Schema(implementation = ProductRequest.class), mediaType = "application/json") }),
			@ApiResponse(responseCode = "404", content = { @Content(schema = @Schema()) }),
			@ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }) })
	@PostMapping()
	ResponseEntity<ProductResponse> createProduct(

			@RequestBody ProductRequest product) {
		
			return new ResponseEntity<ProductResponse>(service.createProduct(product), OK);
	}

	@Operation(summary = "Update a Product ", description = "Updates a Product objects using product Id. The response is Updated Product object with id, name, description.")
	@ApiResponses({ @ApiResponse(responseCode = "200", content = {
			@Content(schema = @Schema(implementation = ProductRequest.class), mediaType = "application/json") }),
			@ApiResponse(responseCode = "404", content = { @Content(schema = @Schema()) }),
			@ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }) })
	@PutMapping
	ResponseEntity<Product> updateProduct(@RequestBody Product product) throws ProductNotFoundException {

		return new ResponseEntity<>(service.updateProduct(product), OK);

	}

	@Operation(summary = "Deletes a particular Product ", description = "Deletes a particular Product using a product Id. The response is a Status")
	@ApiResponses({ @ApiResponse(responseCode = "200", content = {
			@Content(schema = @Schema(implementation = ProductRequest.class), mediaType = "application/json") }),
			@ApiResponse(responseCode = "404", content = { @Content(schema = @Schema()) }),
			@ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }) })
	@DeleteMapping(value = "/{id}")
	ResponseEntity<String> deleteProduct(@PathVariable Integer id) throws ProductNotFoundException {
		return new ResponseEntity<>(service.deleteProduct(id), OK);
	}
}
