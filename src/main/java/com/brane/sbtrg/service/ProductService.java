package com.brane.sbtrg.service;

import static java.lang.String.format;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.brane.sbtrg.entity.Product;
import com.brane.sbtrg.exception.ProductNotFoundException;
import com.brane.sbtrg.model.ProductRequest;
import com.brane.sbtrg.model.ProductResponse;
import com.brane.sbtrg.repository.ProductRepository;

@Service
public class ProductService {

	@Autowired
	private ProductRepository repository;

	public ProductResponse getProductById(long id) throws ProductNotFoundException {

		Product product = repository.findById(id)
				.orElseThrow(() -> new ProductNotFoundException(format("Product: %d does not exist", id)));

		ProductResponse res = new ProductResponse();

		res.setId(String.valueOf(product.getId()));
		res.setName(product.getName());
		res.setDescription(product.getDescription());

		return res;
	}

	public Iterable<Product> getAllProducts() {
		return repository.findAll();
	}

	public ProductResponse createProduct(ProductRequest product) {

		Product productEntity = new Product(product.getName(), product.getDescription());

		Product productretEntity = repository.save(productEntity);

		ProductResponse res = new ProductResponse();

		res.setId(String.valueOf(productretEntity.getId()));
		res.setName(productretEntity.getName());
		res.setDescription(productretEntity.getDescription());

		return res;
	}

	public Product updateProduct(Product product) throws ProductNotFoundException {

		Product savedProduct = repository.findById(product.getId())
				.orElseThrow(() -> new ProductNotFoundException(format("Product: %d does not exist", product.getId())));

		savedProduct.update(product);

		return repository.save(savedProduct);
	}

	public String deleteProduct(long id) throws ProductNotFoundException {

		Product savedProduct = repository.findById(id)
				.orElseThrow(() -> new ProductNotFoundException(format("Product: %d does not exist", id)));

		repository.delete(savedProduct);
		return format("Product: %d Successfully deleted", id);
	}
}
