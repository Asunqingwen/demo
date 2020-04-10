package com.example.demo;

import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.Map;

@RestController //告诉spring以字符串形式渲染结果
public class HelloController {
    //    @Value("${my.name}")
//    String name;
    @Autowired
//    My my;
            Users users;

//    @GetMapping("/hello")  //提供路由信息，告诉spring，任何来自“/hello”路径的HTTP的get请求都被映射到hello方法
//    public String hello() {
////        return my.getName() + " : " + my.getAge() + " : " + my.getJob() + my.getFavorites();
//        String result = "";
//        for (User user : users.getUsers()) {
//            result += user.getName() + " : " + user.getAge() + "<br>";
//        }
//        return result;
//    }

    @GetMapping("/world")
    public Book world() {
        Book book = new Book();
        book.setName("Spring Boot开发指南");
        book.setAuthor("mvtech");
        book.setPrice(50f);
        book.setPublicationDate(new Date());
        return book;
    }

    //参数在路径里
    @GetMapping("/hello/{name}")
    public String hello(@PathVariable("name") String name) {
        return "获取到的name是：" + name;
    }

    //参数在?后面
    @GetMapping("/hello1")
    public String hello1(@RequestParam(value = "name", required = false) String name) {
        return "获取到的name是：" + name;
    }

    //map接收参数
    @GetMapping("/hello2")
    public String hello(@RequestParam Map<String, Object> params) {
        return "name: " + params.get("name") + "<br>age:" + params.get("age");
    }

    //切面编程
    @Autowired
    UserService userService;

    @GetMapping("/test")
    public String test(Integer id) {
        return userService.getUserById(id);
    }
}
