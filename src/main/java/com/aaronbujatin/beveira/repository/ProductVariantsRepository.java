package com.aaronbujatin.beveira.repository;

import com.aaronbujatin.beveira.entity.ProductVariants;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductVariantsRepository extends JpaRepository<ProductVariants, Long> {
}
