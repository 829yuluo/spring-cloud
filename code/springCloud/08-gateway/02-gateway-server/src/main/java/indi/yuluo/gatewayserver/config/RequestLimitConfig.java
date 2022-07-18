package indi.yuluo.gatewayserver.config;

import org.springframework.cloud.gateway.filter.ratelimit.KeyResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import reactor.core.publisher.Mono;

/**
 * @Author: yuluo
 * @CreateTime: 2022-07-16  18:15
 * @Description: TODO
 */

@Configuration
public class RequestLimitConfig {

    // 针对某一个接口 ip来限流 每一个ip只能访问3次
    // Bean的名字可以设置为方法名 可以任意改
    @Bean("ipKeyResolver")
    @Primary   // 主候选的配置类
    public KeyResolver ipKeyResolver() {
        return exchage -> Mono.just(exchage.getRequest().getHeaders().getHost().getHostName());
    }

    // 针对这个路径来限制 这个路径每秒只能访问3次
    // api 就是 接口外面一般把gateway 当作网关
    @Bean
    public KeyResolver apiKeyResolver() {
        return exchange -> Mono.just(exchange.getRequest().getPath().value());
    }

}
