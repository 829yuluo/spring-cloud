package indi.yuluo.feign.hystrix;

import indi.yuluo.domain.Order;
import indi.yuluo.feign.UserOrderFeign;
import org.springframework.stereotype.Component;

/**
 * @Author: yuluo
 * @CreateTime: 2022-07-13  23:09
 * @Description: TODO
 */

@Component
public class UserOrderFeignHystrix implements UserOrderFeign {

    /**
     * 备选方法
     * 一般远程调用的熔断可以直接返回null值
     * @param userId
     * @return
     */
    @Override
    public Order getOrderByUserId(Integer userId) {

        Order order = Order.builder()
                .name("备选方法")
                .price(0.00)
                .orderId(000)
                .build();

        return order;
    }
}
