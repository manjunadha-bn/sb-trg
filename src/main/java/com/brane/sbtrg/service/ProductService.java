package com.brane.sbtrg.service;

import com.brane.sbtrg.entity.Product;
import com.brane.sbtrg.repository.ProductRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static java.lang.String.format;

@Service
public class ProductService {

  @Autowired
  private ProductRepository repository;

  public Product getProductById(long id) {
    return repository
      .findById(id)
      .orElseThrow(() -> new EntityNotFoundException(format("Product: %d does not exist", id)));
  }

  public Iterable<Product> getAllProducts() {
    return repository.findAll();
  }

  public Product createProduct(Product Product) {
    return repository.save(Product);
  }

  public Product updateProduct(Product product) {
    Product savedProduct = getProductById(product.getId());
    savedProduct.update(product);
    return repository.save(savedProduct);
  }

  public String deleteProduct(int id) {
    repository.delete(getProductById(id));
    return format("Product: %d Successfully deleted", id);
  }
}
