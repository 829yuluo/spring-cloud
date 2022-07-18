package indi.yuluo.customerservice.controller;

import indi.yuluo.customerservice.feign.CustomerRentCarFeign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: yuluo
 * @CreateTime: 2022-07-13  11:58
 * @Description: TODO
 */

@RestController
public class CustomerController {

    // 注入feign的接口
    // 注意：在这的时候需要在启动类上加一个@EnableFeignClients的注解来开启feign接口的功能
    @Autowired
    private CustomerRentCarFeign customerRentCarFeign;

    @GetMapping("/customerRent")
    public String customerRent() {

        System.out.println("客户来租车了！");

        // 使用feign调用远程接口
        String rent = customerRentCarFeign.rendCar();

        return rent;
    }


}
