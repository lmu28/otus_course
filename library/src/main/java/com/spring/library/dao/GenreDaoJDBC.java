package com.spring.library.dao;

import com.spring.library.domain.Genre;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@Repository
public class GenreDaoJDBC implements GenreDao{

    NamedParameterJdbcOperations jdbcOperations;

    public GenreDaoJDBC(NamedParameterJdbcOperations jdbcOperations) {
        this.jdbcOperations = jdbcOperations;
    }

    @Override
    public List<Genre> getAll() {
        return jdbcOperations.query("select * from genre", new GenreRowMapper());
    }


    @Override
    public Genre getById(int id) {
        return jdbcOperations.queryForObject("select * from genre where id = :id",
                Map.of("id",id),new GenreRowMapper());
    }


    @Override
    public void insert(Genre genre) {
        jdbcOperations.update("insert into genre(`name`,`book_id`)" +
                "values(:name,:book_id)",Map.of("name",genre.getName(),"book_id", genre.getBook_id()));
    }

    @Override
    public void insertAll(List<Genre> genres) {
        String query = buildQueryWithAllGenres(genres);
        jdbcOperations.update(query,Map.of());
    }

    @Override
    public void deleteByBookId(int bookID) {
        jdbcOperations.update("delete from genre where book_id = :book_id"
                ,Map.of("book_id",bookID));

    }

    @Override
    public List<Genre> getByBookId(int bookID) {

        return jdbcOperations.query("select * from genre where book_id = :book_id"
                ,Map.of("book_id",bookID),new GenreRowMapper());

    }

    private String buildQueryWithAllGenres(List<Genre> genres){
        StringBuilder query = new StringBuilder("insert into genre(`name`,`book_id`)values");
        for (int i = 0; i < genres.size();i++){
            Genre genre = genres.get(i);
            query.append("(");
            query.append("'");
            query.append(genre.getName());
            query.append("'");
            query.append(",");
            query.append("'");
            query.append(genre.getBook_id());
            query.append("'");
            if (i != genres.size()-1){
                query.append("),");
            }else {
                query.append(");");
            }

        }

        return query.toString();

    }

    private static class GenreRowMapper implements RowMapper<Genre> {

        @Override
        public Genre mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new Genre(rs.getInt(1),rs.getString(2),rs.getInt(3));
        }
    }

}
