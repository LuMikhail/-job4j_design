create or replace procedure delete_products(d_price integer)
language 'plpgsql'
as $$
    BEGIN
        if d_price > 0 THEN
            delete from products 
			where price > d_price;
        end if;
    END;
$$;

call delete_products(30);


create or replace function delete_products_function(d_products_name varchar, d_count integer)
returns void
language 'plpgsql'
as
$$
    begin
             delete from products
             where products_name = d_products_name and count = d_count;
        end if;
    end;
$$;
insert into products(products_name, producer, count, price) values ('product_3', 'producer_3', 8, 115);

select delete_products_function('product_3', 8);



