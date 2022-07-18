package indi.yuluo.teacherloginservice;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import indi.yuluo.teacherloginservice.domain.TUser;
import indi.yuluo.teacherloginservice.mapper.LoginMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ApplicationTests {

    @Autowired
    private LoginMapper loginMapper;

    @Test
    void contextLoads() {
    }

    @Test
    void testMybatisPlus() {
        LambdaQueryWrapper<TUser> tUserLambdaQueryWrapper = new LambdaQueryWrapper<>();
        tUserLambdaQueryWrapper.eq(TUser::getUsername, "yuluo");

        TUser tUser = loginMapper.selectOne(tUserLambdaQueryWrapper);

        System.out.println(tUser);
    }


}
