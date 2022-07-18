package indi.yuluo.customerservice.feign;

import indi.yuluo.customerservice.feign.hystrix.CustomerRentCArFeignHystrix;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @Author: yuluo
 * @CreateTime: 2022-07-13  11:59
 * @Description: TODO
 */

/*
 value是服务提供者的名字
 另外需要在@FeignClient的注解中指定备选方案
*/
@FeignClient(value = "rent-car-service", fallback = CustomerRentCArFeignHystrix.class)
public interface CustomerRentCarFeign {

    // 写的是需要rpc调用的远程方法签名
    @GetMapping("/rent")
    String rendCar();

}
