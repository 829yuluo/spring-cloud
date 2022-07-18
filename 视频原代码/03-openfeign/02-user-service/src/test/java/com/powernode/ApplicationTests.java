package com.powernode;

import com.powernode.controller.UserController;
import com.powernode.feign.UserOrderFeign;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

@SpringBootTest
class ApplicationTests {
    @Autowired
    private RestTemplate restTemplate;

    /**
     * 手写feign的核心步骤
     */
    @Test
    void contextLoads() {
        UserOrderFeign o = (UserOrderFeign) Proxy.newProxyInstance(UserController.class.getClassLoader(), new Class[]{UserOrderFeign.class}, new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                // 能去拿到对方的ip和port 并且拿到这个方法上面的注解里面的值 那么就完事了
                GetMapping annotation = method.getAnnotation(GetMapping.class);
                String[] paths = annotation.value();
                String path = paths[0];
                Class<?> aClass = method.getDeclaringClass();
                FeignClient annotation1 = aClass.getAnnotation(FeignClient.class);
                String applicationName = annotation1.value();
                String url = "http://" + applicationName + "/" + path;
                String forObject = restTemplate.getForObject(url, String.class);
                return forObject;
            }
        });
        String s = o.doOrder();
        System.out.println(s);
    }

}
