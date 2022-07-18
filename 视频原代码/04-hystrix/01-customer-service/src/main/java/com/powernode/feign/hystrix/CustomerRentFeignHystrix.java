package com.powernode.feign.hystrix;


import com.powernode.feign.CustomerRentFeign;
import org.springframework.stereotype.Component;

/**
 * 这里需要加入ioc容器
 */
@Component
public class CustomerRentFeignHystrix implements CustomerRentFeign {

    /**
     * 这个方法就是备选方案
     * @return
     */
    @Override
    public String rent() {
        return "我是备胎";
    }
}
