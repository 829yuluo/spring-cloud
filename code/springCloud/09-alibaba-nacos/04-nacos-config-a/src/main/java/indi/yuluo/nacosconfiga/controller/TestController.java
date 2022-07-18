package indi.yuluo.nacosconfiga.controller;

import indi.yuluo.nacosconfiga.config.Hero;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: yuluo
 * @CreateTime: 2022-07-18  09:25
 * @Description: TODO
 */

@RestController
public class TestController {

    @Autowired
    private Hero hero;

    @GetMapping("/test")
    public String test() {

        String s = hero.getName() + " " + hero.getAge() + " " + hero.getAddress();

        return s;
    }

}

