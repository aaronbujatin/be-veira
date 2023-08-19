package com.aaronbujatin.beveira.repository;

import com.aaronbujatin.beveira.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
