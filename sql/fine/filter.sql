create table type (
type_id serial primary key,
name_type text
);

create table product (
id serial primary key,
name_product text,
type_id int,
expired_date bool,
price int,
foreign key (type_id) references type (type_id)
);

insert into type (name_type) values ('cheese'), ('milk'), ('meat');

select * from type;

insert into product (name_product, type_id, expired_date, price)
values
('Processed cheese', 1, false, 150),
('Mozzarella cheese', 1, true , 250),
('Parmesan cheese', 1, true , 370),
('The cow from korenovka ice cream', 2, true , 100),
('The cow from korenovka milk', 2, false , 50),
('The merry milkman ice cream', 2, true , 120),
('Pork', 3, true, 320),
('Beef', 3, true, 650),
('Turkey', 3, true, 420);

select name_product from product join type using (type_id) where name_type in ('cheese');

select name_product from product join type using (type_id) where name_product like '%ice_cream%';

select name_product, expired_date from product join type using (type_id) where expired_date = false;

select name_product, price
from product join type using (type_id)  where price = (select max(price) from product);

select name_type, count(name_type) as count_name_type
 from product join type using (type_id) group by name_type;

select name_product, name_type from product join type using (type_id) where name_type in ('cheese', 'milk');

select name_type from product join type using (type_id) group by name_type having count(name_type) < 10;

select name_product, name_type from product join type using (type_id);
