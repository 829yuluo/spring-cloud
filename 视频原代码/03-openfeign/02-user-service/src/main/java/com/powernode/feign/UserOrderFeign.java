package com.powernode.feign;

import com.powernode.domain.Order;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

/**
 * @FeignClient(value = "order-service")
 * value 就是提供者的应用名称
 */
@FeignClient(value = "order-service")
public interface UserOrderFeign {

    /**
     * 你需要调用哪个controller  就写它的方法签名
     * 方法签名(就是包含一个方法的所有的属性)
     *
     * @return
     */
    @GetMapping("doOrder")
    String doOrder();

    @GetMapping("testUrl/{name}/and/{age}")
    public String testUrl(@PathVariable("name") String name, @PathVariable("age") Integer age);

    @GetMapping("oneParam")
    public String oneParam(@RequestParam(required = false) String name);

    @GetMapping("twoParam")
    public String twoParam(@RequestParam(required = false) String name, @RequestParam(required = false) Integer age);

    @PostMapping("oneObj")
    public String oneObj(@RequestBody Order order);


    @PostMapping("oneObjOneParam")
    public String oneObjOneParam(@RequestBody Order order, @RequestParam("name") String name);



    @GetMapping("testTime")
    public String testTime(@RequestParam Date date);
}
