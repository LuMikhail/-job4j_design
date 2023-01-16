/* many-to-many */
create table car(
     id serial primary key,
     title varchar(15)
 );

 create table human(
     id serial primary key,
     name varchar(20)
 );

 create table driver(
     id serial primary key,
     car_id int references car(id),
     human_id int references human(id)
 );

 /* one-to-one */
 create table client(
    id serial primary key,
    name varchar(20),
    number int
);

create table phone(
    id serial primary key,
    number int,
    client_id int references client(id) unique
);

/* many-to-one */
create table pages(
    id serial primary key,
    content varchar(250),
    name varchar(50),
    menus_id int references menus(id)
);

create table menus(
    id serial primary key,
    name varchar(50)
);