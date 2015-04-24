-- testFindBySourceByDestinationByType
insert into party(party_id, party_deleted, party_status, party_date, party_creation_date, party_type_fk) values(100, 0, 1, '2014-01-01 12:30:55', '2014-01-01 10:30:55', 1);
insert into party(party_id, party_deleted, party_status, party_date, party_creation_date, party_type_fk) values(110, 0, 1, '2014-01-01 12:30:55', '2014-01-01 10:30:55', 1);

insert into prel(prel_id, prel_type_fk, prel_source_fk, prel_dest_fk, prel_rank, prel_qualifier) values(100, 1, 100, 110, 1, 'foo');

-- testFindBySourceByType
insert into party(party_id, party_deleted, party_status, party_date, party_creation_date, party_type_fk) values(200, 0, 1, '2014-01-01 12:30:55', '2014-01-01 10:30:55', 1);
insert into party(party_id, party_deleted, party_status, party_date, party_creation_date, party_type_fk) values(210, 0, 1, '2014-01-01 12:30:55', '2014-01-01 10:30:55', 1);
insert into party(party_id, party_deleted, party_status, party_date, party_creation_date, party_type_fk) values(220, 0, 1, '2014-01-01 12:30:55', '2014-01-01 10:30:55', 1);
insert into party(party_id, party_deleted, party_status, party_date, party_creation_date, party_type_fk) values(230, 1, 1, '2014-01-01 12:30:55', '2014-01-01 10:30:55', 1);

insert into prel(prel_id, prel_type_fk, prel_source_fk, prel_dest_fk, prel_rank, prel_qualifier) values(200, 1, 200, 210, 1, 'foo');
insert into prel(prel_id, prel_type_fk, prel_source_fk, prel_dest_fk, prel_rank, prel_qualifier) values(201, 1, 200, 220, 2, 'foo');
insert into prel(prel_id, prel_type_fk, prel_source_fk, prel_dest_fk, prel_rank, prel_qualifier) values(202, 1, 200, 230, 3, 'foo');


-- testFindBySourceByDestinationsByType
insert into party(party_id, party_deleted, party_status, party_date, party_creation_date, party_type_fk) values(300, 0, 1, '3014-01-01 12:30:55', '3014-01-01 10:30:55', 1);
insert into party(party_id, party_deleted, party_status, party_date, party_creation_date, party_type_fk) values(310, 0, 1, '3014-01-01 12:30:55', '3014-01-01 10:30:55', 1);
insert into party(party_id, party_deleted, party_status, party_date, party_creation_date, party_type_fk) values(320, 0, 1, '3014-01-01 12:30:55', '3014-01-01 10:30:55', 1);
insert into party(party_id, party_deleted, party_status, party_date, party_creation_date, party_type_fk) values(330, 1, 1, '3014-01-01 12:30:55', '3014-01-01 10:30:55', 1);
insert into party(party_id, party_deleted, party_status, party_date, party_creation_date, party_type_fk) values(340, 0, 1, '3014-01-01 12:30:55', '3014-01-01 10:30:55', 1);
insert into party(party_id, party_deleted, party_status, party_date, party_creation_date, party_type_fk) values(350, 0, 1, '3014-01-01 12:30:55', '3014-01-01 10:30:55', 1);

insert into prel(prel_id, prel_type_fk, prel_source_fk, prel_dest_fk, prel_rank, prel_qualifier) values(300, 1, 300, 310, 1, 'foo');
insert into prel(prel_id, prel_type_fk, prel_source_fk, prel_dest_fk, prel_rank, prel_qualifier) values(301, 1, 300, 320, 2, 'foo');
insert into prel(prel_id, prel_type_fk, prel_source_fk, prel_dest_fk, prel_rank, prel_qualifier) values(302, 1, 300, 330, 3, 'foo');
