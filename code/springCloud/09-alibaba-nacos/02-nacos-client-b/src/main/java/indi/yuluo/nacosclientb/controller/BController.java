package indi.yuluo.nacosclientb.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: yuluo
 * @CreateTime: 2022-07-17  16:15
 * @Description: TODO
 */

@RestController
public class BController {

    @GetMapping("/testFeignAndGateway")
    public String testFeignAndGateway() {

        return "testFeignAndGateway 这是client-b中的服务，在client-a中调用这个服务！";
    }

}
