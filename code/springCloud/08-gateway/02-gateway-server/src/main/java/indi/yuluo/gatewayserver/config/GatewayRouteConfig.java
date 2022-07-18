package indi.yuluo.gatewayserver.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: yuluo
 * @CreateTime: 2022-07-15  11:34
 * @Description: java Bean的配置路由方法
 */

@Configuration
public class GatewayRouteConfig {
    @Bean
    public RouteLocator routeLocator(RouteLocatorBuilder routeLocatorBuilder) {

        RouteLocatorBuilder.Builder routes = routeLocatorBuilder.routes();

        /**
         * 如果访问 http://localhost:80/guonei
         * 浏览器会访问 http://news.baidu.com/guonei
         */
        routes.route("path_rote_guonei", r -> r.path("/guonei").uri("http://news.baidu.com/guonei"))
                .route("path_rote_guoji", r -> r.path("/guoji").uri("http://news.baidu.com/guoji"))
                .route("path_rote_tech", r -> r.path("/tech").uri("http://news.baidu.com/tech"))
                .route("path_rote_lady", r -> r.path("/lady").uri("http://news.baidu.com/lady"))
                .build();

        return routes.build();
    }
}