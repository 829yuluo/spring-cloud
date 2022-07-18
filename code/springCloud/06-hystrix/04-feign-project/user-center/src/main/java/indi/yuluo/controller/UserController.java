package indi.yuluo.controller;

import indi.yuluo.domain.Order;
import indi.yuluo.feign.UserOrderFeign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: yuluo
 * @CreateTime: 2022-07-13  23:01
 * @Description: TODO
 */

@RestController
public class UserController {

    @Autowired
    private UserOrderFeign userOrderFeign;

    @GetMapping("/findOrder")
    public String findOrder() {
        Order orderByUserId = userOrderFeign.getOrderByUserId(001);

        return orderByUserId.toString();
    }

}
