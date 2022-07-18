package com.powernode.controller;

import com.powernode.feign.CustomerRentFeign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CustomerController {

    @Autowired
    private CustomerRentFeign customerRentFeign;

    @GetMapping("customerRent")
    public String CustomerRent(){
        System.out.println("客户来租车了");
        // RPC
        String rent = customerRentFeign.rent();
        return rent;
    }

}
