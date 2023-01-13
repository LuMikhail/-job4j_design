create table fine (
id serial primary key,
names varchar(30),
number_plate varchar(6),
sum_fine int,
date_violation date
);

select * from fine