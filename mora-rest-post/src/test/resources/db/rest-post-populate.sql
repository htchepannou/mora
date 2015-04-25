insert into t_space_type (id, name) values(1, 'club');
insert into t_attachment_type (id, name) values(1, 'post');
insert into t_media_type (id, name) values(1, 'image');

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

-- getPost
insert into t_media(id, user_id, space_id, type_id, deleted, oembed, size, title, description, content_type, url, thumbnail_url, image_url, creation_date, last_update) values(310, 300, 300, 1, 0, 1, 1024, 'title1', 'description1', 'image/png', 'http://t.com/foo1.png', 'http://t.com/foo_small1.png', 'http://t.com/foo_large1.png', '2014-01-01 10:30:55', '2014-12-01 14:30:55');
insert into t_media(id, user_id, space_id, type_id, deleted, oembed, size, title, description, content_type, url, thumbnail_url, image_url, creation_date, last_update) values(311, 300, 300, 1, 0, 1, 2048, 'title2', 'description2', 'image/png', 'http://t.com/foo2.png', 'http://t.com/foo_small2.png', 'http://t.com/foo_large2.png', '2014-01-01 10:30:55', '2014-12-01 14:30:55');
insert into t_media(id, user_id, space_id, type_id, deleted, oembed, size, title, description, content_type, url, thumbnail_url, image_url, creation_date, last_update) values(312, 300, 300, 1, 1, 1, 2048, 'title3', 'description3', 'image/png', 'http://t.com/foo3.png', 'http://t.com/foo_small3.png', 'http://t.com/foo_large3.png', '2014-01-01 10:30:55', '2014-12-01 14:30:55');

insert into t_attachment(id, owner_id, media_id, type_id, rank) values(300, 300, 310, 1, 1);
insert into t_attachment(id, owner_id, media_id, type_id, rank) values(301, 300, 311, 1, 2);
insert into t_attachment(id, owner_id, media_id, type_id, rank) values(302, 300, 312, 1, 3);
