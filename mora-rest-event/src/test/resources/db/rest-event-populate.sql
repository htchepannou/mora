insert into t_space_type (id, name) values(1, 'club');
insert into t_event_type (id, name) values(1, 'game');

-- getUserPosts / getCurrentUserPosts
insert into t_role(id, name) values(399, 'r399');
insert into t_user(id, username, email) values(399, 'ray399', 'ray399@gmail.com');

insert into t_user(id, username, email, firstname, lastname, deleted, creation_date, last_update) values(300, 'ray.sponsible3', 'ray.sponsible3@gmail.com', 'Ray', 'Sponsible', 0, '2014-01-01 10:30:55', '2014-12-01 14:30:55');
insert into t_user(id, username, email, firstname, lastname, deleted, creation_date, last_update) values(310, 'john.smith',     'john.smith@gmail.com',     'John', 'Smith',    0, '2014-01-01 10:30:55', '2014-12-01 14:30:55');

insert into t_access_token(id, user_id, value, creation_date, expiry_date) values(300, 300, 'bc9a50e1e0085b13c4bba866f6dfe57c', '2014-01-01 10:30:55', '2030-12-01 14:30:55');


insert into t_space(id, space_type_id, user_id, name, logo_url, deleted) values(300, 1, 399, 'space1', 'http://space1.com/logo.png', 0);
insert into t_space(id, space_type_id, user_id, name, logo_url, deleted) values(310, 1, 399, 'space2', null, 0);

insert into t_member(id, space_id, user_id, role_id) values(300, 300, 300, 399);
insert into t_member(id, space_id, user_id, role_id) values(301, 300, 310, 399);
insert into t_member(id, space_id, user_id, role_id) values(302, 310, 300, 399);
insert into t_member(id, space_id, user_id, role_id) values(303, 310, 310, 399);

insert into t_event(id, user_id, space_id, type_id, deleted, title, notes, location, url, timezone, start_datetime, end_datetime, creation_date, last_update) values(300, 300, 300, 1, 0, 'title1', '<p>notes</p>', 'location', 'http://www.google.ca', 'America/Montreal', now(), now(), '2014-01-01 10:30:55', '2014-12-01 14:30:55');
insert into t_event(id, user_id, space_id, type_id, deleted, title, notes, location, url, timezone, start_datetime, end_datetime, creation_date, last_update) values(301, 300, 300, 1, 0, 'title2', '<p>notes</p>', 'location', 'http://www.google.ca', 'America/Montreal', NOW() + INTERVAL 1 DAY, NOW() + INTERVAL 1 DAY, '2014-01-01 10:30:55', '2014-12-01 14:30:55');
insert into t_event(id, user_id, space_id, type_id, deleted, title, notes, location, url, timezone, start_datetime, end_datetime, creation_date, last_update) values(302, 300, 300, 1, 1, 'title3', '<p>notes</p>', 'location', 'http://www.google.ca', 'America/Montreal', NOW() + INTERVAL 2 DAY, NOW() + INTERVAL 2 DAY, '2014-01-01 10:30:55', '2014-12-01 14:30:55');
insert into t_event(id, user_id, space_id, type_id, deleted, title, notes, location, url, timezone, start_datetime, end_datetime, creation_date, last_update) values(303, 300, 300, 1, 1, 'title4', '<p>notes</p>', 'location', 'http://www.google.ca', 'America/Montreal', '2014-01-01 10:30:55', '2014-12-01 14:30:55', '2014-01-01 10:30:55', '2014-12-01 14:30:55');

