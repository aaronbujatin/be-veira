package com.aaronbujatin.beveira.service;

import com.aaronbujatin.beveira.entity.CheckoutInfo;
import com.aaronbujatin.beveira.entity.Order;
import com.aaronbujatin.beveira.entity.OrderItem;
import com.aaronbujatin.beveira.repository.CheckoutInfoRepository;
import com.aaronbujatin.beveira.repository.OrderItemRepository;
import com.aaronbujatin.beveira.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class OrderService {

    private final CheckoutInfoRepository checkoutInfoRepository;
    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;


    public Order saveOrder(Order order){

        BigDecimal totalAmount = BigDecimal.ZERO;
        List<OrderItem> orderItems = new ArrayList<>(order.getOrderItems());
        for(OrderItem item : orderItems){
            BigDecimal itemTotal = BigDecimal.valueOf(item.getQuantity()).multiply(item.getUnitPrice());
            item.setOrder(order);
            totalAmount = totalAmount.add(itemTotal);
        }


        order.setTotalAmount(totalAmount);
        order.setOrderDate(LocalDate.now());

        orderItemRepository.saveAll(order.getOrderItems());

        CheckoutInfo checkoutInfo = order.getCheckoutInfo();
        checkoutInfo.setFirstName(order.getCheckoutInfo().getFirstName());
        checkoutInfo.setLastName(order.getCheckoutInfo().getLastName());
        checkoutInfo.setEmail(order.getCheckoutInfo().getEmail());
        checkoutInfo.setCountry(order.getCheckoutInfo().getCountry());
        checkoutInfo.setAddress(order.getCheckoutInfo().getAddress());
        checkoutInfo.setPostalCode(order.getCheckoutInfo().getPostalCode());
        checkoutInfo.setCity(order.getCheckoutInfo().getCity());
        checkoutInfo.setPhoneNumber(order.getCheckoutInfo().getPhoneNumber());
        checkoutInfoRepository.save(checkoutInfo);

        return orderRepository.save(order);

    }


}
