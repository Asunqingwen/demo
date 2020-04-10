package com.example.demo;

import com.example.demo.mapper1.UserMapper1;
import com.example.demo.mapper2.UserMapper2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class DataController {

    @Autowired
    UserMapper1 userMapper1;
    @Autowired
    UserMapper2 userMapper2;

    @RequestMapping(value = "/database", method = RequestMethod.GET)
    public void database() {
        // 新增数据
        User user1 = new User();
        user1.setUsername("hangge");
        user1.setPassword("123456");
        int i1 = userMapper1.addUser(user1);
        System.out.println("插入一条数据>>>" + i1);

        // 修改数据
        User user2 = new User();
        user2.setId(1);
        user2.setPassword("888888");
        user2.setUsername("mvtech");
        int i2 = userMapper1.updateUser(user2);
        System.out.println("更新一条数据>>>" + i2);

        // 删除数据
        int i3 = userMapper1.deleteUserById(12);
        System.out.println("删除一条数据>>>" + i3);

        // 查询单条数据
        User user4 = userMapper1.getUserById(1);
        System.out.println("查询1条数据>>>" + user4.toString());

        // 查询多条数据
        List<User> users = userMapper1.getAllUsers();
        System.out.println("查询多条数据>>>" + users);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/database2")
    public void database2() {
        List<User> users1 = userMapper1.getAllUsers();
        List<User> users2 = userMapper2.getAllUsers();

        System.out.println("数据库1>>>users>>>" + users1);
        System.out.println("数据库2>>>users>>>" + users2);
    }
}
