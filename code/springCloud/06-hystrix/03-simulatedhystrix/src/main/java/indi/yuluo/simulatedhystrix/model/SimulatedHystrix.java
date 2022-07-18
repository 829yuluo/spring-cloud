package indi.yuluo.simulatedhystrix.model;

import lombok.Data;

import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Author: yuluo
 * @CreateTime: 2022-07-13  16:00
 * @Description: 断路器模型
 */

@Data
public class SimulatedHystrix {

    /*断路器状态*/
    private SimulatedHystrixStatus status = SimulatedHystrixStatus.CLOSE;

    /*窗口时间*/
    public static final Integer WINDOW_TIME = 20;

    /*最大失败次数*/
    public static final Integer MAX_FAIL_COUNT = 3;

    /*当前失败次数*/
    // AtomicInteger 可以保证线程安全 用自旋加cs i ++
    private AtomicInteger currentFailCount = new AtomicInteger(0);

    /**
     * 线程池
     */
    private ThreadPoolExecutor poolExecutor = new ThreadPoolExecutor(
            4,
            8,
            30,
            TimeUnit.SECONDS,
            new LinkedBlockingDeque<>(2000),  // 阻塞队列
            Executors.defaultThreadFactory(),
            new ThreadPoolExecutor.AbortPolicy()
    );

    private Object lock = new Object();

    /**
     * 成员代码块 当对象被new的时候才会执行
     */
    {
        poolExecutor.execute(() -> {
            while (true) {
                try {
                    TimeUnit.SECONDS.sleep(WINDOW_TIME);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                // 如果断路器是开的，那么 不会去调用，就不会有失败，就不会记录次数，没有必要清零，这个线程可以不执行
                if (this.status.equals(SimulatedHystrixStatus.CLOSE)) {
                    // 清零
                    this.currentFailCount.set(0);
                } else {
                    // 半开或者开，不需要去记录次数， 这个线程可以不工作
                    synchronized (lock) {
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }
            }
        });
    }

    /**
     * 记录失败次数
     */
    public void addFailCount() {
        // ++ i
        // getAndIncrement ++ i
        int i = currentFailCount.incrementAndGet();
        if (i >= MAX_FAIL_COUNT) {
            // 大于说明已经到达阈值
            // 修改当前状态为OPEN
            this.setStatus(SimulatedHystrixStatus.OPEN);

            // 当断路器打开之后，就不能去访问了，需要将他变成半开
            // 等待一个时间窗口，让断路器变成半开
            poolExecutor.execute(() -> {
                try {
                    TimeUnit.SECONDS.sleep(WINDOW_TIME);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                // 将状态设置为半开
                this.setStatus(SimulatedHystrixStatus.HALF_OPEN);
                // 重置失败次数
                this.currentFailCount.set(0);
            });
        }
    }
}
