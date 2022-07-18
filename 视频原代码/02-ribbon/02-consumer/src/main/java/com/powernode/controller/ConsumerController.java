package com.powernode.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class ConsumerController {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private LoadBalancerClient loadBalancerClient;

    /**
     * 思考 ribbon是怎么将 http://provider/hello 路径请求成功的
     * http://127.0.0.1:8080/hello
     * 1.拦截这个请求
     * 2.截取主机名称
     * 3.借助eureka来做服务发现 list<>
     * 4.通过负载均衡算法 拿到一个服务ip port
     * 5.reConstructURL
     * 6.发起请求
     *
     * @param serviceName
     * @return
     */
    @GetMapping("testRibbon")
    public String testRibbon(String serviceName){
        // 正常来讲 需要 拿到ip和port 以及 路径 才可以用
        // http://provider/hello
        String result = restTemplate.getForObject("http://" + serviceName + "/hello", String.class);
        // 只要你给restTemplate 加了ribbon的注解 项目中这个对象发起的请求 都会走ribbon的代理
        // 如果你想使用原生的restTemplate 就需要重新创建一个对象
//        RestTemplate myRest = new RestTemplate();
//        String forObject = myRest.getForObject("http://localhost:8888/aaa", String.class);
        return result;
    }


    // 轮训的算法 怎么去实现
    // 两台机器   A B
    // A
    // B
    // A
    // B
    // 代码实现轮训的算法  List<机器>
    // 请求次数
    //  int index =   1 % size    list.get(index);
    // % 取模 取余好处是一个周期函数 让得到的结果 总是小于 除数的
    //  1 / 2    1 % 2
    // 1%2=1
    // 2%2=0
    // 3%2=1
    // 4%2=0
    // 全局顶一个int i = 0
    // i++  线程不安全的
    // i % size
    // 怎么能做一个线程安全的轮训算法   加锁 效率极低  CAS 自旋锁 没有线程的等待和唤醒的开销
    // CAS 优点 性能好 java层面无锁的状态  但是在jvm层面 有锁的cmpxchg
    // CAS 缺点 会导致短暂时间内 CPU 飙升  还有ABA 问题


    /**
     * 核心是负载均衡
     * @param serviceName
     * @return
     */
    @GetMapping("testRibbonRule")
    public String testRibbonRule(String serviceName){
        ServiceInstance choose = loadBalancerClient.choose(serviceName);
        return choose.toString();
    }

}
