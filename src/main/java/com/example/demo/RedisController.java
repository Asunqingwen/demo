package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RedisController {
    @Autowired
    RedisTemplate redisTemplate; //默认提供的用来操作对象的redis操作实例

    @Autowired
    StringRedisTemplate stringRedisTemplate; //默认提供用来操作字符串的redis操作实例

    @RequestMapping("/redisTest")
    public void redisTest() {
        //保存一个字符串
        ValueOperations<String, String> ops1 = stringRedisTemplate.opsForValue();
        ops1.set("message", "欢迎访问mvtech.com");

        //读取一个字符串
        String message = ops1.get("message");
        System.out.println("读取一个字符串：" + message);

        //保存一个对象
        ValueOperations ops2 = redisTemplate.opsForValue();
        User1 u1 = new User1();
        u1.setId(1);
        u1.setName("mvtech");
        u1.setAge(100);
        ops2.set("user1", u1);

        //读取一个对象
        User1 u2 = (User1) ops2.get("user1");
        System.out.println("读取一个对象：" + u2.toString());
    }
}
