package com.ja.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.ja.entity.Product;

/**
 * Repository interface for managing {@link Product} entities.
 * Extends {@link JpaRepository} to provide CRUD operations and additional query methods.
 */
public interface ProductRepository extends JpaRepository<Product, Long> {
    // No additional methods are defined here; JpaRepository provides basic CRUD operations.
    // Additional query methods can be defined following Spring Data JPA naming conventions if needed.
}
