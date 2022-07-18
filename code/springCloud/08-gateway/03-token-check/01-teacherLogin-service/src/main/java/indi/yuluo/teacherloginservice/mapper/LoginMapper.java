package indi.yuluo.teacherloginservice.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import indi.yuluo.teacherloginservice.domain.TUser;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Author: yuluo
 * @CreateTime: 2022-07-16  10:53
 * @Description: TODO
 */

@Mapper
public interface LoginMapper extends BaseMapper<TUser> {
}
