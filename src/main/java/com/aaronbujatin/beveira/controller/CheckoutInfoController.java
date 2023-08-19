package com.aaronbujatin.beveira.controller;

import com.aaronbujatin.beveira.entity.CheckoutInfo;
import com.aaronbujatin.beveira.service.CheckoutInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/checkouts")
public class CheckoutInfoController {

    private final CheckoutInfoService checkoutInfoService;

    @PostMapping
    public ResponseEntity<CheckoutInfo> saveCheckout(@RequestBody CheckoutInfo checkoutInfo){
        return new ResponseEntity<>(checkoutInfoService.saveCheckout(checkoutInfo), HttpStatus.CREATED);
    }
}
