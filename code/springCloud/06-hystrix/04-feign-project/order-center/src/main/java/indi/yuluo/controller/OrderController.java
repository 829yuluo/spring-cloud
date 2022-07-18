package indi.yuluo.controller;

import indi.yuluo.domain.Order;
import indi.yuluo.feign.UserOrderFeign;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: yuluo
 * @CreateTime: 2022-07-13  22:51
 * @Description: TODO
 */

@RestController
public class OrderController implements UserOrderFeign {

    /**
     * 根据用户id查询订单信息
     * 实现common-apis的feign接口里的方法
     * @param userId
     * @return
     */
    @Override
    public Order getOrderByUserId(Integer userId) {

        Order order = Order.builder()
                .orderId(001)
                .name("炒面")
                .price(20.00)
                .build();

        return order;
    }
}
