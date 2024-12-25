package com.ecommerce.inventoryService.repository;

import com.ecommerce.inventoryService.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
