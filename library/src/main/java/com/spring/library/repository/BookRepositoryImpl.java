package com.spring.library.repository;

import com.spring.library.domain.Book;
import com.spring.library.dto.BookGenresCount;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Repository;

import java.util.List;


//!Название обязательно BookRepository + Impl !
@Repository
public class BookRepositoryImpl implements BookRepositoryBase{

    private final MongoTemplate mongoTemplate;

    public BookRepositoryImpl(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    public List<BookGenresCount> getBookGenresCount() {
        Aggregation aggregation = Aggregation.newAggregation(
                Aggregation.match(Criteria.where("field").is("value")),
                Aggregation.group("groupField").count().as("count")
        );


        AggregationResults<BookGenresCount> results = mongoTemplate.aggregate(
                aggregation, "book", BookGenresCount.class
        );

        List<BookGenresCount> resultList = results.getMappedResults();



        return null;

    }
}
