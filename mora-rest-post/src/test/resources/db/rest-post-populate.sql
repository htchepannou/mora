insert into t_space_type (id, name) values(1, 'club');

-- findIdsPublishedForUser
insert into t_role(id, name) values(399, 'r399');
insert into t_user(id, username, email) values(399, 'ray399', 'ray399@gmail.com');

insert into t_user(id, username, email, firstname, lastname, deleted, creation_date, last_update) values(300, 'ray.sponsible3', 'ray.sponsible3@gmail.com', 'Ray', 'Sponsible', 0, '2014-01-01 10:30:55', '2014-12-01 14:30:55');
insert into t_user(id, username, email, firstname, lastname, deleted, creation_date, last_update) values(310, 'john.smith',     'john.smith@gmail.com',     'John', 'Smith',    0, '2014-01-01 10:30:55', '2014-12-01 14:30:55');

insert into t_space(id, space_type_id, user_id, name, deleted) values(300, 1, 399, 'space1', 0);
insert into t_space(id, space_type_id, user_id, name, deleted) values(310, 1, 399, 'space2', 0);

insert into t_member(id, space_id, user_id, role_id) values(300, 300, 300, 399);
insert into t_member(id, space_id, user_id, role_id) values(301, 300, 310, 399);
insert into t_member(id, space_id, user_id, role_id) values(302, 310, 300, 399);
insert into t_member(id, space_id, user_id, role_id) values(303, 310, 310, 399);

insert into t_post(id, user_id, space_id, deleted, creation_date, last_update) values(300, 399, 300, 0, '2014-01-01 10:30:55', '2014-12-01 14:30:55');
insert into t_post(id, user_id, space_id, deleted, creation_date, last_update) values(301, 399, 300, 0, '2014-01-01 10:30:55', '2014-12-01 15:30:55');
insert into t_post(id, user_id, space_id, deleted, creation_date, last_update) values(302, 399, 300, 0, '2014-01-01 10:30:55', '2014-12-01 16:30:55');
insert into t_post(id, user_id, space_id, deleted, creation_date, last_update) values(310, 399, 310, 0, '2014-01-01 10:30:55', '2014-12-01 17:30:55');
insert into t_post(id, user_id, space_id, deleted, creation_date, last_update) values(311, 399, 310, 1, '2014-01-01 10:30:55', '2014-12-01 18:30:55');
