package indi.yuluo.nacosclienta;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableDiscoveryClient  // 开启服务注册
@EnableFeignClients  // 开启feign的客户端
public class NacosClientAApplication {

    public static void main(String[] args) {

        SpringApplication.run(NacosClientAApplication.class, args);
    }

}
