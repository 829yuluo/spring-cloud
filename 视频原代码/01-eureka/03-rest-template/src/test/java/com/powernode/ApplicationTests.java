package com.powernode;

import com.powernode.domain.User;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.web.client.RestTemplate;

@SpringBootTest
class ApplicationTests {


    @Test
    void contextLoads() {
        // 在java代码中去发送一个请求 请求一个页面
        RestTemplate restTemplate = new RestTemplate();
        // 如果你访问的一个页面 会返回html代码
        String url = "https://www.baidu.com";
        String result = restTemplate.getForObject(url, String.class);
        System.out.println(result);
    }


    @Test
    void testGet() {
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:8080/testGet?name=cxs";
//        String result = restTemplate.getForObject(url, String.class);
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(url, String.class);
        // http:// 协议 (规范 接头暗号)
        // 请求头 请求参数 .. 响应头 响应状态码 报文 ....
        System.out.println(responseEntity);
    }


    @Test
    void testPost1(){
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:8080/testPost1";
        User user = new User("leige",22,10000D);
        // 发送post 而且是json参数 因为web里面默认使用jackson 他会把你的对象转成json字符串
        String result = restTemplate.postForObject(url, user, String.class);
        System.out.println(result);
    }


    @Test
    void testPost2(){
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:8080/testPost2";
        // 构建表单参数
        LinkedMultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
        map.add("name","tangge");
        map.add("age",26);
        map.add("price",8000D);

        String result = restTemplate.postForObject(url, map, String.class);
        System.out.println(result);


    }
}
