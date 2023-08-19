package com.aaronbujatin.beveira.repository;

import com.aaronbujatin.beveira.entity.CheckoutInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CheckoutInfoRepository extends JpaRepository<CheckoutInfo, Long> {
}
