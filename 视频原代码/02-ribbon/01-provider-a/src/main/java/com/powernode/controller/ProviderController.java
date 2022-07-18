package com.powernode.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProviderController {


    @GetMapping("hello")
    public String hello(){
        return "我是提供者aaaa的接口";
    }

}
