package indi.yuluo.dockerboot.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
public class TestController {

    @Value("${server.port}")
    private String port;

    @RequestMapping("/helloDocker")
    public String helloDocker() {
        return "hello docker" + "\t" + port + "\t" + UUID.randomUUID().toString();
    }

    @GetMapping("/index")
    public String index() {
        return "服务端口号" + "\t" + port + "\t" + UUID.randomUUID().toString();
    }

}
