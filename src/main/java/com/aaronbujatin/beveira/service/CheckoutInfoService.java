package com.aaronbujatin.beveira.service;

import com.aaronbujatin.beveira.entity.CheckoutInfo;
import com.aaronbujatin.beveira.repository.CheckoutInfoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CheckoutInfoService {

    private final CheckoutInfoRepository checkoutInfoRepository;

    public CheckoutInfo saveCheckout(CheckoutInfo checkoutInfo){
        return checkoutInfoRepository.save(checkoutInfo);
    }

}
