package indi.yuluo.orderservice.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: yuluo
 * @CreateTime: 2022-07-12  08:45
 * @Description: TODO
 */

@RestController
public class OrderController {

    @GetMapping("/doOrder")
    public String doOrder() {
        return "下单成功 油条豆浆！";
    }

}
