insert into t_space_type (id, name) values(1, 'club');

-- findById
insert into t_user(id, username, email, firstname, lastname, deleted, creation_date, last_update) values(100, 'ray.sponsible', 'ray.sponsible@gmail.com', 'Ray', 'Sponsible', 0, '2014-01-01 10:30:55', '2014-12-01 14:30:55');
insert into t_space(id, space_type_id, user_id, name, abbreviation, description, logo_url, website_url, email, deleted, creation_date, last_update) values(100, 1, 100, 'New York Soccer Club', 'NYSC', 'Best soccer club', 'http://img.com/nysc.png', 'http://newyorksoccerclub.org', 'info@newyorksoccerclub.org', 0, '2014-01-01 10:30:55', '2014-12-01 14:30:55');
insert into t_post(id, user_id, space_id, deleted, title, summary, content, creation_date, last_update) values(100, 100, 100, 0, 'title1', 'summary1', '<p>content1</p>', '2014-01-01 10:30:55', '2014-12-01 14:30:55');

-- findById_deleted
insert into t_user(id, username, email, firstname, lastname, deleted, creation_date, last_update) values(200, 'ray.sponsible1', 'ray.sponsible1@gmail.com', 'Ray', 'Sponsible', 0, '2014-01-01 10:30:55', '2014-12-01 14:30:55');
insert into t_space(id, space_type_id, user_id, name, abbreviation, description, logo_url, website_url, email, deleted, creation_date, last_update) values(200, 1, 200, 'New York Soccer Club', 'NYSC', 'Best soccer club', 'http://img.com/nysc.png', 'http://newyorksoccerclub.org', 'info@newyorksoccerclub.org', 0, '2014-01-01 10:30:55', '2014-12-01 14:30:55');
insert into t_post(id, user_id, space_id, deleted, title, summary, content, creation_date, last_update) values(200, 200, 200, 1, 'title1', 'summary1', '<p>content1</p>', '2014-01-01 10:30:55', '2014-12-01 14:30:55');

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


-- findIdsPublishedForUser_offset
insert into t_role(id, name) values(499, 'r499');
insert into t_user(id, username, email) values(499, 'ray499', 'ray499@gmail.com');

insert into t_user(id, username, email, firstname, lastname, deleted, creation_date, last_update) values(400, 'ray.sponsible3', 'ray.sponsible3@gmail.com', 'Ray', 'Sponsible', 0, '2014-01-01 10:30:55', '2014-12-01 14:30:55');

insert into t_space(id, space_type_id, user_id, name, deleted, creation_date, last_update) values(400, 1, 400, 'Foo', 0, '2014-01-01 10:30:55', '2014-12-01 14:30:55');

insert into t_member(id, space_id, user_id, role_id) values(400, 400, 400, 499);

insert into t_post(id, user_id, space_id, deleted, creation_date, last_update) values(400, 499, 400, 0, '2014-01-01 10:30:55', '2014-12-01 1:30:55');
insert into t_post(id, user_id, space_id, deleted, creation_date, last_update) values(401, 499, 400, 0, '2014-01-01 10:30:55', '2014-12-01 2:30:55');
insert into t_post(id, user_id, space_id, deleted, creation_date, last_update) values(402, 499, 400, 0, '2014-01-01 10:30:55', '2014-12-01 3:30:55');
insert into t_post(id, user_id, space_id, deleted, creation_date, last_update) values(403, 499, 400, 0, '2014-01-01 10:30:55', '2014-12-01 4:30:55');
insert into t_post(id, user_id, space_id, deleted, creation_date, last_update) values(404, 499, 400, 0, '2014-01-01 10:30:55', '2014-12-01 5:30:55');
insert into t_post(id, user_id, space_id, deleted, creation_date, last_update) values(405, 499, 400, 0, '2014-01-01 10:30:55', '2014-12-01 6:30:55');
insert into t_post(id, user_id, space_id, deleted, creation_date, last_update) values(406, 499, 400, 0, '2014-01-01 10:30:55', '2014-12-01 7:30:55');
insert into t_post(id, user_id, space_id, deleted, creation_date, last_update) values(407, 499, 400, 0, '2014-01-01 10:30:55', '2014-12-01 8:30:55');
insert into t_post(id, user_id, space_id, deleted, creation_date, last_update) values(408, 499, 400, 0, '2014-01-01 10:30:55', '2014-12-01 9:30:55');
insert into t_post(id, user_id, space_id, deleted, creation_date, last_update) values(409, 499, 400, 0, '2014-01-01 10:30:55', '2014-12-01 10:30:55');
