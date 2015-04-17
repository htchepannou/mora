insert into party(party_id, party_deleted, party_status, party_date, party_creation_date, party_type_fk) values(1, 0, 1, '2014-01-01 12:30:55', '2014-01-01 10:30:55', 3);


-- testFindById_team
insert into party(party_id, party_deleted, party_status, party_date, party_creation_date, party_type_fk, party_owner_fk) values(100, 0, 1, '2014-01-01 12:30:55', '2014-01-01 10:30:55', 3, 1);
insert into pattr(pattr_id, pattr_party_fk, pattr_name, pattr_value) values(100, 100, 'name', 'name1');
insert into pattr(pattr_id, pattr_party_fk, pattr_name, pattr_value) values(101, 100, 'description', 'description1');
insert into pattr(pattr_id, pattr_party_fk, pattr_name, pattr_value) values(102, 100, 'logo_url', 'http://google.ca/logo.png');
insert into pattr(pattr_id, pattr_party_fk, pattr_name, pattr_value) values(103, 100, 'website_url', 'http://google.ca');
insert into pattr(pattr_id, pattr_party_fk, pattr_name, pattr_value) values(104, 100, 'email', 'info@google.ca');
insert into pattr(pattr_id, pattr_party_fk, pattr_name, pattr_value) values(105, 100, 'city', 'Montreal');
insert into pattr(pattr_id, pattr_party_fk, pattr_name, pattr_value) values(106, 100, 'country', 'CA');

-- testFindById_club
insert into party(party_id, party_deleted, party_status, party_date, party_creation_date, party_type_fk, party_owner_fk) values(500, 0, 1, '2014-01-01 12:30:55', '2014-01-01 10:30:55', 4, 1);
insert into pattr(pattr_id, pattr_party_fk, pattr_name, pattr_value) values(500, 500, 'name', 'name1');
insert into pattr(pattr_id, pattr_party_fk, pattr_name, pattr_value) values(501, 500, 'description', 'description1');
insert into pattr(pattr_id, pattr_party_fk, pattr_name, pattr_value) values(502, 500, 'logo_url', 'http://google.ca/logo.png');
insert into pattr(pattr_id, pattr_party_fk, pattr_name, pattr_value) values(503, 500, 'website_url', 'http://google.ca');
insert into pattr(pattr_id, pattr_party_fk, pattr_name, pattr_value) values(504, 500, 'email', 'info@google.ca');
insert into pattr(pattr_id, pattr_party_fk, pattr_name, pattr_value) values(505, 500, 'city', 'Montreal');
insert into pattr(pattr_id, pattr_party_fk, pattr_name, pattr_value) values(506, 500, 'country', 'CA');

-- testFindById_deleted
insert into party(party_id, party_deleted, party_status, party_date, party_creation_date, party_type_fk, party_owner_fk) values(200, 1, 1, '2014-01-01 12:30:55', '2014-01-01 10:30:55', 3, 1);

-- testFindById_person
insert into party(party_id, party_deleted, party_status, party_date, party_creation_date, party_type_fk, party_owner_fk) values(300, 0, 1, '2014-01-01 12:30:55', '2014-01-01 10:30:55', 1, 1);

-- testFindById_family
insert into party(party_id, party_deleted, party_status, party_date, party_creation_date, party_type_fk, party_owner_fk) values(400, 0, 1, '2014-01-01 12:30:55', '2014-01-01 10:30:55', 2, 1);
