package indi.yuluo.providerb.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: yuluo
 * @CreateTime: 2022-07-11  16:23
 * @Description: TODO
 */

@RestController
public class ProviderController {

    @GetMapping("/hello")
    public String hello() {
        return "我是服务提供者bbb的接口";
    }

}
