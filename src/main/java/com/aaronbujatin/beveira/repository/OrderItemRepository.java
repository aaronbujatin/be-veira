package com.aaronbujatin.beveira.repository;

import com.aaronbujatin.beveira.entity.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
}
