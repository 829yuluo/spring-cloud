package indi.yuluo.gatewayservers.filter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * @Author: yuluo
 * @CreateTime: 2022-07-16  15:50
 * @Description: token校验
 */

@Component
public class TokenCheckFilter implements GlobalFilter, Ordered {

    /*注入redis*/
    @Autowired
    private StringRedisTemplate redisTemplate;

    /* 指定好放行的路径 */
    public static final List<String> ALLOW_URL = Arrays.asList(
            // "/teacher-service/teach",
            "/teacher-login-service/doLogin"
    );

    /**
     *
     * 一般token放在请求头中，一般 key是Authorization（授权） value是bearer token  行业约定带bearer
     * 1、拿到url
     * 2、判断放行
     * 3、拿到请求头
     * 4、拿到token
     * 5、校验
     * 6、放行或拦截
     *
     * @param exchange
     * @param chain
     * @return
     */
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {

        // 获取url
        ServerHttpRequest request = exchange.getRequest();
        String path = request.getURI().getPath();
        // 校验uri
        if (ALLOW_URL.contains(path)) {
            // 直接放行
            return chain.filter(exchange);
        }

        // 检查
        HttpHeaders headers = request.getHeaders();
        List<String> authorization = headers.get("Authorization");
        if (!CollectionUtils.isEmpty(authorization)) {
            String token = authorization.get(0);
            if (StringUtils.hasText(token)) {
                // 约定好有前缀，bearer token 替换bearer为”
                String realToken = token.replaceFirst("bearer ", "");
                System.out.println(redisTemplate.hasKey(realToken));
                if (StringUtils.hasText(token) && redisTemplate.hasKey(realToken)) {
                    return chain.filter(exchange);
                }
            }
        }

        // 拦截
        ServerHttpResponse response = exchange.getResponse();
        response.getHeaders().set("content-type", "application/json;charset=utf-8");
        HashMap<Object, Object> map = new HashMap<>();
        // 返回401
        map.put("code", HttpStatus.UNAUTHORIZED.value());
        map.put("msg", "您没有该网站的使用权限！未授权！");

        // 序列化
        ObjectMapper objectMapper = new ObjectMapper();
        byte[] bytes = new byte[0];
        try {
            bytes = objectMapper.writeValueAsBytes(map);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        // 通过响应流的形式响应给response
        DataBuffer wrap = response.bufferFactory().wrap(bytes);

        // 响应到前端
        return response.writeWith(Mono.just(wrap));
    }

    @Override
    public int getOrder() {

        return -1;
    }
}
