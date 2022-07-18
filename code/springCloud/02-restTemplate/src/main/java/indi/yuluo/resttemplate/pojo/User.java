package indi.yuluo.resttemplate.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author: yuluo
 * @FileName: User.java
 * @createTime: 2022/6/17 12:35
 * @Description:
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User implements Serializable {

    private String name;

    private Integer age;

}
