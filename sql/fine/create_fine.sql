create table fines (
id serial primary key,
names varchar(30),
number_plate varchar(6),
sum_fine int,
date_violation date
);

select * from fines

INSERT INTO fines (names, number_plate, sum_fine, date_violation)
VALUES ('Баранов П.Е.', 'Р523ВТ', 500, '2020-02-14 '),
       ('Абрамова К.А.', 'О111АВ', 2000, '2020-02-23'),
       ('Яковлев Г.Р.', 'Т330ТТ', 1500, '2020-03-03');

select * from fines

update fines
set names = 'Смирнов С. Д.'
where names = '"Баранов П.Е.';

select * from fines;

delete from fines
where sum_fine > 1400;

select * from fines;

INSERT INTO fines (names, number_plate, sum_fine, date_violation)
VALUES ('Баранов П.Е.', 'Р523ВТ', 500, '2020-02-14 '),
       ('Абрамова К.А.', 'О111АВ', 2000, '2020-02-23'),
       ('Яковлев Г.Р.', 'Т330ТТ', 1500, '2020-03-03');

select * from fines;
