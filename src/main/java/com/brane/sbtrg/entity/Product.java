package com.brane.sbtrg.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Product {
    private long id;
    private String name;
    private String description;

    public Product update(final Product product) {
        this.name = product.name;
        this.description = product.description;
        return this;
    }
}