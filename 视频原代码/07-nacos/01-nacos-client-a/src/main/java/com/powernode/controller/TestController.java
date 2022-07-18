package com.powernode.controller;

import com.powernode.feign.TestFeign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TestController {

    @Autowired
    public DiscoveryClient discoveryClient;

    // # 读ioc容器的值
    // $ 读配置文件的值
    // 如果直接写字符串 那么就是一个简单赋值操作
    @Value("aaaa")
    public String data;

    @Autowired
    public TestFeign testFeign;

    @GetMapping("test")
    public String test() {
        List<ServiceInstance> instances = discoveryClient.getInstances("user-service");
        System.out.println(instances);
        return testFeign.info();
    }


}
