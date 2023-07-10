package com.brane.sbtrg.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.brane.sbtrg.entity.Product;

@Repository
public interface ProductRepository extends CrudRepository<Product, Long> {
}
