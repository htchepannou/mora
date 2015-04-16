insert into party(party_id, party_deleted, party_status, party_date, party_creation_date, party_type_fk) values(1, 0, 1, '2014-01-01 12:30:55', '2014-01-01 10:30:55', 1);

insert into pattr(pattr_id, pattr_party_fk, pattr_name, pattr_value) values(1, 1, 'title', 'This is title');
insert into pattr(pattr_id, pattr_party_fk, pattr_name, pattr_value) values(2, 1, 'description', 'This is description');
insert into pattr(pattr_id, pattr_party_fk, pattr_name, pattr_value) values(3, 1, 'age', '3');
insert into pattr(pattr_id, pattr_party_fk, pattr_name, pattr_value) values(4, 1, 'sex', 'F');

insert into party(party_id, party_deleted, party_status, party_date, party_creation_date, party_type_fk, party_owner_fk) values(10, 0, 1, '2014-01-01 12:30:55', '2014-01-01 10:30:55', 3, 1);
insert into pattr(pattr_id, pattr_party_fk, pattr_name, pattr_value) values(5, 10, 'title', 'This is title #2');
insert into pattr(pattr_id, pattr_party_fk, pattr_name, pattr_value) values(6, 10, 'description', 'This is description #2');


