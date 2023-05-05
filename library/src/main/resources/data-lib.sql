INSERT INTO author (name)
VALUES ('Анна Иванова'),
       ('Максим Петров'),
       ('Елена Смирнова'),
       ('Илья Кузнецов'),
       ('Александра Николаева'),
       ('Дмитрий Морозов'),
       ('Ксения Лебедева'),
       ('Артем Федоров'),
       ('Мария Ковалева'),
       ('Павел Сидоров');


INSERT INTO book (name, author_id)
VALUES ('Мастер и Маргарита', 1),
       ('Преступление и наказание', 2),
       ('Война и мир', 3),
       ('Анна Каренина', 4),
       ('Идиот', 5),
       ('Братья Карамазовы', 6),
       ('Три товарища', 7),
       ('Доктор Живаго', 8),
       ('Метро 2033', 9),
       ('Пикник на обочине', 10),
       ('Бегущий по лезвию', 1),
       ('Космический десант', 2),
       ('1984', 3),
       ('О дивный новый мир', 4),
       ('451 градус по Фаренгейту', 5),
       ('Тёмная башня', 6),
       ('Хроники Нарнии', 7),
       ('Властелин колец', 8),
       ('Остров сокровищ', 9),
       ('Робинзон Крузо', 2),
       ('Девятнадцать восемьдесят четвертый', 1),
       ('Триумфальная арка', 2),
       ('Сто лет одиночества', 3),
       ('Жизнь и судьба', 4),
       ('Собачье сердце', 5),
       ('Повесть временных лет', 6),
       ('Князь Серебряный', 7),
       ('Руслан и Людмила', 8),
       ('Евгений Онегин', 9),
       ('Двенадцать стульев', 3);



INSERT INTO genre (name)
VALUES ('Фантастика'),
       ('Детектив'),
       ('Детектив'),
       ('Роман'),
       ('Поэзия'),
       ('Драма'),
       ('Роман'),
       ('Классика'),
       ('Исторический'),
       ('Научно-популярный'),
       ('Приключения'),
       ('Детектив'),
       ('Юмористический');


INSERT INTO comment (body, book_id)
VALUES ('Очень интересная книга, рекомендую!', 1),
       ('Постоянно перечитываю, всегда нахожу что-то новое', 1),
       ('Не могу оторваться от этой книги', 2),
       ('Сильная и захватывающая история', 2),
       ('Как же я раньше не знал об этой книге?', 3),
       ('Душевная и трогательная история', 3),
       ('Необычный подход к теме, очень понравилось', 4),
       ('Интересный поворот сюжета в конце книги', 5),
       ('Прекрасное описание персонажей, словно они живые', 6),
       ('Захватывает с первых страниц и не отпускает до конца', 7);


-- UPDATE book SET name = :name, author_id = :author_id, genre_id = :genre_id WHERE id = :id;