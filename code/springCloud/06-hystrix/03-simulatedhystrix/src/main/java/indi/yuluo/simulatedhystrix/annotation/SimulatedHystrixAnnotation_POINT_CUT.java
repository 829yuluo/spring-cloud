package indi.yuluo.simulatedhystrix.annotation;

import java.lang.annotation.*;

/**
 * @Author: yuluo
 * @CreateTime: 2022-07-13  15:53
 * @Description: 使用注解做切入点的管理
 *
 * 熔断器切面注解
 *
 */

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface SimulatedHystrixAnnotation_POINT_CUT {
}
