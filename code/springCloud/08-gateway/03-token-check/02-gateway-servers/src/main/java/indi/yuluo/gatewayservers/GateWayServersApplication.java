package indi.yuluo.gatewayservers;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class GateWayServersApplication {

    public static void main(String[] args) {
        SpringApplication.run(GateWayServersApplication.class, args);
    }

}
