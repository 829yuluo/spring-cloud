package indi.yuluo.teacherloginservice.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import indi.yuluo.teacherloginservice.domain.TUser;
import indi.yuluo.teacherloginservice.mapper.LoginMapper;
import indi.yuluo.teacherloginservice.service.LoginService;
import org.springframework.stereotype.Service;

/**
 * @Author: yuluo
 * @CreateTime: 2022-07-16  10:51
 * @Description: TODO
 */

@Service
public class LoginServiceImpl extends ServiceImpl<LoginMapper, TUser> implements LoginService {
}
