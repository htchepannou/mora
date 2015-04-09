insert into t_user(id, username, email, firstname, lastname, deleted, creation_date, last_update) values(1, 'ray.sponsible', 'ray.sponsible@gmail.com', 'Ray', 'Sponsible', 0, '2014-01-01 10:30:55', '2014-12-01 14:30:55');


insert into t_user(id, username, email, firstname, lastname, deleted, creation_date, last_update) values(2, 'john.doe', 'john.doe@gmail.com', 'John', 'Dow', 0, '2014-01-01 10:30:55', '2014-12-01 14:30:55');

insert into t_access_token(id, user_id, value, creation_date, expiry_date) values(20, 2, 'bc9a50e1e0085b13c4bba866f6dfe571', '2014-01-01 10:30:55', '2017-01-01 11:30:55');
insert into t_access_token(id, user_id, value, creation_date, expiry_date) values(21, 2, 'bc9a50e1e0085b13c4bba866f6dfe572', '2014-01-02 10:30:55', '2017-01-01 12:30:55');
insert into t_access_token(id, user_id, value, creation_date, expiry_date) values(22, 2, 'bc9a50e1e0085b13c4bba866f6dfe573', '2014-01-03 10:30:55', '2017-01-01 13:30:55');

insert into t_access_token(id, user_id, value, creation_date, expiry_date) values(30, 2, 'bc9a50e1e0085b13c4bba866f6dfe574', '2014-01-03 10:30:55', '2014-01-04 10:30:55');
insert into t_access_token(id, user_id, value, creation_date, expiry_date) values(31, 2, 'bc9a50e1e0085b13c4bba866f6dfe575', '2014-01-03 11:30:55', '2014-01-04 12:30:55');