package com.spring.library.repository;

import com.spring.library.domain.Genre;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@Import({BookRepositoryJPA.class, CommentRepositoryJPA.class, GenreRepositoryJPA.class})

class GenreRepositoryJPATest {

    public static final int FIRST_GENRE_ID = 1;
    @Autowired
    GenreRepository genreRepository;

    @Autowired
    TestEntityManager entityManager;

    @Test
    void deleteById() {
        Genre genre = entityManager.find(Genre.class, FIRST_GENRE_ID);
        assertThat(genre).isNotNull();
        entityManager.detach(genre);
        genreRepository.deleteById(FIRST_GENRE_ID);
        assertThat(entityManager.find(Genre.class, FIRST_GENRE_ID)).isNull();

    }
}