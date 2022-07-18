package com.powernode.controller;

import com.powernode.domain.Hero;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {


    @Autowired
    private Hero hero;

    @GetMapping("info")
    public String getInfo() {
        return hero.getName() + ":" + hero.getAge() + ":" + hero.getAddress() + ":" + hero.getHobby();
    }


}
