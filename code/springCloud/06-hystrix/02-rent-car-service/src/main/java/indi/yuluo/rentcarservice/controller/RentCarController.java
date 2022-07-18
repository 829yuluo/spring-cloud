package indi.yuluo.rentcarservice.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: yuluo
 * @CreateTime: 2022-07-13  11:55
 * @Description: TODO
 */

@RestController
public class RentCarController {

    @GetMapping("/rent")
    public String rendCar() {
        return "租车成功！";
    }

}
