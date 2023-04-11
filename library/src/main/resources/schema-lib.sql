create table author
(
    id  int primary key AUTO_INCREMENT,
    name varchar(255)

);

create table genre
(
    id  int primary key AUTO_INCREMENT,
    name varchar(255)
);


create table book
(
    id int primary key AUTO_INCREMENT,
    name varchar(255),
    author_id int,
    genre_id int,
    FOREIGN KEY (author_id)  REFERENCES author(id),
    FOREIGN KEY (genre_id)  REFERENCES genre (id)
)

-- drop table book;
-- drop table author;
-- drop table genre;



