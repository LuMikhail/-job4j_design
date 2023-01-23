CREATE TABLE movie (
    id SERIAL PRIMARY KEY,
    name_movie text,
    director text
);

CREATE TABLE book (
    id SERIAL PRIMARY KEY,
    title text,
    author text
);

INSERT INTO movie (name_movie, director)
VALUES ('The Martian', 'Ridley Scott'),
       ('The Matrix', 'The Wachowski Brothers'),
       ('Lord of the Rings', 'Peter Jackson'),
       ('Harry Potter and the Prisoner of Azkaban', 'Alfonso Cuaron'),
       ('Iron man', 'Jon Favreau');

INSERT INTO book (title, author)
VALUES ('Harry Potter and the Prisoner of Azkaban', 'J.K. Rowling'),
       ('Lord of the Rings', 'John Tolkien'),
       ('1984', 'George Orwell'),
       ('The Martian', 'Andy Weir'),
       ('The Divine Comedy', 'Dante Alighieri');

select name_movie
from movie
intersect
select title
from book
order by name_movie;

select title
from book
except
select name_movie
from movie
order by title;

(select name_movie
from movie
intersect
select title
from book)
union
(select title
from book
except
select name_movie
from movie)
Order by name_movie;