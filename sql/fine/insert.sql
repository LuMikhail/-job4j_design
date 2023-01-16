insert into role(role_name) values('Director');
insert into role(role_name) values('Employee');

insert into users(users_name, role_id) values('Petrov', (select id from role where role_name = 'Director'));
insert into users(users_name, role_id) values('Sidorov', (select id from role where role_name = 'Employee'));

insert into rules(name) values('customer');
insert into role_rules(role_id, rules_id) values (2, 1);

insert into category(category_name) values ('first');
insert into category(category_name) values ('second');

insert into state(status) values ('sent');
insert into state(status) values ('received');

insert into item(item_name, users_id, category_id, state_id) values ('reward', 1, 1, 1);
insert into item(item_name, users_id, category_id, state_id) values ('vacation pay', 2, 1, 2);

insert into comments(comments_name, item_id) values ('Thanks for the work', 1);
insert into attachs(attachs_name, item_id) values('file', 1); 
