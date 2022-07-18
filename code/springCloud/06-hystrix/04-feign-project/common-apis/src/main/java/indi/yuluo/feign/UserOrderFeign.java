package indi.yuluo.feign;

import indi.yuluo.domain.Order;
import indi.yuluo.feign.hystrix.UserOrderFeignHystrix;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @Author: yuluo
 * @CreateTime: 2022-07-13  22:51
 * @Description: TODO
 */

@FeignClient(value = "order-service", fallback = UserOrderFeignHystrix.class)
public interface UserOrderFeign {

    /**
     * 查询订单
     */
    @GetMapping("/order/getOrderByUserId")
    Order getOrderByUserId(@RequestParam Integer userId);

}
