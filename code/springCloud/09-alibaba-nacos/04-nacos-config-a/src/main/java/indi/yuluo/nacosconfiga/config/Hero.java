package indi.yuluo.nacosconfiga.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;

/**
 * @Author: yuluo
 * @CreateTime: 2022-07-18  09:40
 * @Description: TODO
 */

@Component
@RefreshScope  // 动态刷新的作用域
public class Hero {

    @Value("${hero.name}")
    private String name;

    @Value("${hero.age}")
    private String age;

    @Value("${hero.address}")
    private String address;

    public Hero() {
    }

    public Hero(String name, String age, String address) {
        this.name = name;
        this.age = age;
        this.address = address;
    }


    @Override
    public String toString() {
        return "Hero{" +
                "name='" + name + '\'' +
                ", age='" + age + '\'' +
                ", address='" + address + '\'' +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
