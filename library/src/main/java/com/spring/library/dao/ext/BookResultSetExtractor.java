package com.spring.library.dao.ext;

import com.spring.library.dao.AuthorDao;
import com.spring.library.dao.GenreDao;
import com.spring.library.domain.Author;
import com.spring.library.domain.Book;
import com.spring.library.domain.Genre;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


@Component
public class BookResultSetExtractor implements
        ResultSetExtractor<Map<Long, Book>> {



    @Override
    public Map<Long, Book> extractData(ResultSet rs) throws SQLException,
            DataAccessException {

        Map<Long, Book> books = new HashMap<>();
        while (rs.next()) {
            int id = rs.getInt("b_id");
            Book book = books.get((long)id);
            if (book == null) {
                String bookName = rs.getString("b_name");
                int authorId = rs.getInt("a_id");
                String authorName = rs.getString("a_name");
                book = new Book(id,bookName,new Author(authorId,authorName),new ArrayList<>());
                books.put((long)book.getId(), book);
            }
            int genreId =rs.getInt("g_id");
            String genreName =rs.getString("g_name");
            book.getGenres().add(new Genre(genreId,genreName,id));



        }
        return books;

    }
}
