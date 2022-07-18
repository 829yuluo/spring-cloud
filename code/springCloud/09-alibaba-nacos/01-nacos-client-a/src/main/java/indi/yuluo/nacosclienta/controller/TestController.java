package indi.yuluo.nacosclienta.controller;

import indi.yuluo.nacosclienta.feign.TestFeign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author: yuluo
 * @CreateTime: 2022-07-17  16:04
 * @Description: TODO
 */

@RestController
public class TestController {

    @Autowired
    private TestFeign testFeign;

    @Autowired
    private DiscoveryClient discoveryClient;

    @GetMapping("/test")
    public String test() {
        List<ServiceInstance> instances = discoveryClient.getInstances("nacos-client-b");
        System.out.println(instances);

        return instances.toString();
    }

    @GetMapping("/testFeign")
    public String testFeign() {

        String s = testFeign.testFeignAndGateway();

        return "调用client-b的服务的结果：" + s + "！";
    }

}
