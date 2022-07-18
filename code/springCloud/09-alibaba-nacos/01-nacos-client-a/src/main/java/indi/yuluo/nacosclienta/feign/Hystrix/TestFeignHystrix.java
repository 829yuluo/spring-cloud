package indi.yuluo.nacosclienta.feign.Hystrix;

import indi.yuluo.nacosclienta.feign.TestFeign;
import org.springframework.stereotype.Component;

/**
 * @Author: yuluo
 * @CreateTime: 2022-07-17  17:24
 * @Description: TODO
 */

@Component
public class TestFeignHystrix implements TestFeign {
    public String testFeignAndGateway() {

        return "这是测试Hystrix的接口! ";
    }
}
