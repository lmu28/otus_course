create table author_communication_email
(
    id int primary key auto_increment,
    name varchar(250)
);

create table author
(
    id  int primary key AUTO_INCREMENT,
    name varchar(255),
    communication_email_id int references author_communication_email(id)  on delete cascade

);

create table book
(
    id int primary key AUTO_INCREMENT,
    name varchar(255),
    author_id int references author(id) on delete cascade
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
    FOREIGN KEY (book_id) REFERENCES book (id)on delete cascade,
    FOREIGN KEY (genre_id) REFERENCES genre (id)on delete cascade
);

--alter table if exists book_genre add constraint FK8l6ops8exmjrlr89hmfow4mmo foreign key (genre_id) references genre;
--alter table if exists book_genre add constraint FK52evq6pdc5ypanf41bij5u218 foreign key (book_id) references book;


Create table comment
(
    id int primary key AUTO_INCREMENT,
    body text,
    book_id INT,
    FOREIGN KEY (book_id) references book(id) on delete cascade
);


-- select * from book ;
-- select * from  author;
-- select * from  genre;
-- select * from  book_genre ;
-- select * from  comment ;


