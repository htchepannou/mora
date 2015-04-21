insert into t_space_type (id, name) values(1, 'club');

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

insert into t_post(id, user_id, space_id, deleted, title, summary, content, creation_date, last_update) values(300, 300, 300, 0, 'title1', 'summary1', '<p>content1</p>', '2014-01-01 10:30:55', '2014-12-01 14:30:55');
insert into t_post(id, user_id, space_id, deleted, title, summary, content, creation_date, last_update) values(301, 310, 300, 0, 'title2', 'summary2', '<p>content2</p>', '2014-01-01 10:30:55', '2014-12-01 14:30:55');
insert into t_post(id, user_id, space_id, deleted, title, summary, content, creation_date, last_update) values(302, 300, 300, 1, 'title3', 'summary3', '<p>content3</p>', '2014-01-01 10:30:55', '2014-12-01 14:30:55');
