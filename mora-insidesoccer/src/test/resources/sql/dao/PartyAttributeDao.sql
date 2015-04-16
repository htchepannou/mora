-- testFindByPartyByNames
insert into party(party_id, party_deleted, party_status, party_date, party_creation_date, party_type_fk) values(100, 0, 1, '2014-01-01 12:30:55', '2014-01-01 10:30:55', 1);
insert into pattr(pattr_id, pattr_party_fk, pattr_name, pattr_value) values(100, 100, 'title', 'title1');
insert into pattr(pattr_id, pattr_party_fk, pattr_name, pattr_value) values(101, 100, 'description', 'description1');
insert into pattr(pattr_id, pattr_party_fk, pattr_name, pattr_value) values(102, 100, 'age', '3');
insert into pattr(pattr_id, pattr_party_fk, pattr_name, pattr_value) values(103, 100, 'email', 'user1@gmail.com');

insert into party(party_id, party_deleted, party_status, party_date, party_creation_date, party_type_fk) values(110, 0, 1, '2014-01-01 12:30:55', '2014-01-01 10:30:55', 1);
insert into pattr(pattr_id, pattr_party_fk, pattr_name, pattr_value) values(110, 110, 'title', 'title2');
insert into pattr(pattr_id, pattr_party_fk, pattr_name, pattr_value) values(111, 110, 'description', 'description2');


-- testFindByPartyByNames_BadParty_returnsEmpty
insert into party(party_id, party_deleted, party_status, party_date, party_creation_date, party_type_fk) values(200, 1, 1, '2014-01-01 12:30:55', '2014-01-01 10:30:55', 1);
insert into pattr(pattr_id, pattr_party_fk, pattr_name, pattr_value) values(200, 200, 'title', 'title1');
insert into pattr(pattr_id, pattr_party_fk, pattr_name, pattr_value) values(201, 200, 'description', 'description2');


-- testFindByNameByValueByPartyType
insert into party(party_id, party_deleted, party_status, party_date, party_creation_date, party_type_fk) values(300, 0, 1, '2014-01-01 12:30:55', '2014-01-01 10:30:55', 1);
insert into pattr(pattr_id, pattr_party_fk, pattr_name, pattr_value) values(300, 300, 'title', 'duplicate');

insert into party(party_id, party_deleted, party_status, party_date, party_creation_date, party_type_fk) values(310, 0, 1, '2014-01-01 12:30:55', '2014-01-01 10:30:55', 1);
insert into pattr(pattr_id, pattr_party_fk, pattr_name, pattr_value) values(310, 310, 'title', 'duplicate');

insert into party(party_id, party_deleted, party_status, party_date, party_creation_date, party_type_fk) values(380, 1, 1, '2014-01-01 12:30:55', '2014-01-01 10:30:55', 1);
insert into pattr(pattr_id, pattr_party_fk, pattr_name, pattr_value) values(380, 380, 'title', 'duplicate');
insert into pattr(pattr_id, pattr_party_fk, pattr_name, pattr_value) values(381, 380, 'description', 'deleted object');

insert into party(party_id, party_deleted, party_status, party_date, party_creation_date, party_type_fk) values(390, 0, 1, '2014-01-01 12:30:55', '2014-01-01 10:30:55', 2);
insert into pattr(pattr_id, pattr_party_fk, pattr_name, pattr_value) values(390, 390, 'title', 'duplicate');
insert into pattr(pattr_id, pattr_party_fk, pattr_name, pattr_value) values(391, 390, 'description', 'different type');

