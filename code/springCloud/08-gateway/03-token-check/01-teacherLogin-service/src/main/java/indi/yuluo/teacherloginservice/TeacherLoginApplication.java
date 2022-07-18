package indi.yuluo.teacherloginservice;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
@MapperScan("indi.yuluo.teacherloginservice.mapper")
public class TeacherLoginApplication {

    public static void main(String[] args) {
        SpringApplication.run(TeacherLoginApplication.class, args);
    }

}
