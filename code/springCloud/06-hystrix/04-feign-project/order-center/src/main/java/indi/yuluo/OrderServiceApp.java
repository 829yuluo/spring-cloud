package indi.yuluo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @Author: yuluo
 * @CreateTime: 2022-07-13  22:49
 * @Description: TODO
 */

@SpringBootApplication
@EnableEurekaClient
public class OrderServiceApp {

    public static void main(String[] args) {
        SpringApplication.run(OrderServiceApp.class, args);
    }

}
