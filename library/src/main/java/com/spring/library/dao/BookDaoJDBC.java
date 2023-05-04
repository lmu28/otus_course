package com.spring.library.dao;

import com.spring.library.dao.ext.BookResultSetExtractor;
import com.spring.library.domain.Author;
import com.spring.library.domain.Book;
import com.spring.library.domain.Genre;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;



//TODO избавиться от n+1 проблемы
@Repository
public class BookDaoJDBC implements BookDao {

    private final NamedParameterJdbcOperations jdbcOperations;
    private final GenreDao genreDao;
    private final AuthorDao authorDao;

    private final BookResultSetExtractor bookResultSetExtractor;

    public BookDaoJDBC(NamedParameterJdbcOperations jdbcOperations, GenreDao genreDao,AuthorDao authorDao,BookResultSetExtractor bookResultSetExtractor) {
        this.bookResultSetExtractor = bookResultSetExtractor;
        this.authorDao = authorDao;
        this.jdbcOperations = jdbcOperations;
        this.genreDao = genreDao;
    }





    @Override
    public void insert(Book book) {
        addAuthorIfNotExists(book.getAuthor());
        KeyHolder keyHolder = new GeneratedKeyHolder();

        MapSqlParameterSource parameters = new MapSqlParameterSource()
                .addValue("name", book.getName())
                .addValue("author_id", book.getAuthor().getId());

        jdbcOperations.update(
                "insert into book(name, author_id) values (:name, :author_id)",
                parameters, keyHolder, new String[]{"id"});
        //new String[]{"id"} - передаем автогенерируемые  столбцы
        int bookId = keyHolder.getKey().intValue();
        book.setId(bookId);
        insertGenresForCurrentBook(bookId, book.getGenres());
    }





    @Override
    public Book getById(int id) {

        Map<Long,Book> bookMap = jdbcOperations.query("select b.id as b_id,b.name as b_name," +
                "a.id as a_id,a.name as a_name,g.id as g_id,g.name as g_name from book as b" +
                " left  join author as a on (b.author_id = a.id)" +
                " left  join genre as g on (g.book_id = b.id)" +
                " where b.id = :id ;",Map.of("id",id), bookResultSetExtractor);

        return bookMap.get((long)id);

//
    }

    @Override
    public List<Book> getAll() {
        Map<Long,Book> bookMap = jdbcOperations.query("select b.id as b_id,b.name as b_name," +
                "a.id as a_id,a.name as a_name,g.id as g_id,g.name as g_name from book as b" +
                " left join author as a on (b.author_id = a.id)" +
                " left join genre as g on (g.book_id = b.id)", bookResultSetExtractor);

        return new ArrayList<>(Objects.requireNonNull(bookMap).values());
    }



    @Override
    public void deleteById(int id) {
        genreDao.deleteByBookId(id);
        jdbcOperations.update("delete  from book where id = :id", Map.of("id",id));
    }
    @Override
    public void update(Book book) {

        addAuthorIfNotExists(book.getAuthor());
        jdbcOperations.update("update book set name = :name, author_id = :author_id " +
                        "where id = :id"
                ,Map.of("id",book.getId(),"name",book.getName(),"author_id", book.getAuthor().getId()));

        clearGenresForCurrentBook(book);
        insertGenresForCurrentBook(book.getId(),book.getGenres());

    }



    private void clearGenresForCurrentBook(Book book){
        genreDao.deleteByBookId(book.getId());
    }
    private void insertGenresForCurrentBook(int bookId,List<Genre> genres){
        for (Genre genre: genres){
            genre.setBook_id(bookId);
        }
        genreDao.insertAll(genres);

    }

    private void addAuthorIfNotExists(Author author){//корректность автора проверяется в Service
        Author authorInDataBase = authorDao.getByAllParametersExcludeId(author);
        if(authorInDataBase == null){
            authorDao.insert(author);
        }

    }

    private void getGenresForBook(Book book){
        List<Genre> genres = genreDao.getAll();
        for (Genre genre: genres){
            if (book.getId() == genre.getBook_id()){
                book.getGenres().add(genre);
            }
        }
    }

    private class BookMapper implements RowMapper<Book> {
        @Override
        public Book mapRow(ResultSet rs, int rowNum) throws SQLException {


            return new Book(rs.getInt("id"), rs.getString("name"),
                    authorDao.getById(rs.getInt("author_id")),new ArrayList<>());

        }
    }
}
