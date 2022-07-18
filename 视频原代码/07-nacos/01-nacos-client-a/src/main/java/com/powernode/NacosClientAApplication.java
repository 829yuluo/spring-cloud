package com.powernode;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableDiscoveryClient  // 开启服务发现客户端
@EnableFeignClients
public class NacosClientAApplication {

    public static void main(String[] args) {
        SpringApplication.run(NacosClientAApplication.class, args);
    }

    @Bean
    public String abc(){
        return "abc";
    }
}
