create table car_bodies (
body_id serial primary key,
body_name text
);

create table car_engines (
engine_id serial primary key,
engine_name text
);

create table car_transmissions (
transmission_id serial primary key,
transmission_name text
);

create table cars (
id serial primary key,
car_name text,
body_id int,
engine_id int,
transmission_id int,
foreign key (body_id) references car_bodies (body_id),
foreign key (engine_id) references car_engines (engine_id),
foreign key (transmission_id) references car_transmissions (transmission_id)
);

insert into car_bodies (body_name)
values ('sedan'), ('hatchback'), ('pickup');

insert into car_engines (engine_name)
values ('diesel'), ('petrol');

insert into car_transmissions (transmission_name)
values ('manual'), ('automatic');

insert into cars (car_name, body_id, engine_id, transmission_id)
values ('Nissan', 1, 1, null), ('Toyota', null, 2, 2), ('Ford', 3, null, 1), ('Kia', 2, 2, 2);

select car_name, body_name, engine_name, transmission_name
from car_bodies
right join cars using (body_id)
left join car_engines using (engine_id)
left join car_transmissions using (transmission_id);

select car_name
from car_bodies
right join cars using (body_id)
where body_name is null;

select car_name
from  cars
left join car_engines using (engine_id)
where engine_name is null;

select car_name
from cars
left join car_transmissions using (transmission_id)
where transmission_name is null;