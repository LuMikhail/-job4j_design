/*users - role = many-to-one*/ 
create table role(
	id serial primary key,
	role_name text
);

create table users(
	id serial primary key,
	users_name text,
	role_id int references role(id)
);

/*role - rules = many-to-many*/
create table rules(
	id serial primary key,
	name text
);

create table role_rules(
	id serial primary key,
	role_id int references role(id),
	rules_id int references rules(id)
);

/*item - category = many-to-one */
create table category(
	id serial primary key,
	category_name text
);

/*item - state = many-to-one*/
create table state(
	id serial primary key,
	status text
);

/*item - users = many-to-one*/ 
create table item(
	id serial primary key,
	item_name text,
	users_id int references users(id),
	category_id int references category(id),
	state_id int references state(id)
);

/*item - comments = one-to-many*/
create table comments(
	id serial primary key,
	comments_name text,
	item_id int references item(id)
);

/*item - attachs = one-to-many*/
create table attachs(
	id serial primary key,
	attachs_name text,
	item_id int references item(id)
);
