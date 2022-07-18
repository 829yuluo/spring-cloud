package com.powernode.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class DiscoveryController {

    @Autowired
    private DiscoveryClient discoveryClient;

    /**
     * 通过应用名称 找到服务的ip和port
     * 你会不会在java代码中发起http请求呢?
     * http://192.168.204.1:8081/api?
     *
     * @param serviceName
     * @return
     */
    @GetMapping("test")
    public String doDiscovery(String serviceName){
        // 这就是服务发现  通过服务的应用名 找到服务的具体信息
        List<ServiceInstance> instances = discoveryClient.getInstances(serviceName);
        instances.forEach(System.out::println);
        ServiceInstance serviceInstance = instances.get(0);
        String ip = serviceInstance.getHost();
        int port = serviceInstance.getPort();
        System.out.println(ip+":"+port);
        // 这里去找b的ip和port
        return instances.get(0).toString();
    }



}
