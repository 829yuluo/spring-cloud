package indi.yuluo.resttemplate.controller;

import indi.yuluo.resttemplate.pojo.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: yuluo
 * @FileName: TestController.java
 * @createTime: 2022/6/17 12:33
 * @Description:
 */

@RestController
public class TestController {

    @GetMapping("/testGet")
    public String get(String name) {

        System.out.println(name);

        return "ok";
    }

    /**
     * post传参常见的有两种
     * 1、application/json
     *      json参数的核心在content-type 是 application/json类型 charset=utf-8
     * 2、form 表单参数
     *      header content-type = application/x-www-form-urlencoded
     * @param user
     * @return
     */
    @PostMapping("/testPost1")
    public String post(@RequestBody User user) {

        System.out.println(user);

        return "ok";
    }

    @PostMapping(value = "/testPost2")
    public String post2(User user) {
        System.out.println(user);

        return "ok";
    }

}
