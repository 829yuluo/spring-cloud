package indi.yuluo.orderservice.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @Author: yuluo
 * @CreateTime: 2022-07-12  21:25
 * @Description: TODO
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Order {

    private Integer id;
    private String name;
    private Double price;
    private Date time;

}
