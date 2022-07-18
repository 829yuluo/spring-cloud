package indi.yuluo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @Author: yuluo
 * @CreateTime: 2022-07-13  23:01
 * @Description: TODO
 */

@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients
public class UserServiceApp {

    public static void main(String[] args) {
        SpringApplication.run(UserServiceApp.class, args);
    }

}
