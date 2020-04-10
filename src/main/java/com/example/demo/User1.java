package com.example.demo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Getter
@Setter
@ToString
public class User1 implements Serializable {
    private Integer id;
    private String name;
    private Integer age;
}
