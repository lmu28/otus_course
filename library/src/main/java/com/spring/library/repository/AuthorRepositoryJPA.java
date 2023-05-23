package com.spring.library.repository;

import com.spring.library.domain.Author;
import com.spring.library.domain.Book;
import com.spring.library.dto.AuthorBooks;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;


@Repository
@Transactional
public class AuthorRepositoryJPA implements AuthorRepository{

    @PersistenceContext
    EntityManager entityManager;

    private final BookRepository bookRepository;

    public AuthorRepositoryJPA(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public Author save(Author author) {
        if (author.getId() == 0){
           entityManager.persist(author);
            return author;
        }
        return entityManager.merge(author);
    }

    @Override
    public Author findById(int id) {
        TypedQuery<Author> query = entityManager.createQuery("select a from Author a" +
                " where a.id = :id", Author.class);
        query.setParameter("id",id);
        return query.getSingleResult();
    }


    @Override
    public void deleteById(int id) {

        //bookRepository.deleteByAuthorId(id);
        Query query = entityManager.createQuery("delete from Author a where a.id = :id");
        query.setParameter("id",id);
        query.executeUpdate();

    }


    @Override
    public int calcAuthorCount() {
        Long res = (Long)entityManager.createQuery("select count(a) from Author a").getSingleResult();
        return res.intValue();
    }

//    @Override
//    public List<Author> findAuthorWithGivenBooks(String bookName1, String bookName2) {
//        TypedQuery<Author> query = entityManager
//                .createQuery("select a from Author a join Book b1 join Book b2 " +
//                        "where b1.name = :name1 and b2.name = :name2",Author.class);
//        query.setParameter("name1", bookName1);
//        query.setParameter("name2", bookName2);
//
//        return query.getResultList();
//    }

    @Override
    public List<Author> findAuthorWithGivenBooks(String bookName1, String bookName2) {
        TypedQuery<Author> query = entityManager
                .createQuery("select a from Author a join a.books b1 join a.books b2 " +
                        "where b1.name = :name1 and b2.name = :name2",Author.class);
        query.setParameter("name1", bookName1);
        query.setParameter("name2", bookName2);

        return query.getResultList();
    }


    @Override
    public List<Author> findAuthorWithGivenNames(List<String> names) {
        TypedQuery<Author> query = entityManager
                .createQuery("select a from Author a " +
                        "where a.name in :names",Author.class);
        query.setParameter("names",names);

        return query.getResultList();
    }

//    @Override
//    public List<Author> findAuthorsWithBookCountMoreThanAllEmployees() {
//        TypedQuery<Author> query = entityManager
//                .createQuery("select a from Author a where a.id in" +
//                        "(select b.author.id from Book b group by b.author.id " +
//                        "having count (b.id) =" +
//                        "(select max(subq.count_books) from" +
//                        "(select count(b.id) count_books from Book b group by b.author.id ) as subq))",Author.class);
//        return query.getResultList();
//    }
//
//


    @Override
    public List<Author> findAuthorsWithBookCountMoreThanAllEmployees() {

        List<AuthorBooks> authorBooks = entityManager
                .createQuery(" select new com.spring.library.dto.AuthorBooks(a,count(b))" +
                        " from Author a join a.books b group by a order by count (b) desc ",AuthorBooks.class).getResultList();



        return getListAuthorsWithMaxBooksCount(authorBooks);
    }




//    select * from author a where a.id in (
//            select b.author_id from book b  group by b.author_id having(count(b.id) =(select max(subq.count_books) from
//(select count(b.id) count_books from book b  group by b.author_id ) as subq) )) ;



    @Override
    public List<AuthorBooks> findAuthorBooksCount() {
        TypedQuery<AuthorBooks> query = entityManager
                .createQuery("select new com.spring.library.dto.AuthorBooks(a,count(b)) " +
                        "from Author a left join a.books b group by a order by count(b) desc",AuthorBooks.class);
        return query.getResultList();
    }

//                "select new ru.otus.example.jpql_demo.dto.EmployeeProjects(e, count(p)) " +
//                        "from Employee e left join e.projects p " +
//                        "group by e " +
//                        "order by count(p) desc "



    private List<Author> getListAuthorsWithMaxBooksCount(List<AuthorBooks> authorBooks) {
        if (authorBooks.size() == 0) return null;
        int maxCount = (int) authorBooks.get(0).getCountOfBooks();
        List<Author> authors = new ArrayList<>();
        for (AuthorBooks authorBook : authorBooks) {
            if ((int) authorBook.getCountOfBooks() != maxCount) break;
            authors.add(authorBook.getAuthor());

        }
        return authors;
    }

}
