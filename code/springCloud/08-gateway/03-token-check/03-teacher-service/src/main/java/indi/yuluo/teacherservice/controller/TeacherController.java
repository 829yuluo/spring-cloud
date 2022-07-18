package indi.yuluo.teacherservice.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: yuluo
 * @CreateTime: 2022-07-16  16:28
 * @Description: TODO
 */

@RestController
public class TeacherController {

    @GetMapping("/teach")
    public String teach() {
        return "教书学习";
    }

}
