-- testFindById
insert into party(party_id, party_deleted, party_status, party_date, party_creation_date, party_type_fk) values(100, 0, 1, '2014-01-01 12:30:55', '2014-01-01 10:30:55', 3);

insert into party(party_id, party_deleted, party_status, party_date, party_creation_date, party_type_fk) values(101, 0, 1, '2014-01-01 12:30:55', '2014-01-01 10:30:55', 1);
insert into prel(prel_id, prel_type_fk, prel_source_fk, prel_dest_fk, prel_qualifier, prel_rank) values (101, 10, 101, 100, 1, 1);

-- testFindById_deleteSpace
insert into party(party_id, party_deleted, party_status, party_date, party_creation_date, party_type_fk) values(200, 1, 1, '2014-01-01 12:30:55', '2014-01-01 10:30:55', 3);

insert into party(party_id, party_deleted, party_status, party_date, party_creation_date, party_type_fk) values(201, 0, 1, '2014-01-01 12:30:55', '2014-01-01 10:30:55', 1);
insert into prel(prel_id, prel_type_fk, prel_source_fk, prel_dest_fk, prel_qualifier, prel_rank) values (201, 10, 201, 200, 1, 1);

-- testFindById_deleteUser
insert into party(party_id, party_deleted, party_status, party_date, party_creation_date, party_type_fk) values(300, 0, 1, '2014-01-01 12:30:55', '2014-01-01 10:30:55', 3);

insert into party(party_id, party_deleted, party_status, party_date, party_creation_date, party_type_fk) values(301, 1, 1, '2014-01-01 12:30:55', '2014-01-01 10:30:55', 1);
insert into prel(prel_id, prel_type_fk, prel_source_fk, prel_dest_fk, prel_qualifier, prel_rank) values (301, 10, 301, 300, 1, 1);

-- testFindById_invalidType
insert into party(party_id, party_deleted, party_status, party_date, party_creation_date, party_type_fk) values(400, 0, 1, '2014-01-01 12:30:55', '2014-01-01 10:30:55', 3);

insert into party(party_id, party_deleted, party_status, party_date, party_creation_date, party_type_fk) values(401, 0, 1, '2014-01-01 12:30:55', '2014-01-01 10:30:55', 1);
insert into prel(prel_id, prel_type_fk, prel_source_fk, prel_dest_fk, prel_qualifier, prel_rank) values (401, 1, 401, 400, 1, 1);

-- testFindByUser
insert into party(party_id, party_deleted, party_status, party_date, party_creation_date, party_type_fk) values(500, 0, 1, '2014-01-01 12:30:55', '2014-01-01 10:30:55', 3);
insert into party(party_id, party_deleted, party_status, party_date, party_creation_date, party_type_fk) values(510, 0, 1, '2014-01-01 12:30:55', '2014-01-01 10:30:55', 3);
insert into party(party_id, party_deleted, party_status, party_date, party_creation_date, party_type_fk) values(520, 1, 1, '2014-01-01 12:30:55', '2014-01-01 10:30:55', 3);

insert into party(party_id, party_deleted, party_status, party_date, party_creation_date, party_type_fk) values(501, 0, 1, '2014-01-01 12:30:55', '2014-01-01 10:30:55', 1);
insert into prel(prel_id, prel_type_fk, prel_source_fk, prel_dest_fk, prel_qualifier, prel_rank) values (501, 10, 501, 500, 1, 1);
insert into prel(prel_id, prel_type_fk, prel_source_fk, prel_dest_fk, prel_qualifier, prel_rank) values (502, 10, 501, 510, 1, 1);
insert into prel(prel_id, prel_type_fk, prel_source_fk, prel_dest_fk, prel_qualifier, prel_rank) values (503, 10, 501, 520, 1, 1);
insert into prel(prel_id, prel_type_fk, prel_source_fk, prel_dest_fk, prel_qualifier, prel_rank) values (504, 1,  501, 510, 8, 1);
