create table products (
    id serial primary key,
    products_name varchar(50),
    producer varchar(50),
    count integer default 0,
    price integer
);

create table history_of_price (
    id serial primary key,
    name_history_of_price varchar(50),
    price integer,
    date timestamp
);
/*The trigger should be triggered after inserting data for any product and simply calculate
the tax on the product (you need to add the tax to the price of the product). It should act not on each row,
but on the request (statement level).*/

create or replace function tax_rate_statement()
    returns trigger as
$$
    begin
        update products
        set price = price + price * 0.20
        where id = (select id from inserted);
        return new;
    end;
$$
LANGUAGE 'plpgsql';

create trigger tax_trigger
    after insert on products
    referencing new table as inserted
    for each statement
    execute procedure tax_rate_statement();


/*The trigger must be triggered before inserting the data and calculate the tax
on the product (you need to add the tax to the price of the product). Here we use the row level.*/

create or replace function tax_rate_row()
    returns trigger as
$$
    begin
        new.price = new.price + new.price * 0.20;
        return new;
    END;
$$
LANGUAGE 'plpgsql';

create trigger tax_trigger_row
    before insert on products
    for each row
    execute procedure tax_rate_row();

/* A trigger at the row level, which, when inserting a product into the products table,
will enter the name, price and current date in the history_of_price table. */

create or replace function add_history_of_price()
    returns trigger as
$$
    begin
        insert into history_of_price(name_history_of_price, price, date)
        values (new.products_name, new.price, now());
        return new;
    end;
$$
LANGUAGE 'plpgsql';

create trigger add_history_trigger
    before insert on products
    for each row
    execute procedure add_history_of_price();