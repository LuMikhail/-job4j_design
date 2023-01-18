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
/*������� ������ ����������� ����� ������� ������, ��� ������ ������ � ������ �����������
����� �� ����� (����� ��������� ����� � ���� ������). ����������� �� ������ �� �� ������ ���,
� �� ������ (statement �������)*/

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


/*������� ������ ����������� �� ������� ������ � ����������� �����
�� ����� (����� ��������� ����� � ���� ������). ����� ���������� row �������.*/

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

/* ������� �� row ������, ������� ��� ������� �������� � ������� products,
 ����� �������� ���, ���� � ������� ���� � ������� history_of_price. */

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