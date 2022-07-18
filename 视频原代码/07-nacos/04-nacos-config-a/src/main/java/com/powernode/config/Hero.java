package com.powernode.config;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
@RefreshScope  // 给这个类上 添加一个刷新的作用域
public class Hero {

    @Value("${hero.name}")
    private String name;

    @Value("${hero.age}")
    private Integer age;

    @Value("${hero.address}")
    private String address;

}
