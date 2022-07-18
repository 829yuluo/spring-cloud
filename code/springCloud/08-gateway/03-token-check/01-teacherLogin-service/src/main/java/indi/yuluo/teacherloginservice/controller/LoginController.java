package indi.yuluo.teacherloginservice.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import indi.yuluo.teacherloginservice.domain.TUser;
import indi.yuluo.teacherloginservice.mapper.LoginMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Duration;
import java.util.UUID;

/**
 * @Author: yuluo
 * @CreateTime: 2022-07-16  10:21
 * @Description: TODO
 */

@Slf4j
@RestController
public class LoginController {

    @Autowired
    private LoginMapper loginMapper;

    @Autowired
    private StringRedisTemplate redisTemplate;

    @GetMapping("/doLogin")
    public String login(String username, String password) {

        log.info(username, password);

        // 这里去数据库查询数据，看用户是否存在
        LambdaQueryWrapper<TUser> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(TUser::getUsername, username);

        // 查询用户
        TUser tUser = loginMapper.selectOne(lambdaQueryWrapper);
        // System.out.println(tUser);
        // TUser(id=2, username=yuluo, password=e10adc3949ba59abbe56e057f20f883e)

        // 因为http协议是无状态的，为什么用token  就是要建立一个有状态的连接，token就是一个标识
        String token = UUID.randomUUID().toString();

        // 存到redis中 7200s=2h  1day = 86400s
        redisTemplate.opsForValue().set(token, tUser.toString(), Duration.ofSeconds(7200));

        return token;
    }

}
