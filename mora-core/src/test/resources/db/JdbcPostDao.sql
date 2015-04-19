insert into t_space_type (id, name) values(1, 'club');

-- findById
insert into t_user(id, username, email, firstname, lastname, deleted, creation_date, last_update) values(100, 'ray.sponsible', 'ray.sponsible@gmail.com', 'Ray', 'Sponsible', 0, '2014-01-01 10:30:55', '2014-12-01 14:30:55');
insert into t_space(id, space_type_id, user_id, name, abbreviation, description, logo_url, website_url, email, deleted, creation_date, last_update) values(100, 1, 100, 'New York Soccer Club', 'NYSC', 'Best soccer club', 'http://img.com/nysc.png', 'http://newyorksoccerclub.org', 'info@newyorksoccerclub.org', 0, '2014-01-01 10:30:55', '2014-12-01 14:30:55');
insert into t_post(id, user_id, space_id, deleted, title, summary, content, creation_date, last_update) values(100, 100, 100, 0, 'title1', 'summary1', '<p>content1</p>', '2014-01-01 10:30:55', '2014-12-01 14:30:55');

-- findById_deleted
insert into t_user(id, username, email, firstname, lastname, deleted, creation_date, last_update) values(200, 'ray.sponsible1', 'ray.sponsible1@gmail.com', 'Ray', 'Sponsible', 0, '2014-01-01 10:30:55', '2014-12-01 14:30:55');
insert into t_space(id, space_type_id, user_id, name, abbreviation, description, logo_url, website_url, email, deleted, creation_date, last_update) values(200, 1, 200, 'New York Soccer Club', 'NYSC', 'Best soccer club', 'http://img.com/nysc.png', 'http://newyorksoccerclub.org', 'info@newyorksoccerclub.org', 0, '2014-01-01 10:30:55', '2014-12-01 14:30:55');
insert into t_post(id, user_id, space_id, deleted, title, summary, content, creation_date, last_update) values(200, 200, 200, 1, 'title1', 'summary1', '<p>content1</p>', '2014-01-01 10:30:55', '2014-12-01 14:30:55');

-- findByIds
insert into t_user(id, username, email, firstname, lastname, deleted, creation_date, last_update) values(300, 'ray.sponsible3', 'ray.sponsible3@gmail.com', 'Ray', 'Sponsible', 0, '2014-01-01 10:30:55', '2014-12-01 14:30:55');
insert into t_user(id, username, email, firstname, lastname, deleted, creation_date, last_update) values(310, 'john.smith',     'john.smith@gmail.com',     'John', 'Smith',    0, '2014-01-01 10:30:55', '2014-12-01 14:30:55');

insert into t_space(id, space_type_id, user_id, name, abbreviation, description, logo_url, website_url, email, deleted, creation_date, last_update) values(300, 1, 300, 'New York Soccer Club', 'NYSC', 'Best soccer club', 'http://img.com/nysc.png', 'http://newyorksoccerclub.org', 'info@newyorksoccerclub.org', 0, '2014-01-01 10:30:55', '2014-12-01 14:30:55');
insert into t_post(id, user_id, space_id, deleted, title, summary, content, creation_date, last_update) values(300, 300, 300, 0, 'title300', 'summary300', '<p>content300</p>', '2014-01-01 10:30:55', '2014-12-01 14:30:55');
insert into t_post(id, user_id, space_id, deleted, title, summary, content, creation_date, last_update) values(301, 300, 300, 0, 'title301', 'summary301', '<p>content301</p>', '2014-01-01 10:30:55', '2014-12-01 15:30:55');
insert into t_post(id, user_id, space_id, deleted, title, summary, content, creation_date, last_update) values(302, 310, 300, 0, 'title302', 'summary302', '<p>content302</p>', '2014-01-01 10:30:55', '2014-12-01 16:30:55');

