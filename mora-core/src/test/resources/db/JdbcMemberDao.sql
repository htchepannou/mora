insert into t_user(id, username, email, firstname, lastname, deleted, creation_date, last_update) values(1, 'ray.sponsible', 'ray.sponsible@gmail.com', 'Ray', 'Sponsible', 0, '2014-01-01 10:30:55', '2014-12-01 14:30:55');
insert into t_user(id, username, email, firstname, lastname, deleted, creation_date, last_update) values(2, 'john.doe', 'john.doe@gmail.com', 'John', 'Dow', 0, '2014-01-01 10:30:55', '2014-12-01 14:30:55');
insert into t_user(id, username, email, firstname, lastname, deleted, creation_date, last_update) values(3, 'john.smith', 'john.smitch@gmail.com', 'John', 'Smith', 0, '2014-01-01 10:30:55', '2014-12-01 14:30:55');
insert into t_user(id, username, email, firstname, lastname, deleted, creation_date, last_update) values(10, '#ray.sponsible', 'ray.sponsible@gmail.com', 'Ray', 'Sponsible', 1, '2011-01-01 10:30:55', '2012-12-01 14:30:55');

insert into t_space_type (id, name) values(1, 'club');

insert into t_space(id, space_type_id, user_id, name, abbreviation, description, logo_url, website_url, email, deleted, creation_date, last_update) values(1, 1, 1, 'New York Soccer Club', 'NYSC', 'Best soccer club', 'http://img.com/nysc.png', 'http://newyorksoccerclub.org', 'info@newyorksoccerclub.org', 0, '2014-01-01 10:30:55', '2014-12-01 14:30:55');
insert into t_space(id, space_type_id, user_id, name, abbreviation, description, logo_url, website_url, email, deleted, creation_date, last_update) values(2, 1, 1, 'Deleted', 'DEL', null, 'http://img.com/nysc.png', 'http://newyorksoccerclub.org', 'info@newyorksoccerclub.org', 1, '2014-01-01 10:30:55', '2014-12-01 14:30:55');

insert into t_role (id, name) values(1, 'admin');
insert into t_role (id, name) values(2, 'member');

insert into t_member(id, user_id, space_id, role_id, creation_date) VALUES (10, 1,  1, 1, '2014-12-01 14:30:55');
insert into t_member(id, user_id, space_id, role_id, creation_date) VALUES (11, 1,  1, 2, '2014-12-01 14:30:55');

insert into t_member(id, user_id, space_id, role_id, creation_date) VALUES (20, 2,  1, 1, '2014-12-01 14:30:55');
insert into t_member(id, user_id, space_id, role_id, creation_date) VALUES (30, 10, 1, 1, '2014-12-01 14:30:55');

insert into t_member(id, user_id, space_id, role_id, creation_date) VALUES (40, 1,  2, 1, '2014-12-01 14:30:55');
insert into t_member(id, user_id, space_id, role_id, creation_date) VALUES (50, 2,  2, 1, '2014-12-01 14:30:55');
insert into t_member(id, user_id, space_id, role_id, creation_date) VALUES (60, 10, 2, 1, '2014-12-01 14:30:55');
