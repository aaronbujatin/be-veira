package com.aaronbujatin.beveira.repository;

import com.aaronbujatin.beveira.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
