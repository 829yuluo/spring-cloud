package indi.yuluo.orderservice.controller;

import indi.yuluo.orderservice.domain.Order;
import org.springframework.web.bind.annotation.*;

/**
 * @Author: yuluo
 * @CreateTime: 2022-07-12  21:15
 * @Description: TODO
 */

/**
 * 传递时间参数的时候，建议改成字符串处理
 */
@RestController
public class ParamController {

    /**
     * 传参的情况：
     *      url传参：/doOrder/包子
     *      get传参：
     *          传递一个参数
     *          传递多个参数
     *      post传参：
     *          传递一个对象
     *          传递一个对象+一个基本参数
     */

    @GetMapping("/testUrlParam/{name}/and/{age}")
    public String testUrlParam(@PathVariable("name") String name, @PathVariable("age") Integer age) {
        System.out.println(name + " " + age);
        return "ok";
    }

    /**
     * 如果不给@RequestParam设置值，默认是需要传递值的，不然报错
     *     @AliasFor("value")
     *     String name() default "";
     *
     *     boolean required() default true;
     * @param name
     * @return
     */
    @GetMapping("/testOneParam")
    public String oneParam(@RequestParam(required = false) String name) {
        System.out.println(name);
        return "ok";
    }

    @GetMapping("/testTwoParam")
    public String twoParam(@RequestParam(required = false) String name, @RequestParam(required = false) Integer age) {
        System.out.println(name);
        return "ok";
    }

    @PostMapping("/oneObj")
    public String oneObj(@RequestParam Order order) {
        System.out.println(order);
        return "ok";
    }

    @PostMapping("/oneObjOneParam")
    public String oneObjOneParam(@RequestParam Order order, @RequestParam("name") String name) {
        System.out.println(order);
        System.out.println(name);
        return "ok";
    }

}
