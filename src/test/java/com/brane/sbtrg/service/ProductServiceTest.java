package com.brane.sbtrg.service;

import com.brane.sbtrg.entity.Product;
import com.brane.sbtrg.exception.ProductNotFoundException;
import com.brane.sbtrg.repository.ProductRepository;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
@DisplayName("Product Service Unit tests")
class ProductServiceTest {

  @InjectMocks
  private ProductService service;
  @Mock
  private ProductRepository repository;

  @Test
  @DisplayName("should get product by id")
  public void shouldGetProductById() throws ProductNotFoundException {
    long productId = 1L;
    Product product = new Product();
    given(repository.findById(productId)).willReturn(Optional.of(product));

    assertEquals(product, service.getProductById(productId));
  }

  @Test
  @DisplayName("should throw exception if product not found for input id")
  void getCategoryByIdException() {
    long productId = 1L;
    given(repository.findById(productId)).willReturn(Optional.empty());

    assertThrows(EntityNotFoundException.class, () -> service.getProductById(1L));
  }
}