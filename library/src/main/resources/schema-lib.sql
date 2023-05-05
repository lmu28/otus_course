create table author
(
    id  int primary key AUTO_INCREMENT,
    name varchar(255)

);
create table book
(
    id int primary key AUTO_INCREMENT,
    name varchar(255),
    author_id int,
    FOREIGN KEY (author_id)  REFERENCES author(id)
);
create table genre
(
    id  int primary key AUTO_INCREMENT,
    name varchar(255)
);


CREATE TABLE book_genre
(
    book_id  INT,
    genre_id INT,
    PRIMARY KEY (book_id, genre_id),
    FOREIGN KEY (book_id) REFERENCES book (id),
    FOREIGN KEY (genre_id) REFERENCES genre (id)
);

Create table comment
(
    id INT primary key auto_increment,
    body text,
    book_id INT,
    FOREIGN KEY (book_id) references book(id)
)


-- select * from book ;
-- select * from  author;
-- select * from  genre;
-- select * from  book_genre ;
-- select * from  comment ;


