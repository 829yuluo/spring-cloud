package com.powernode.controller;

import com.powernode.domain.Order;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.Date;


/**
 * url    /doOrder/热干面/add/油条/aaa
 * get传递一个参数
 * get传递多个参数
 * post传递一个对象
 * post传递一个对象+一个基本参数
 */
@RestController
public class ParamController {

    @GetMapping("testUrl/{name}/and/{age}")
    public String testUrl(@PathVariable("name") String name, @PathVariable("age") Integer age) {
        System.out.println(name + ":" + age);
        return "ok";
    }

    @GetMapping("oneParam")
    public String oneParam(@RequestParam(required = false) String name) {
        System.out.println(name);
        return "ok";
    }


    @GetMapping("twoParam")
    public String twoParam(@RequestParam(required = false) String name, @RequestParam(required = false) Integer age) {
        System.out.println(name);
        System.out.println(age);
        return "ok";
    }

    @PostMapping("oneObj")
    public String oneObj(@RequestBody Order order) {
        System.out.println(order);
        return "ok";
    }


    @PostMapping("oneObjOneParam")
    public String oneObjOneParam(@RequestBody Order order,@RequestParam("name") String name) {
        System.out.println(name);
        System.out.println(order);
        return "ok";
    }


    ////////////////// 单独传递时间对象

    @GetMapping("testTime")
    public String testTime(@RequestParam Date date){
        System.out.println(date);
        return "ok";
    }
}
