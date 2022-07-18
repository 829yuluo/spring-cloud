package indi.yuluo.gatewayserver.filter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * @Author: yuluo
 * @CreateTime: 2022-07-15  16:55
 * @Description:
 *
 * 网关里面，过滤器
 * ip拦截
 * 请求都有一个源头，
 * 比如 电话:181 183 122 190……
 * 请求执行的顺序 请求 ---> gateway ---> service
 * 黑名单：不放行的请求  black_list
 * 白名单：放行的请求  white_list
 * 根据请求数量判断是黑名单还是白名单
 * 像具体的业务订单，一般是黑名单
 * 一般像数据库，服务器等用白名单
 *
 */

@Component
public class IpCheckFilter implements GlobalFilter, Ordered {

    /**
     * 网关的并发量比较高，不要在网关里面直接操作mysql
     * 后台系统可以查数据库 在用户量和并发量不大的情况下
     * 如果并发量大，去查redis 在内存中写好
     */
    private static final List<String> BLACK_LIST = Arrays.asList(
            "127.0.0.1",
            "144.128.232.147"
    );

    /**
     * 拿到ip，
     * 检验ip是否符合规范
     * 放行/拦截
     *
     * @param exchange
     * @param chain
     * @return
     */
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {

        ServerHttpRequest request = exchange.getRequest();
        // 获取ip地址
        String ip = request.getHeaders().getHost().getHostString();
        // 查询数据库，看这个ip是否是在黑名单里面，mysql数据库的并发
        // 只要能存放数据的地方都是数据库 redis mysql
        if (!BLACK_LIST.contains(ip)) {
            // 如果包含就拦截，不包含就放行
            return chain.filter(exchange);
        }
        // 拦截 设置响应
        ServerHttpResponse response = exchange.getResponse();
        response.getHeaders().set("content-type", "application/json;charset=utf-8");
        HashMap<Object, Object> map = new HashMap<>();
        map.put("code", HttpStatus.UNAUTHORIZED.value());
        map.put("mag", "你未授权被系统的使用权限！是黑名单");

        ObjectMapper objectMapper = new ObjectMapper();
        byte[] bytes = new byte[0];
        try {
            bytes = objectMapper.writeValueAsBytes(map);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        DataBuffer wrap = response.bufferFactory().wrap(bytes);
        Mono<Void> mono = response.writeWith(Mono.just(wrap));

        return mono;


    }

    @Override
    public int getOrder() {
        return -5;
    }
}
