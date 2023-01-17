create table devices(
    id serial primary key,
    name varchar(255),
    price float
);

create table people(
    id serial primary key,
    name varchar(255)
);

create table devices_people(
    id serial primary key,
    device_id int references devices(id),
    people_id int references people(id)
);

INSERT INTO people(name) VALUES ('Sergey'), ('Olga');

INSERT INTO devices(name, price)
VALUES
  ('iPhone 13', 75200),
  ('Galaxy 22', 70500),
  ('Watch', 4500);
  
INSERT INTO devices_people(device_id, people_id) VALUES (1, 1), (2, 2), (3, 1);

select ROUND(avg(price)) as average_price
from devices;

select people.name as name, ROUND(avg(price)) as average_price
from devices 
join devices_people on device_id = devices.id
join people on people_id = people.id
group by people.name;

select people.name as name, ROUND(avg(price)) as average_price
from devices 
join devices_people on device_id = devices.id
join people on people_id = people.id
group by people.name
having avg(price) > 5000;
