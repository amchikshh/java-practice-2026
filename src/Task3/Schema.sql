create table Product (
	id serial not null,
	name char(20) not null,
	cost integer check(cost > -1)
);

insert into product (name, cost) values ('apple', 100);

insert into product (name, cost) values ('milk', 89);

insert into product (name, cost) values ('cheese', 10);
