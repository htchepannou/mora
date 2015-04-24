-- findById
insert into party(party_id, party_deleted, party_status, party_date, party_creation_date, party_type_fk) values(101, 0, 1, '2014-01-01 12:30:55', '2014-01-01 10:30:55', 1);
insert into party(party_id, party_deleted, party_status, party_date, party_creation_date, party_type_fk) values(102, 0, 1, '2014-01-01 12:30:55', '2014-01-01 10:30:55', 1);

insert into node(node_id, node_channel_fk, node_owner_fk, node_type_fk, node_deleted, node_date, node_status) values(100, 101, 102, 1, 0, '2014-01-01 12:30:55', 3);

-- findById_deleted
insert into party(party_id, party_deleted, party_status, party_date, party_creation_date, party_type_fk) values(201, 0, 1, '2014-01-01 12:30:55', '2014-01-01 10:30:55', 1);
insert into party(party_id, party_deleted, party_status, party_date, party_creation_date, party_type_fk) values(202, 0, 1, '2014-01-01 12:30:55', '2014-01-01 10:30:55', 1);

insert into node(node_id, node_channel_fk, node_owner_fk, node_type_fk, node_deleted, node_date, node_status) values(200, 201, 202, 1, 1, '2014-01-01 12:30:55', 3);
