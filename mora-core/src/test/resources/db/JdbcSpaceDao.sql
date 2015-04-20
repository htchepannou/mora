insert into t_user(id, username, email, firstname, lastname, deleted, creation_date, last_update) values(1, 'ray.sponsible', 'ray.sponsible@gmail.com', 'Ray', 'Sponsible', 0, '2014-01-01 10:30:55', '2014-12-01 14:30:55');

insert into t_space_type (id, name) values(1, 'club');

insert into t_space(id, space_type_id, user_id, name, abbreviation, description, logo_url, website_url, email, deleted, creation_date, last_update) values(1, 1, 1, 'New York Soccer Club', 'NYSC', 'Best soccer club', 'http://img.com/nysc.png', 'http://newyorksoccerclub.org', 'info@newyorksoccerclub.org', 0, '2014-01-01 10:30:55', '2014-12-01 14:30:55');
insert into t_space(id, space_type_id, user_id, name, abbreviation, description, logo_url, website_url, email, deleted, creation_date, last_update) values(2, 1, 1, 'Deleted', 'DEL', null, 'http://img.com/nysc.png', 'http://newyorksoccerclub.org', 'info@newyorksoccerclub.org', 1, '2014-01-01 10:30:55', '2014-12-01 14:30:55');
insert into t_space(id, space_type_id, user_id, name, abbreviation, description, logo_url, website_url, email, deleted, creation_date, last_update) values(3, 1, 1, 'Canadien de Montreal', 'CH', 'Best hockey club', 'http://canadiensdemontral.com/logo.png', 'http://canadiensdemontral.com', 'info@canadiensdemontral.com', 0, '2014-01-01 10:30:55', '2014-12-01 14:30:55');
