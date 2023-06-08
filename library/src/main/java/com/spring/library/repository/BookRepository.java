package com.spring.library.repository;

import com.spring.library.domain.Book;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends MongoRepository<Book,String>,BookRepositoryBase {



    List<Book> findByName(String name);

//    @Query("{$group: {_id: '$field', count: {$sum: 1}}}")
//    List<MyAggregationResult> performAggregation();
}


