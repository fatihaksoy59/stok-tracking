package com.faksoy.stocktracking.repository;

import com.faksoy.stocktracking.entity.Product;
import com.faksoy.stocktracking.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    Product findByName(String productName);
    List<Product> findByUser(User user);
}
