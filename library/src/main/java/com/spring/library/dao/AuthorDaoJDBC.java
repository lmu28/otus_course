package com.spring.library.dao;

import com.spring.library.domain.Author;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

@Repository
public class AuthorDaoJDBC implements AuthorDao{

    NamedParameterJdbcOperations jdbcOperations;

    public AuthorDaoJDBC(NamedParameterJdbcOperations jdbcOperations) {
        this.jdbcOperations = jdbcOperations;
    }

    @Override
    public Author getById(int id) {
        return jdbcOperations.queryForObject("select * from author where id = :id",
                Map.of("id",id),new AuthorMapper());
    }


    @Override
    public Author getByAllParametersExcludeId(Author author) {
        Author authorFromDB = null;
        try{
            authorFromDB = jdbcOperations.queryForObject("select * from author where `name` = :name", Map.of("name",author.getName()),new AuthorMapper());
        }catch (EmptyResultDataAccessException e){
            return null;
        }
        return authorFromDB;

    }

    @Override
    public void insert(Author author) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("name",author.getName());
        jdbcOperations.update("insert into author(`name`) " +
                "values (:name)",params,keyHolder,new String[]{"id"});
        int authorId = keyHolder.getKey().intValue();
        author.setId(authorId);
    }

    private static class AuthorMapper implements RowMapper<Author>{
        @Override
        public Author mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new Author(rs.getInt(1),rs.getString(2));
        }
    }
}
