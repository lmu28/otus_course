package com.spring.library.shell;

//
//import com.spring.library.domain.Book;
//import com.spring.library.service.BookService;
//import org.springframework.shell.standard.ShellComponent;
//import org.springframework.shell.standard.ShellMethod;
//import org.springframework.shell.standard.ShellOption;

//import java.util.List;

//@ShellComponent
//public class ApplicationLibraryCommands {
//
//    BookService bookService;
//
//    public ApplicationLibraryCommands(BookService bookService) {
//        this.bookService = bookService;
//    }
//
//    @ShellMethod(value = "get book command", key = {"/get_book"})
//    public Book getBookById(@ShellOption(defaultValue = "1") int bookId){
//
//        return bookService.getById(bookId);
//
//    }
//
//    @ShellMethod(value = "get all book command", key ={"/get_all_books"})
//    public List<Book> getAllBooks(){
//        return bookService.getAll();
//    }
//
//    @ShellMethod(value = "insert book", key = {"/insert_book"})
//    public void insertBook(@ShellOption() Book book){
//
//        bookService.insert(book);
//        System.out.println("Book with id " + book.getId() + " has been inserted");
//    }
//
//
//
//
//    @ShellMethod(value = "update book", key = {"/update_book"})
//    public void update(@ShellOption() Book book){
//        bookService.update(book);
//        System.out.println("Book " + book.toString() + " has been updated");
//    }
//    @ShellMethod(value = "delete book", key = {"/delete_book"})
//    public void deleteById(int id){
//        bookService.deleteById(id);
//        System.out.println("Book with id " + id + " has been deleted");
//    }
//
//}
