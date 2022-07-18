package com.powernode.feign;

import com.powernode.feign.hystrix.CustomerRentFeignHystrix;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * 这里需要指定熔断的类
 */
@FeignClient(value = "order-service")
public interface CustomerOrderFeign {

    @GetMapping("rent")
    public String rent();


}
