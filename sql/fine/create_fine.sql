create table fines (
id serial primary key,
names varchar(30),
number_plate varchar(6),
sum_fine int,
date_violation date
);

select * from fines

INSERT INTO fines (names, number_plate, sum_fine, date_violation)
VALUES ('������� �.�.', '�523��', 500, '2020-02-14 '),
       ('�������� �.�.', '�111��', 2000, '2020-02-23'),
       ('������� �.�.', '�330��', 1500, '2020-03-03');

select * from fines

update fines
set names = '������� �. �.'
where names = '"������� �.�.';

select * from fines;

delete from fines
where sum_fine > 1400;

select * from fines;

INSERT INTO fines (names, number_plate, sum_fine, date_violation)
VALUES ('������� �.�.', '�523��', 500, '2020-02-14 '),
       ('�������� �.�.', '�111��', 2000, '2020-02-23'),
       ('������� �.�.', '�330��', 1500, '2020-03-03');

select * from fines;
