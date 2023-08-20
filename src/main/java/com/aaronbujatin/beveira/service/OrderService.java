package com.aaronbujatin.beveira.service;

import com.aaronbujatin.beveira.entity.CheckoutInfo;
import com.aaronbujatin.beveira.entity.Order;
import com.aaronbujatin.beveira.entity.OrderItem;
import com.aaronbujatin.beveira.repository.CheckoutInfoRepository;
import com.aaronbujatin.beveira.repository.OrderItemRepository;
import com.aaronbujatin.beveira.repository.OrderRepository;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
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
    private final JavaMailSender javaMailSender;

    public Order saveOrder(Order order){

       try {

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
           orderRepository.save(order);

           MimeMessage message = javaMailSender.createMimeMessage();
           MimeMessageHelper helper = new MimeMessageHelper(message, true);

           helper.setTo(order.getCheckoutInfo().getEmail());
           helper.setSubject("Your Order has been Successfully Placed!");



           // HTML content
           String htmlContent = "<html><body>" +
                   "<p>Dear " + order.getCheckoutInfo().getFirstName() + " " + order.getCheckoutInfo().getLastName() + "</p>" +
                   "<p>Thank you for choosing Veira Co.! We're excited to confirm that your order has been successfully placed and is now being processed. " +
                   "Below are the details of your purchase:</p>" +
                   "<h1>Your Order Confirmation</h1>" +
                   "<p>Order Date: " + LocalDate.now() + "</p>" +
                   "<p>Shipping Address: " + order.getCheckoutInfo().getAddress() + ", " + order.getCheckoutInfo().getCity() + ", " + order.getCheckoutInfo().getPostalCode() + ", " + order.getCheckoutInfo().getCountry() + "</p>" +
                   "<h2>Order Summary:</h2>" +
                   "<table>" +
                   "<tr><th>Item name</th><th>Image</th><th>Quantity</th><th>Price</th></tr>";

           for (OrderItem item : order.getOrderItems()) {
               htmlContent += "<tr><td>" + item.getProductName() + "</td><td><img src=\"" + item.getImageUrl() + "\" width=\"100\" height=\"100\"></td><td> " + item.getQuantity() + "</td><td> ₱" + item.getUnitPrice() + "</td></tr>";
           }
           htmlContent += "</table>" +
                   "<h2>Payment Information:</h2>" +
                   "<p>Total Charged: ₱ " + totalAmount + "</p>" +
                   "<h2>Shipping Details:</h2>" +
                   "<p>Estimated Delivery Date: 2-3 Business days"  + "</p>" +
                   "<p>Shipping Method: Local delivery"  + "</p>" +
                   "<p>We will send you a shipping confirmation email along with tracking details...</p>" +
                   "<p>Thank you for shopping with us! We appreciate your business.</p>" +
                   "<p>Best regards,<br>Veira Co.</p>" +
                   "</body></html>";

           helper.setText(htmlContent, true);
           javaMailSender.send(message);



           return order;
       }catch (Exception e){
           return null;
       }

    }


}
