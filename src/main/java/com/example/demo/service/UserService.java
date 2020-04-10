package com.example.demo.service;

import org.springframework.stereotype.Service;

@Service
public class UserService {
    public String getUserById(Integer id) {
        System.out.println("getUserById(" + id + ")...");
        //等待两秒
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "mvtech";
    }
}
