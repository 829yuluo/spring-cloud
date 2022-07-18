package com.powernode.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TeacherController {


    @GetMapping("teach")
    public String teach(){
        return "教书学习";
    }

}
