package indi.yuluo.userserviceprovider.feign;

import indi.yuluo.userserviceprovider.domain.Order;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @Author: yuluo
 * @CreateTime: 2022-07-12  09:04
 * @Description: TODO
 */

/**
 * @FeignClient(value = "")
 * value为提供者的应用名称
 */
@FeignClient(value = "order-service-provider")
public interface UserOrderFeign {

    /*
    需要调用那个controller接口，将那个方法的签名放在这里
    方法签名（包含了一个方法所有的属性）
     */
    @GetMapping("/doOrder")
    String doOrder();

    @GetMapping("/testUrlParam/{name}/and/{age}")
    String testUrlParam(@PathVariable("name") String name, @PathVariable("age") Integer age);

    @GetMapping("/testOneParam")
    String oneParam(@RequestParam(required = false) String name);

    @GetMapping("/testTwoParam")
    String twoParam(@RequestParam(required = false) String name, @RequestParam(required = false) Integer age);

    @PostMapping("/oneObj")
    String oneObj(@RequestParam Order order);

    @PostMapping("/oneObjOneParam")
    String oneObjOneParam(@RequestParam Order order, @RequestParam("name") String name);

}
