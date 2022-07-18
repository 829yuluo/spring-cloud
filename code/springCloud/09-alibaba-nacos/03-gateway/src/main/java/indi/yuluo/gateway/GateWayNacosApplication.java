package indi.yuluo.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class GateWayNacosApplication {

    public static void main(String[] args) {

        SpringApplication.run(GateWayNacosApplication.class, args);
    }

}
