package com.example.demo.mapper2;

import com.example.demo.User;

import java.util.List;

public interface UserMapper2 {
    int addUser(User user);

    int updateUser(User user);

    int deleteUserById(Integer id);

    User getUserById(Integer id);

    List<User> getAllUsers();
}
