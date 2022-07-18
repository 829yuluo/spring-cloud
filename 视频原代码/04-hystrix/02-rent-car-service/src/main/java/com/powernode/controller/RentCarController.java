package com.powernode.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RentCarController {


    @GetMapping("rent")
    public String rent() {
        return "租车成功";

    }

}
