package com.spring.library.dao;

import com.spring.library.domain.Book;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLOutput;
import java.util.List;
import java.util.Map;

@Repository
public class BookDaoJDBC implements BookDao {

    private final NamedParameterJdbcOperations jdbcOperations;

    public BookDaoJDBC(NamedParameterJdbcOperations jdbcOperations) {
        this.jdbcOperations = jdbcOperations;
    }

    @Override
    public void insert(Book book) {
        jdbcOperations.update("insert into book(`name`,`author_id`,`genre_id`)" +
                        "values ( :name,:author_id,:genre_id )",
                Map.of("name", book.getName(), "author_id", book.getAuthorId()
                        , "genre_id", book.getGenreId()));
    }

    @Override
    public Book getById(int id) {
        Book book = jdbcOperations.queryForObject("select * from book where id = :id"
                , Map.of("id", id), new BookMapper());
        if (book == null) {
            System.out.println("No book with id  " + id + " found");
        }
        return book;

    }

    @Override
    public List<Book> getAll() {
        return jdbcOperations.query("select * from book", new BookMapper());
    }

    @Override
    public void update(Book book) {
        jdbcOperations.update("UPDATE book " +
                "SET `name` = :name, `author_id` = :author_id, `genre_id` = :genre_id " +
                "WHERE id = :id;", Map.of("id", book.getId(), "name", book.getName(), "genre_id", book.getGenreId()
                , "author_id", book.getAuthorId()));

    }

    @Override
    public void deleteById(int id) {


        int deletedRows = jdbcOperations.update("delete from book where id = :id", Map.of("id", id));
        if (deletedRows == 0) {
            System.out.println(("No book with id " + id + " found for deletion"));
        }


    }

    private static class BookMapper implements RowMapper<Book> {
        @Override
        public Book mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new Book(rs.getInt("id"), rs.getString("name"),
                    rs.getInt("author_id"), rs.getInt("genre_id"));

        }
    }
}
