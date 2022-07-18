package indi.yuluo.customerservice.feign.hystrix;

import indi.yuluo.customerservice.feign.CustomerRentCarFeign;
import org.springframework.stereotype.Component;

/**
 * @Author: yuluo
 * @CreateTime: 2022-07-13  12:10
 * @Description:
 *  1、加入spring容器进行管理
 *  2、重写feign接口里的方法，重写的方法就是备选方案
 *
 */

@Component
public class CustomerRentCArFeignHystrix implements CustomerRentCarFeign {

    /**
     * 这个方法就是备选方案
     * @return
     */
    @Override
    public String rendCar() {
        return "我是备选方案";
    }
}
