package indi.yuluo.teacherloginservice.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: yuluo
 * @CreateTime: 2022-07-16  10:49
 * @Description: TODO
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TUser {

    private Integer id;
    private String username;
    private String password;

}
