package com.brane.sbtrg.service;

import static java.lang.String.format;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.brane.sbtrg.entity.Product;
import com.brane.sbtrg.exception.ProductNotFoundException;
import com.brane.sbtrg.repository.ProductRepository;

@Service
public class ProductService {

  @Autowired
  private ProductRepository repository;

  public Product getProductById(long id) throws ProductNotFoundException {
    return repository
      .findById(id)
      .orElseThrow(() -> new ProductNotFoundException(format("Product: %d does not exist", id)));
  }

  public Iterable<Product> getAllProducts() {
    return repository.findAll();
  }

  public Product createProduct(Product Product) {
    return repository.save(Product);
  }

  public Product updateProduct(Product product) throws ProductNotFoundException{
    Product savedProduct = getProductById(product.getId());
    savedProduct.update(product);
    return repository.save(savedProduct);
  }

  public String deleteProduct(int id) throws ProductNotFoundException{
    repository.delete(getProductById(id));
    return format("Product: %d Successfully deleted", id);
  }
}
