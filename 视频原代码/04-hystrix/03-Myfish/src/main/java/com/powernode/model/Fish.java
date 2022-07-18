package com.powernode.model;

import lombok.Data;

import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.LockSupport;

/**
 * 这个是断路器的模型
 */
@Data
public class Fish {

    /**
     * 窗口时间
     */
    public static final Integer WINDOW_TIME = 20;

    /**
     * 最大失败次数
     */
    public static final Integer MAX_FAIL_COUNT = 3;


    /**
     * 断路器中有它自己的状态
     */
    private FishStatus status = FishStatus.CLOSE;

    /**
     * 当前这个断路器失败了几次
     * i++
     * AtomicInteger 可以保证线程安全
     */
    private AtomicInteger currentFailCount = new AtomicInteger(0);

    /**
     * 线程池
     */
    private ThreadPoolExecutor poolExecutor = new ThreadPoolExecutor(
            4,
            8,
            30,
            TimeUnit.SECONDS,
            new LinkedBlockingQueue<>(2000),
            Executors.defaultThreadFactory(),
            new ThreadPoolExecutor.AbortPolicy()
    );


    private Object lock = new Object();

    {
        poolExecutor.execute(() -> {
            // 定期删除
            while (true) {
                try {
                    TimeUnit.SECONDS.sleep(WINDOW_TIME);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                // 如果断路器是开的 那么 不会去调用  就不会有失败  就不会记录次数 没有必要清零  这个线程可以不执行
                if (this.status.equals(FishStatus.CLOSE)) {
                    // 清零
                    this.currentFailCount.set(0);
                } else {
                    // 半开或者开 不需要去记录次数 这个线程可以不工作
                    // 学过生产者 消费者模型  wait notifyAll  condition singleAll await   它们只能随机唤醒某一个线程
                    // lock锁 源码  CLH 队列 放线程 A B C D E  park unpark  可以 唤醒指定的某一个线程
//                    LockSupport.park();
//                    LockSupport.unpark();
                    synchronized (lock) {
                        try {
                            lock.wait();
                            System.out.println("我被唤醒了,开始工作");
                        } catch (InterruptedException e) {
                            e.printStackTrace();
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
        int i = currentFailCount.incrementAndGet();  // ++i
        if (i >= MAX_FAIL_COUNT) {
            // 说失败次数已经到了阈值了
            // 修改当前状态为 open
            this.setStatus(FishStatus.OPEN);
            // 当断路器打开以后  就不能去访问了  需要将他变成半开
            // 等待一个时间窗口  让断路器变成半开
            poolExecutor.execute(() -> {
                try {
                    TimeUnit.SECONDS.sleep(WINDOW_TIME);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                this.setStatus(FishStatus.HALF_OPEN);
                // 重置失败次数  不然下次进来直接就会打开断路器
                this.currentFailCount.set(0);
            });
        }
    }
}
