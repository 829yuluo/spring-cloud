package indi.yuluo.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: yuluo
 * @CreateTime: 2022-07-13  22:42
 * @Description: TODO
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Order {

    private Integer orderId;
    private String name;
    private Double price;

}
