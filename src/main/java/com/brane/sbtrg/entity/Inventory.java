package com.brane.sbtrg.entity;

import jakarta.persistence.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.Objects;

@Entity
@Table(name = "INVENTORY")
public class Inventory {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(nullable = false)
  private long id;

  @Column(nullable = false)
  private long count = 0;

  @OneToOne
  private Product product;

  public long getId() {
    return id;
  }

  public Product getProduct() {
    return product;
  }

  public long getCount() {
    return count;
  }

  public Inventory setProduct(final Product product) {
    this.product = product;
    return this;
  }

  @Override
  public boolean equals(final Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    final Inventory inventory = (Inventory) o;
    return id == inventory.id;
  }

  @Override
  public int hashCode() {
    return Objects.hash(id);
  }

  @Override
  public String toString() {
    final StringBuilder sb = new StringBuilder("Inventory{");
    sb.append("id=").append(id);
    sb.append(", product=").append(product);
    sb.append(", count=").append(count);
    sb.append('}');
    return sb.toString();
  }

  public Inventory update(final Inventory inventory) {
    this.count = inventory.count;
    return this;
  }
}
