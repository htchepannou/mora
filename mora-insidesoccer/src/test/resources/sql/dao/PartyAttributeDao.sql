insert into party(party_id, party_deleted, party_status, party_date, party_creation_date, party_type_fk) values(1, 0, 1, '2014-01-01 12:30:55', '2014-01-01 10:30:55', 1);

insert into pattr(pattr_id, pattr_party_fk, pattr_name, pattr_value) values(1, 1, 'title', 'This is title');
insert into pattr(pattr_id, pattr_party_fk, pattr_name, pattr_value) values(2, 1, 'description', 'This is description');
insert into pattr(pattr_id, pattr_party_fk, pattr_name, pattr_value) values(3, 1, 'age', '3');
insert into pattr(pattr_id, pattr_party_fk, pattr_name, pattr_value) values(4, 1, 'email', 'user1@gmail.com');

insert into party(party_id, party_deleted, party_status, party_date, party_creation_date, party_type_fk, party_owner_fk) values(10, 1, 1, '2014-01-01 12:30:55', '2014-01-01 10:30:55', 3, 1);
insert into pattr(pattr_id, pattr_party_fk, pattr_name, pattr_value) values(10, 10, 'title', 'This is title #2');
insert into pattr(pattr_id, pattr_party_fk, pattr_name, pattr_value) values(11, 10, 'description', 'This is description #2');
insert into pattr(pattr_id, pattr_party_fk, pattr_name, pattr_value) values(12, 10, 'email', 'user1@gmail.com');

insert into party(party_id, party_deleted, party_status, party_date, party_creation_date, party_type_fk, party_owner_fk) values(11, 0, 1, '2014-01-01 12:30:55', '2014-01-01 10:30:55', 3, 1);
insert into pattr(pattr_id, pattr_party_fk, pattr_name, pattr_value) values(20, 11, 'title', 'This is title #3');
insert into pattr(pattr_id, pattr_party_fk, pattr_name, pattr_value) values(21, 11, 'email', 'user1@gmail.com');


