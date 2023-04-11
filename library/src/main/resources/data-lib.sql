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


INSERT INTO genre (name)
VALUES ('Фантастика'),
       ('Детектив'),
       ('Роман'),
       ('Поэзия'),
       ('Драма'),
       ('Классика'),
       ('Исторический'),
       ('Научно-популярный'),
       ('Приключения'),
       ('Юмористический');


INSERT INTO book (name, author_id, genre_id)
VALUES
    ('Мастер и Маргарита', 1, 1),
    ('Преступление и наказание', 2, 1),
    ('Война и мир', 3, 1),
    ('Анна Каренина', 4, 1),
    ('Идиот', 5, 1),
    ('Братья Карамазовы', 6, 1),
    ('Три товарища', 7, 1),
    ('Доктор Живаго', 8, 1),
    ('Метро 2033', 9, 2),
    ('Пикник на обочине', 10, 2),
    ('Бегущий по лезвию', 1, 2),
    ('Космический десант', 2, 2),
    ('1984', 3, 3),
    ('О дивный новый мир', 4, 3),
    ('451 градус по Фаренгейту', 5, 3),
    ('Тёмная башня', 6, 4),
    ('Хроники Нарнии', 7, 4),
    ('Властелин колец', 8, 4),
    ('Остров сокровищ', 9, 5),
    ('Робинзон Крузо', 2, 5),
    ('Девятнадцать восемьдесят четвертый', 1, 6),
    ('Триумфальная арка', 2, 6),
    ('Сто лет одиночества', 3, 7),
    ('Жизнь и судьба', 4, 7),
    ('Собачье сердце', 5, 8),
    ('Повесть временных лет', 6, 9),
    ('Князь Серебряный', 7, 9),
    ('Руслан и Людмила', 8, 9),
    ('Евгений Онегин', 9, 9),
    ('Двенадцать стульев', 3, 10);






-- UPDATE book SET name = :name, author_id = :author_id, genre_id = :genre_id WHERE id = :id;