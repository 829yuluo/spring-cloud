package com.powernode.controller;

import com.powernode.config.Hero;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @Autowired
    public Hero hero;

    @GetMapping("info")
    public String heroInfo() {
        return hero.getName() + ":" + hero.getAge() + ":" + hero.getAge();
    }

}
