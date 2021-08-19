package com.trankhaihung.ecommerce.repository;

import com.trankhaihung.ecommerce.entity.CartItem;
import com.trankhaihung.ecommerce.entity.Customer;
import com.trankhaihung.ecommerce.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Long> {
    public List<CartItem> findByCustomer(Customer customer);

    public CartItem findByCustomerAndProduct(Customer customer, Product product);

    public CartItem findByProduct(Product product);
}
