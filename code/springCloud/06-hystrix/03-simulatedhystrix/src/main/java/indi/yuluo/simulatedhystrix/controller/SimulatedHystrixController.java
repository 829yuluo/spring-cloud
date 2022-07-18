package indi.yuluo.simulatedhystrix.controller;

import indi.yuluo.simulatedhystrix.annotation.SimulatedHystrixAnnotation_POINT_CUT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @Author: yuluo
 * @CreateTime: 2022-07-13  15:42
 * @Description: TODO
 */

@RestController
public class SimulatedHystrixController {

    @Autowired
    private RestTemplate restTemplate;

    /**
     * 自己模拟PRC，只有PRC只会出现远程调用
     * @return
     */
    @GetMapping("/doRPC")
    @SimulatedHystrixAnnotation_POINT_CUT   // 自定义切入点注解
    public String doPRC() {

        String result = restTemplate.getForObject("http://localhost:8989/abc", String.class);

        return result;
    }

}
