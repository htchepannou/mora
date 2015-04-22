insert into t_media_type (id, name) values(1, 'image');
insert into t_space_type (id, name) values(1, 'club');
insert into t_user(id, username, email, firstname, lastname, deleted, creation_date, last_update) values(1, 'ray.sponsible', 'ray.sponsible@gmail.com', 'Ray', 'Sponsible', 0, '2014-01-01 10:30:55', '2014-12-01 14:30:55');
insert into t_space(id, space_type_id, user_id, name, abbreviation, description, logo_url, website_url, email, deleted, creation_date, last_update) values(1, 1, 1, 'New York Soccer Club', 'NYSC', 'Best soccer club', 'http://img.com/nysc.png', 'http://newyorksoccerclub.org', 'info@newyorksoccerclub.org', 0, '2014-01-01 10:30:55', '2014-12-01 14:30:55');

-- findById
insert into t_media(id, user_id, space_id, deleted, oembed, size, title, description, content_type, url, thumbnail_url, image_url, creation_date, last_update) values(100, 1, 1, 0, 1, 1024, 'title1', 'description1', 'image/png', 'http://t.com/foo.png', 'http://t.com/foo_small.png', 'http://t.com/foo_large.png', '2014-01-01 10:30:55', '2014-12-01 14:30:55');

-- findById_deleted
insert into t_media(id, user_id, space_id, deleted, oembed, size, title, description, content_type, url, thumbnail_url, image_url, creation_date, last_update) values(200, 1, 1, 1, 1, 1024, 'title1', 'description1', 'image/png', 'http://t.com/foo.png', 'http://t.com/foo_small.png', 'http://t.com/foo_large.png', '2014-01-01 10:30:55', '2014-12-01 14:30:55');

-- findByIds
insert into t_media(id, user_id, space_id, deleted, oembed, size, title, description, content_type, url, thumbnail_url, image_url, creation_date, last_update) values(300, 1, 1, 0, 1, 2048, 'title2', 'description2', 'image/png', 'http://t.com/foo.png', 'http://t.com/foo_small.png', 'http://t.com/foo_large.png', '2014-01-01 10:30:55', '2014-12-01 14:30:55');

-- findByOwnerByAttachmentType
insert into t_attachment_type (id, name) values(400, 'post');

insert into t_media(id, user_id, space_id, deleted, oembed, size, title, description, content_type, url, thumbnail_url, image_url, creation_date, last_update) values(410, 1, 1, 0, 1, 1024, 'title1', 'description1', 'image/png', 'http://t.com/foo.png', 'http://t.com/foo_small.png', 'http://t.com/foo_large.png', '2014-01-01 10:30:55', '2014-12-01 14:30:55');
insert into t_media(id, user_id, space_id, deleted, oembed, size, title, description, content_type, url, thumbnail_url, image_url, creation_date, last_update) values(411, 1, 1, 0, 1, 2048, 'title2', 'description2', 'image/png', 'http://t.com/foo.png', 'http://t.com/foo_small.png', 'http://t.com/foo_large.png', '2014-01-01 10:30:55', '2014-12-01 14:30:55');
insert into t_media(id, user_id, space_id, deleted, oembed, size, title, description, content_type, url, thumbnail_url, image_url, creation_date, last_update) values(412, 1, 1, 1, 1, 2048, 'title3', 'description3', 'image/png', 'http://t.com/foo.png', 'http://t.com/foo_small.png', 'http://t.com/foo_large.png', '2014-01-01 10:30:55', '2014-12-01 14:30:55');

insert into t_post(id, user_id, space_id, deleted, creation_date, last_update) values(400, 1, 1, 0, '2014-01-01 10:30:55', '2014-12-01 1:30:55');

insert into t_attachment(id, owner_id, media_id, type_id, rank) values(400, 400, 410, 400, 1);
insert into t_attachment(id, owner_id, media_id, type_id, rank) values(401, 400, 411, 400, 2);
insert into t_attachment(id, owner_id, media_id, type_id, rank) values(402, 400, 412, 400, 3);
