package com.powernode.controller;

import com.powernode.domain.Order;
import com.powernode.feign.UserOrderFeign;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderController implements UserOrderFeign {

    @Override
    public Order getOrderByUserId(Integer userId) {
        System.out.println(userId);
        Order order = Order.builder()
                .name("青椒肉丝盖饭")
                .price(15D)
                .orderId(1)
                .build();
        return order;
    }
}
