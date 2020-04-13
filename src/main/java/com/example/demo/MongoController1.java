package com.example.demo;

import com.example.demo.bean.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class MongoController1 {
    @Autowired
    MongoTemplate mongoTemplate;

    @RequestMapping("/save")
    public void save(){
        List<Book> books = new ArrayList<Book>();

        Book b1 = new Book();
        b1.setName("时间的秩序");
        b1.setAuthor("卡洛。罗韦利");
        books.add(b1);

        Book b2 = new Book();
        b2.setName("克苏鲁神话");
        b2.setAuthor("H.P.洛夫克拉夫特");
        books.add(b2);

        Book b3 = new Book();
        b3.setName("筋膜拉伸");
        b3.setAuthor("克里斯。弗雷德里克");
        books.add(b3);

        mongoTemplate.insertAll(books);
    }

    @RequestMapping("/search")
    public void search(){
        List<Book> books = mongoTemplate.findAll(Book.class);
        Book book = mongoTemplate.findById(1,Book.class);

        System.out.println(books);
        System.out.println(book);
    }
}