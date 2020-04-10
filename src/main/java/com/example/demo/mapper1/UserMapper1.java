package com.example.demo.mapper1;

import com.example.demo.User;

import java.util.List;

public interface UserMapper1 {
    int addUser(User user);

    int updateUser(User user);

    int deleteUserById(Integer id);

    User getUserById(Integer id);

    List<User> getAllUsers();
}
