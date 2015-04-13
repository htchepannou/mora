insert into t_user(id, username, email, firstname, lastname, deleted, creation_date, last_update) values(1, 'ray.sponsible', 'ray.sponsible@gmail.com', 'Ray', 'Sponsible', 0, '2014-01-01 10:30:55', '2014-12-01 14:30:55');
insert into t_password(id, user_id, value, creation_date, last_update) values(1, 1, 'bc9a50e1e0085b13c4bba866f6dfe57c', '2014-01-01 10:30:55', '2014-12-01 14:30:55');
insert into t_access_token(id, user_id, value, creation_date, expiry_date) values(1, 1, 'bc9a50e1e0085b13c4bba866f6dfe57c', '2014-01-01 10:30:55', '2030-12-01 14:30:55');


insert into t_space_type (id, name) values(1, 'club');
insert into t_space_type (id, name) values(2, 'team');
