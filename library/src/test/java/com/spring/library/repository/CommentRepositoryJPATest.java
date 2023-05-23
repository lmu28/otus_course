package com.spring.library.repository;

import com.spring.library.domain.Book;
import com.spring.library.domain.Comment;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.ComponentScan;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;


@DataJpaTest()
@ComponentScan("com.spring.library.repository")
class CommentRepositoryJPATest {

    public static final int FIRST_BOOK_ID = 1;
    public static final int FIRST_COMMENT_ID = 1;
    private Comment newComment;
    private Book book1;

    private static final String newBody = "Так себе книга";
    @Autowired
    TestEntityManager entityManager;
    @Autowired
    CommentRepository commentRepository;




    @BeforeEach
    void init(){
        book1 = entityManager.find(Book.class, FIRST_BOOK_ID);
        newComment = new Comment(0,"Очень интересная книга!",book1);
        newComment = commentRepository.save(newComment);
        entityManager.detach(book1);
        entityManager.detach(newComment);

    }



    @Test
    void save() {
        book1  = entityManager.find(Book.class, FIRST_BOOK_ID);
        newComment = entityManager.find(Comment.class, newComment.getId());
        int actualId = newComment.getId();
        String actualBody = newComment.getBody();
        Book actualBook = newComment.getBook();
        assertThat(entityManager.find(Comment.class,newComment.getId()))
                .matches(c-> c.getId() == actualId)
                .matches(c-> c.getBody().equals(actualBody))
                .matches(c-> c.getBook().toString().equals(actualBook.toString()));
        assertThat(entityManager.find(Book.class,FIRST_BOOK_ID).getComments()).containsOnlyOnce(newComment);
    }

    @Test
    void findById() {
        assertThat(entityManager.find(Comment.class, FIRST_COMMENT_ID))
                .isEqualTo(commentRepository.findById(FIRST_COMMENT_ID));
    }

    @Test
    void updateBodyById() {
        Comment comment1 = entityManager.find(Comment.class,FIRST_COMMENT_ID);


        commentRepository.updateBodyById(FIRST_COMMENT_ID,newBody);

        assertThat(entityManager.find(Comment.class,FIRST_BOOK_ID).getBody())
                .isEqualTo(comment1.getBody());

        entityManager.detach(comment1);

        assertThat(entityManager.find(Comment.class,FIRST_BOOK_ID).getBody())
                .isEqualTo(newBody);



    }

    @Test
    void deleteById() {
        assertThat(entityManager.find(Comment.class,FIRST_COMMENT_ID)).isNotNull();
        commentRepository.deleteById(FIRST_COMMENT_ID);
        entityManager.clear();
        assertThat(entityManager.find(Comment.class,FIRST_COMMENT_ID)).isNull();
    }
}