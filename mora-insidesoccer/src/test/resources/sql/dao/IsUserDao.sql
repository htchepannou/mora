-- testFindById
insert into party(party_id, party_deleted, party_status, party_date, party_creation_date, party_type_fk) values(100, 0, 1, '2014-01-01 12:30:55', '2014-01-01 10:30:55', 1);
insert into pattr(pattr_id, pattr_party_fk, pattr_name, pattr_value) values(100, 100, 'uname', 'ray.sponsible');
insert into pattr(pattr_id, pattr_party_fk, pattr_name, pattr_value) values(101, 100, 'first_name', 'Ray');
insert into pattr(pattr_id, pattr_party_fk, pattr_name, pattr_value) values(102, 100, 'last_name', 'Sponsible');
insert into pattr(pattr_id, pattr_party_fk, pattr_name, pattr_value) values(103, 100, 'email', 'ray.sponsible@gmail.com');


-- testFindById_badType
insert into party(party_id, party_deleted, party_status, party_date, party_creation_date, party_type_fk) values(500, 0, 1, '2014-01-01 12:30:55', '2014-01-01 10:30:55', 3);
insert into pattr(pattr_id, pattr_party_fk, pattr_name, pattr_value) values(500, 500, 'uname', 'ray.sponsible');
insert into pattr(pattr_id, pattr_party_fk, pattr_name, pattr_value) values(501, 500, 'first_name', 'Ray');
insert into pattr(pattr_id, pattr_party_fk, pattr_name, pattr_value) values(502, 500, 'last_name', 'Sponsible');
insert into pattr(pattr_id, pattr_party_fk, pattr_name, pattr_value) values(503, 500, 'email', 'ray.sponsible@gmail.com');

-- testFindById_deletedParty_returnNull
insert into party(party_id, party_deleted, party_status, party_date, party_creation_date, party_type_fk) values(200, 1, 1, '2014-01-01 12:30:55', '2014-01-01 10:30:55', 1);
insert into pattr(pattr_id, pattr_party_fk, pattr_name, pattr_value) values(200, 200, 'uname', '#ray.sponsible');
insert into pattr(pattr_id, pattr_party_fk, pattr_name, pattr_value) values(201, 200, 'first_name', 'Ray');
insert into pattr(pattr_id, pattr_party_fk, pattr_name, pattr_value) values(202, 200, 'last_name', 'Sponsible');
insert into pattr(pattr_id, pattr_party_fk, pattr_name, pattr_value) values(203, 200, 'email', 'deleted.ray.sponsible@gmail.com');


-- testFindByUsername
insert into party(party_id, party_deleted, party_status, party_date, party_creation_date, party_type_fk) values(300, 0, 1, '2014-01-01 12:30:55', '2014-01-01 10:30:55', 1);
insert into pattr(pattr_id, pattr_party_fk, pattr_name, pattr_value) values(300, 300, 'uname', 'duplicate');
insert into pattr(pattr_id, pattr_party_fk, pattr_name, pattr_value) values(301, 300, 'email', 'duplicate1@gmail.com');

insert into party(party_id, party_deleted, party_status, party_date, party_creation_date, party_type_fk) values(310, 0, 1, '2014-01-01 12:30:55', '2014-01-01 10:30:55', 1);
insert into pattr(pattr_id, pattr_party_fk, pattr_name, pattr_value) values(310, 310, 'uname', 'duplicate');
insert into pattr(pattr_id, pattr_party_fk, pattr_name, pattr_value) values(311, 310, 'email', 'duplicate2@gmail.com');

insert into party(party_id, party_deleted, party_status, party_date, party_creation_date, party_type_fk) values(320, 1, 1, '2014-01-01 12:30:55', '2014-01-01 10:30:55', 1);
insert into pattr(pattr_id, pattr_party_fk, pattr_name, pattr_value) values(320, 320, 'uname', 'duplicate');
insert into pattr(pattr_id, pattr_party_fk, pattr_name, pattr_value) values(321, 320, 'email', 'duplicate3@gmail.com');
insert into pattr(pattr_id, pattr_party_fk, pattr_name, pattr_value) values(322, 320, 'description', 'deleted user');

-- testFindByEmail
insert into party(party_id, party_deleted, party_status, party_date, party_creation_date, party_type_fk) values(400, 0, 1, '2014-01-01 12:30:55', '2014-01-01 10:30:55', 1);
insert into pattr(pattr_id, pattr_party_fk, pattr_name, pattr_value) values(400, 400, 'uname', 'duplicate1');
insert into pattr(pattr_id, pattr_party_fk, pattr_name, pattr_value) values(401, 400, 'email', 'duplicate@gmail.com');

insert into party(party_id, party_deleted, party_status, party_date, party_creation_date, party_type_fk) values(410, 0, 1, '2014-01-01 12:30:55', '2014-01-01 10:30:55', 1);
insert into pattr(pattr_id, pattr_party_fk, pattr_name, pattr_value) values(410, 410, 'uname', 'duplicate2');
insert into pattr(pattr_id, pattr_party_fk, pattr_name, pattr_value) values(411, 410, 'email', 'duplicate@gmail.com');

insert into party(party_id, party_deleted, party_status, party_date, party_creation_date, party_type_fk) values(420, 1, 1, '2014-01-01 12:30:55', '2014-01-01 10:30:55', 1);
insert into pattr(pattr_id, pattr_party_fk, pattr_name, pattr_value) values(420, 420, 'uname', 'duplicate3');
insert into pattr(pattr_id, pattr_party_fk, pattr_name, pattr_value) values(421, 420, 'email', 'duplicate@gmail.com');
insert into pattr(pattr_id, pattr_party_fk, pattr_name, pattr_value) values(422, 420, 'description', 'deleted user');
