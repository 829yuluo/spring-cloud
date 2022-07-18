package indi.yuluo.userserviceprovider.controller;

import indi.yuluo.userserviceprovider.feign.UserOrderFeign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @Author: yuluo
 * @CreateTime: 2022-07-12  08:53
 * @Description: TODO
 */

@RestController
public class UserController {

    // 注入feign  （UserOrderFeign）
    @Autowired
    private UserOrderFeign userOrderFeign;

    @GetMapping("/userDoOrder")
    public String UserDoOrder() {
        System.out.println("有用户下单了");

        /*
        这里需要一个远程调用
        根据官网，需要接口和注解去使用feign
        在application启动类中加入@EnableFeignClients注解，并且创建一个feign包，写入相应内容

        总结：调用流程
        浏览器（前端）----> user-service-provider(/userDoOrder) -----> RPC ----> oder-service-provider(/doOrder)
         */
        String s = userOrderFeign.doOrder();

        return s;
    }

    /**
     * 手写feign接口的代理对象  jdk invoke
     * @param args
     */
    public static void main(String[] args) {
        UserOrderFeign userOrderFeign1 = (UserOrderFeign) Proxy.newProxyInstance(UserController.class.getClassLoader(), new Class[]{UserOrderFeign.class}, new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                System.out.println("进入代理对象 invoke方法里面");

                // 如果能在这拿到对方的ip和port  并且拿到这个方法上面的注解里面的值，feign接口的功能就完成了

                // 获取feign接口里的方法
                String name = method.getName();
                // 获取方法上的路径注解
                GetMapping annotation = method.getAnnotation(GetMapping.class);
                // 取出值
                String[] paths = annotation.value();
                String path = paths[0];
                // 路径拼接
                // String url = "http://" + "/" + path;

                // 调用ribbon去eureka的服务列表找到相应的服务（在feign接口上的@FeignClient(value = "")）

                // 获取类
                Class<?> declaringClass = method.getDeclaringClass();
                String name1 = declaringClass.getName();
                FeignClient annotation1 = declaringClass.getAnnotation(FeignClient.class);
                String valueApplicationName = annotation1.value();
                String url = "http://" + valueApplicationName + "/" + path;

                // 调用RestTemplate去发送请求，完成远程调用
                RestTemplate restTemplate = new RestTemplate();
                String forObject = restTemplate.getForObject(url, String.class);

                return forObject;
            }
        });

        // 通过代理创建出来的对象调用doOrder方法  会走invoke方法
        String s = userOrderFeign1.doOrder();
        System.out.println(s);
    }

}
