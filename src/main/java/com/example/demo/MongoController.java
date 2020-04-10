package com.example.demo;

import com.example.demo.bean.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class MongoController {
    @Autowired
    BookDao bookDao;

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

        bookDao.insert(books);
    }

    @RequestMapping("/findAll")
    public void findAll(){
        PageRequest pageable = PageRequest.of(1,2);
        Page<Book> page = bookDao.findAll(pageable);
        System.out.println("总页数："+page.getTotalPages());
        System.out.println("总记录数："+page.getTotalElements());
        System.out.println("查询结果："+page.getContent());
        System.out.println("当前页数："+page.getNumber()+1);
        System.out.println("当前记录数："+page.getNumberOfElements());
        System.out.println("每页记录数："+page.getSize());
    }

    @RequestMapping("/search")
    public void search(){
        List<Book> books = bookDao.findByAuthorContains("克");
        Book book = bookDao.findByNameEquals("时间的秩序");

        System.out.println("作者名包含'克'字的书籍：" + books);
        System.out.println("书名为'时间的秩序'的书籍：" + book);
    }
}
