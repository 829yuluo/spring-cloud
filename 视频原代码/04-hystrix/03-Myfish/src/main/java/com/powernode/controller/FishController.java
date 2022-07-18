package com.powernode.controller;

import com.powernode.anno.MyFish;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class FishController {

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("doRpc")
    @MyFish
    public String doRpc(){
        String result = restTemplate.getForObject("http://localhost:8989/abc", String.class);
        return result;
    }

}
