package com.aaronbujatin.beveira.controller;

import com.aaronbujatin.beveira.entity.Order;
import com.aaronbujatin.beveira.service.OrderItemService;
import com.aaronbujatin.beveira.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/orders")
@CrossOrigin("*")
public class OrderController {

    private final OrderItemService orderItemService;
    private final OrderService orderService;
    
    @PostMapping()
    public ResponseEntity<Order> saveOrder(@RequestBody Order order){
        return new ResponseEntity<>(orderService.saveOrder(order), HttpStatus.CREATED);
    }


}
