package com.trankhaihung.ecommerce.repository;

import com.trankhaihung.ecommerce.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    Product getById(Long id);
}
