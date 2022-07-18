package indi.yuluo.nacosclienta.feign;

import indi.yuluo.nacosclienta.feign.Hystrix.TestFeignHystrix;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @Author: yuluo
 * @CreateTime: 2022-07-17  16:17
 * @Description: TODO
 */

@Component
@FeignClient(value = "nacos-client-b", fallback = TestFeignHystrix.class)
public interface TestFeign {

    /**
     * 添加远程调用服务的方法签名
     * @return
     */
    @GetMapping("/testFeignAndGateway")
    public String testFeignAndGateway();

}
