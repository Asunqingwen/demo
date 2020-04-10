package com.example.demo.bean;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;

@Getter
@Setter
@ToString
public class Book {
    //设置主键
//    @Id
//    private String id;
    private String name;
    private String author;
}
