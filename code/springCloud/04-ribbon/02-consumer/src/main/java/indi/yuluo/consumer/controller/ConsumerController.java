package indi.yuluo.consumer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @Author: yuluo
 * @CreateTime: 2022-07-11  16:35
 * @Description: TODO
 */

@RestController
public class ConsumerController {

    @Autowired
    private RestTemplate restTemplate;

    /**
     * 实际的请求地址是：http://localhost:9092/test?serviceName=provider
     *
     * 思考：ribbon是如何将http://provider/hello路径请求成功的
     * http://localhost:9090/hello
     * 1、拦截请求
     * 2、截取主机名称
     * 3、借助eureka来做服务发现
     * 4、通过负载均衡算法来拿到一个服务ip port
     * 5、reconstructURL  （重构url）
     * 6、发起请求
     *
     * @param serviceName
     * @return
     */
    @GetMapping("/test")
    public String testRibbon(String serviceName) {

        // 正常来说 需要端口和ip以及路径才能使用
        String result = restTemplate.getForObject("http://" + serviceName + "/hello", String.class);

        // 如果想用原生的RestTemplate去访问 需要重新创建一个RestTemplate对象
        // 只要给RestTemplate加了ribbon的注解，项目中这个对象发起的请求都会走ribbon的代理

        return result;
    }

}
