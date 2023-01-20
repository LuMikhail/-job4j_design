CREATE TABLE customers(
    customer_id serial primary key,
    first_name text,
    last_name text,
    age int,
    country text
);

insert into customers(first_name, last_name, age, country)
values
('Petrov', 'Andey', 25, 'Russia'),
('Fralova', 'Svetlana', 22, 'Russia'),
('Sidorov', 'Nikolay', 46, 'Russia'),
('Volkova', 'Olga', 22, 'Russia');

select first_name, last_name, age, country
from customers
where age = (select min(age) from customers);

CREATE TABLE orders(
    id serial primary key,
    amount int,
    customer_id int,
foreign key (customer_id) references customers(customer_id)
);

insert into orders(amount, customer_id)
values (2, 2), (6, 3);

select first_name, last_name
from customers
left join orders using (customer_id)
where customer_id  not in (select customer_id from orders);
