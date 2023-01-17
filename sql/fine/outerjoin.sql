create table departments (
departments_id serial primary key,
name_departments text
);

create table employees (
id serial primary key,
name_employees text,
departments_id int,
foreign key (departments_id) references departments (departments_id)
);

insert into departments (name_departments)
values ('Legal'), ('Sales');

select * from departments;

insert into employees (name_employees, departments_id)
values ('Sidorov', 1), ('Petrov', 2), ('Ivanov', 2);

insert into employees (name_employees, departments_id)
values ('Kornev', null);

select name_departments, employees
from departments
left join  employees using (departments_id);

select name_departments, employees
from departments
right join  employees using (departments_id);

select name_departments, employees
from departments
cross join employees;

select name_departments
from departments
left join  employees using (departments_id)
where name_employees is null;

select * from employees
 left join departments using (departments_id);

select departments_id, id, name_employees, name_departments
from departments
right join employees using (departments_id);

create table teens(
    id serial primary key,
    name_teens text,
    gender varchar(5)
);

insert into teens(name_teens, gender)
values
('Sergey', 'man'),
('Sveta', 'woman');

select t1.name_teens, t1.gender, t2.name_teens,  t2.gender
from teens as t1,  teens as t2
where t1.gender != t2.gender;