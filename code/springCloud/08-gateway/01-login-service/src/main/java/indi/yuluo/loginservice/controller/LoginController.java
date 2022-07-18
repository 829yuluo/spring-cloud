package indi.yuluo.loginservice.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

/**
 * @Author: yuluo
 * @CreateTime: 2022-07-15  11:08
 * @Description: TODO
 */

@RestController
public class LoginController {

    @GetMapping("/doLogin")
    public String doLogin(String username, String password) {
        System.out.println(username);
        System.out.println(password);

        // 登录一般是返回token，将用户名和密码换成token
        String token = UUID.randomUUID().toString();

        return token;
    }

}