insert into t_space(id, space_type_id, user_id, name, abbreviation, description, logo_url, website_url, email, deleted, creation_date, last_update) values(310, 1, 300, 'New York Soccer Club', 'NYSC', 'Best soccer club', 'http://img.com/nysc.png', 'http://newyorksoccerclub.org', 'info@newyorksoccerclub.org', 0, '2014-01-01 10:30:55', '2014-12-01 14:30:55');
insert into t_post(id, user_id, space_id, deleted, title, summary, content, creation_date, last_update) values(310, 300, 310, 0, 'title310', 'summary310', '<p>content310</p>', '2014-01-01 10:30:55', '2014-12-01 17:30:55');
insert into t_post(id, user_id, space_id, deleted, title, summary, content, creation_date, last_update) values(311, 300, 310, 1, 'title311', 'summary311', '<p>content311</p>', '2014-01-01 10:30:55', '2014-12-01 18:30:55');


-- findByIds_offset
insert into t_user(id, username, email, firstname, lastname, deleted, creation_date, last_update) values(400, 'ray.sponsible3', 'ray.sponsible3@gmail.com', 'Ray', 'Sponsible', 0, '2014-01-01 10:30:55', '2014-12-01 14:30:55');

insert into t_space(id, space_type_id, user_id, name, abbreviation, description, logo_url, website_url, email, deleted, creation_date, last_update) values(400, 1, 400, 'New York Soccer Club', 'NYSC', 'Best soccer club', 'http://img.com/nysc.png', 'http://newyorksoccerclub.org', 'info@newyorksoccerclub.org', 0, '2014-01-01 10:30:55', '2014-12-01 14:30:55');
insert into t_post(id, user_id, space_id, deleted, title, summary, content, creation_date, last_update) values(400, 400, 400, 0, 'title300', 'summary300', '<p>content300</p>', '2014-01-01 10:30:55', '2014-12-01 1:30:55');
insert into t_post(id, user_id, space_id, deleted, title, summary, content, creation_date, last_update) values(401, 400, 400, 0, 'title300', 'summary300', '<p>content300</p>', '2014-01-01 10:30:55', '2014-12-01 2:30:55');
insert into t_post(id, user_id, space_id, deleted, title, summary, content, creation_date, last_update) values(402, 400, 400, 0, 'title300', 'summary300', '<p>content300</p>', '2014-01-01 10:30:55', '2014-12-01 3:30:55');
insert into t_post(id, user_id, space_id, deleted, title, summary, content, creation_date, last_update) values(403, 400, 400, 0, 'title300', 'summary300', '<p>content300</p>', '2014-01-01 10:30:55', '2014-12-01 4:30:55');
insert into t_post(id, user_id, space_id, deleted, title, summary, content, creation_date, last_update) values(404, 400, 400, 0, 'title300', 'summary300', '<p>content300</p>', '2014-01-01 10:30:55', '2014-12-01 5:30:55');
insert into t_post(id, user_id, space_id, deleted, title, summary, content, creation_date, last_update) values(405, 400, 400, 0, 'title300', 'summary300', '<p>content300</p>', '2014-01-01 10:30:55', '2014-12-01 6:30:55');
insert into t_post(id, user_id, space_id, deleted, title, summary, content, creation_date, last_update) values(406, 400, 400, 0, 'title300', 'summary300', '<p>content300</p>', '2014-01-01 10:30:55', '2014-12-01 7:30:55');
insert into t_post(id, user_id, space_id, deleted, title, summary, content, creation_date, last_update) values(407, 400, 400, 0, 'title300', 'summary300', '<p>content300</p>', '2014-01-01 10:30:55', '2014-12-01 8:30:55');
insert into t_post(id, user_id, space_id, deleted, title, summary, content, creation_date, last_update) values(408, 400, 400, 0, 'title300', 'summary300', '<p>content300</p>', '2014-01-01 10:30:55', '2014-12-01 9:30:55');
insert into t_post(id, user_id, space_id, deleted, title, summary, content, creation_date, last_update) values(409, 400, 400, 0, 'title300', 'summary300', '<p>content300</p>', '2014-01-01 10:30:55', '2014-12-01 10:30:55');
