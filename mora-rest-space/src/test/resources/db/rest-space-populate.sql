insert into t_user(id, username, email, firstname, lastname, deleted, creation_date, last_update) values(1, 'ray.sponsible', 'ray.sponsible@gmail.com', 'Ray', 'Sponsible', 0, '2014-01-01 10:30:55', '2014-12-01 14:30:55');
insert into t_user(id, username, email, firstname, lastname, deleted, creation_date, last_update) values(2, '##already.deleted', '##already.deleted@gmail.com', 'Already', 'Deleted', 1, now(), now());

insert into t_space_type (id, name) values(1, 'club');
insert into t_space_type (id, name) values(2, 'team');