create table fauna (
    id serial primary key,
    type_name text,
    avg_age int,
    discovery_date date
);

insert into fauna(type_name, avg_age, discovery_date) values('Chelonoidis donfaustoi', 21900, '2015-03-12');
insert into fauna(type_name, avg_age) values('Jellyfish', 180);
insert into fauna(type_name, avg_age) values('Brown bear', 10950);
insert into fauna(type_name, discovery_date) values('Rhinopithecus strykeri', '2010-04-15');

select * from fauna where type_name like '%fish%';
select * from fauna where avg_age between 10000 and 21000;
select * from fauna where discovery_date is null;
select * from fauna where date_part('year', discovery_date) < 1950;
select * from fauna;