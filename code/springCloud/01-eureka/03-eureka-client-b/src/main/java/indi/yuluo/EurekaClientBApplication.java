package indi.yuluo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient  // 开启eureka的客户端功能
public class EurekaClientBApplication {

    public static void main(String[] args) {

        SpringApplication.run(EurekaClientBApplication.class, args);
    }

}
