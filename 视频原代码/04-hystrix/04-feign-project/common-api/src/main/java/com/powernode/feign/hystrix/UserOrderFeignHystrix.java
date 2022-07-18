package com.powernode.feign.hystrix;

import com.powernode.domain.Order;
import com.powernode.feign.UserOrderFeign;
import org.springframework.stereotype.Component;

@Component
public class UserOrderFeignHystrix implements UserOrderFeign {

    /**
     * 一般远程调用的熔断可以直接返回null
     * @param userId
     * @return
     */
    @Override
    public Order getOrderByUserId(Integer userId) {
        return null;
    }
}
