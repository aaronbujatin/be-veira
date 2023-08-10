package com.aaronbujatin.beveira.repository;

import com.aaronbujatin.beveira.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findByCatalogContainingIgnoreCase(String catalog);
}
