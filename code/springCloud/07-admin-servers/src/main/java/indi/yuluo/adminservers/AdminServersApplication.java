package indi.yuluo.adminservers;

import de.codecentric.boot.admin.server.config.EnableAdminServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
@EnableAdminServer
public class AdminServersApplication {

    public static void main(String[] args) {

        SpringApplication.run(AdminServersApplication.class, args);
    }

}
