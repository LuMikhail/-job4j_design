CREATE VIEW work_plan as
select car_name, body_name, engine_name, transmission_name,
 case
 when car_name = 'Ford' then 'oil change'
 when car_name = 'Nissan' then 'filter change'
 when car_name = 'Toyota' then 'wheel change'
 end as maintenance_works
from car_bodies
right join cars using (body_id)
left join car_engines using (engine_id)
left join car_transmissions using (transmission_id)
where car_name like '____%'
order by maintenance_works;

select car_name, maintenance_works from work_plan;