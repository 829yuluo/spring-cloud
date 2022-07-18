package com.powernode.controller;

import com.powernode.domain.Order;
import com.powernode.feign.UserOrderFeign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@RestController
public class UserController {

    /**
     * 接口是不能做事情的
     * 如果想做事 必须要有对象
     * 那么这个接口肯定是被创建出代理对象的
     * 动态代理 jdk(java interface 接口 $Proxy )  cglib(subClass 子类)
     * jdk动态代理 只要是代理对象调用的方法必须走 java.lang.reflect.InvocationHandler#invoke(java.lang.Object, java.lang.reflect.Method, java.lang.Object[])
     */
    @Autowired
    public UserOrderFeign userOrderFeign;

    /**
     * 总结
     * 浏览器(前端)-------> user-service(/userDoOrder)-----RPC(feign)--->order-service(/doOrder)
     * feign的默认等待时间时1s
     * 超过1s就在直接报错超时
     *
     * @return
     */
    @GetMapping("userDoOrder")
    public String userDoOrder() {
        System.out.println("有用户进来了");
        // 这里需要发起远程调用
        String s = userOrderFeign.doOrder();
        return s;
    }



    @GetMapping("testParam")
    public String testParam(){
        String cxs = userOrderFeign.testUrl("cxs", 18);
        System.out.println(cxs);

        String t = userOrderFeign.oneParam("老唐");
        System.out.println(t);

        String lg = userOrderFeign.twoParam("雷哥", 31);
        System.out.println(lg);

        Order order = Order.builder()
                .name("牛排")
                .price(188D)
                .time(new Date())
                .id(1)
                .build();

        String s = userOrderFeign.oneObj(order);
        System.out.println(s);

        String param = userOrderFeign.oneObjOneParam(order, "稽哥");
        System.out.println(param);
        return "ok";
    }

    /**
     * Sun Mar 20 10:24:13 CST 2022
     * Mon Mar 21 00:24:13 CST 2022  +- 14个小时
     * 1.不建议单独传递时间参数
     * 2.转成字符串   2022-03-20 10:25:55:213 因为字符串不会改变
     * 3.jdk LocalDate 年月日    LocalDateTime 会丢失s
     * 4.改feign的源码
     *
     * @return
     */
    @GetMapping("time")
    public String time(){
        Date date = new Date();
        System.out.println(date);
        String s = userOrderFeign.testTime(date);

        LocalDate now = LocalDate.now();
        LocalDateTime now1 = LocalDateTime.now();

        return s;
    }



}
