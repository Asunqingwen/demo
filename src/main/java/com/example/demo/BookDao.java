package com.example.demo;

import com.example.demo.bean.Book;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

//<Book,String>类名，主键的数据类型
//<Book,Integer>
public interface BookDao extends MongoRepository<Book, String> {
    //查询作者包含某文字的所有书籍
    List<Book> findByAuthorContains(String author);

    //根据书名查询指定书籍
    Book findByNameEquals(String nameMongoRepository);
}
