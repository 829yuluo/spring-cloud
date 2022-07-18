package com.powernode.controller;

import com.powernode.domain.Order;
import com.powernode.feign.UserOrderFeign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    public UserOrderFeign userOrderFeign;

    @GetMapping("find")
    public Order findOrder() {
        return userOrderFeign.getOrderByUserId(1);
    }


}
