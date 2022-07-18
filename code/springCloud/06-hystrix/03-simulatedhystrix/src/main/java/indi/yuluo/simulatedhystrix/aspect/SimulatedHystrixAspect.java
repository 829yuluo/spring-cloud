package indi.yuluo.simulatedhystrix.aspect;

import indi.yuluo.simulatedhystrix.model.SimulatedHystrix;
import indi.yuluo.simulatedhystrix.model.SimulatedHystrixStatus;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * @Author: yuluo
 * @CreateTime: 2022-07-13  15:49
 * @Description: 用切面切入远程调用的过程中
 */

@Component
@Aspect
public class SimulatedHystrixAspect {

    // 切入点
    // 这样不是很方便，使用注解的方式
    // private final static String POINT_CUT = "execution (* indi.yuluo.controller.SimulatedHystrixController.doRPC(..))";

    // 因为一个消费者用多个消费者，每个提供者都有自己的断路器
    // 在消费者里面去创建一个断路器的容器
    private static Map<String, SimulatedHystrix> simulatedHystrix = new HashMap<>();

    /**
     * 假设 是需要去调用order-service的服务，这个方法就是给每个服务都创建一个自己的断路器容器
     * 服务和服务之间分隔开，互不影响
     */
    static {
        simulatedHystrix.put("order-service", new SimulatedHystrix());
    }

    Random random = new Random();

    /**
     * 类比拦截器
     * 就是要判断当前断路器的状态，从而决定是否调用（执行目标方法）
     * @param joinPoint
     * @return
     */
    @Around(value = "@annotation(indi.yuluo.simulatedhystrix.annotation.SimulatedHystrixAnnotation_POINT_CUT)")
    public Object SimulatedHystrixAround(ProceedingJoinPoint joinPoint) {

        // 定义返回值
        Object result = null;

        // 获取到当前提供者的断路器
        SimulatedHystrix hystrix = simulatedHystrix.get("order-service");
        SimulatedHystrixStatus status = hystrix.getStatus();
        switch (status) {
            case CLOSE:
                // 正常 去调用  执行目标方法
                try {
                    result = joinPoint.proceed();
                    return result;
                } catch (Throwable e) {
                    // 说明调用失败，给当前服务记录失败次数
                    hystrix.addFailCount();
                    return "我是备选方案";
                }
            case OPEN:
                // 不能调用
                return "我是备选方法";
            case HALF_OPEN:
                // 可以用少许流量去调用 使用20%去尝试调用
                int i = random.nextInt(5);
                System.out.println(i);
                if (i == 1) {
                    try {
                        result = joinPoint.proceed();
                        // 走这一行说明服务调用成功  断路器关闭
                        hystrix.setStatus(SimulatedHystrixStatus.OPEN);

                        // 需要工作
                        synchronized (hystrix) {
                            hystrix.getLock().notify();
                        }

                        return hystrix;

                    } catch (Throwable e) {
                        return "我是备选方案";
                    }

                }
            default:
                return "我是备选方案";
        }
    }

}
