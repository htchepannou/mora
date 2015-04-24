-- testFindById
insert into party(party_id, party_deleted, party_status, party_date, party_creation_date, party_type_fk) values(100, 0, 1, '2014-01-01 12:30:55', '2014-01-01 10:30:55', 1);
insert into node(node_id, node_channel_fk, node_owner_fk, node_type_fk, node_deleted, node_date, node_status) values(100, 100, 100, 1, 0, '2014-01-01 12:30:55', 1);
insert into nprel(nprel_id, nprel_node_fk, nprel_party_fk, nprel_type_fk, nprel_rank) values(101, 100, 100, 1, 1000);

-- testFindById_deleted
insert into party(party_id, party_deleted, party_status, party_date, party_creation_date, party_type_fk) values(200, 0, 1, '2014-01-01 12:30:55', '2014-01-01 10:30:55', 1);
insert into node(node_id, node_channel_fk, node_owner_fk, node_type_fk, node_deleted, node_date, node_status) values(200, 200, 200, 1, 1, '2014-01-01 12:30:55', 1);
insert into nprel(nprel_id, nprel_node_fk, nprel_party_fk, nprel_type_fk) values(200, 200, 200, 1);

-- testFindIdsByRelationshhipTypeByParties
insert into party(party_id, party_deleted, party_status, party_date, party_creation_date, party_type_fk) values(300, 0, 1, '2014-01-01 12:30:55', '2014-01-01 10:30:55', 1);

insert into node(node_id, node_channel_fk, node_owner_fk, node_type_fk, node_deleted, node_date, node_status) values(300, 300, 300, 1, 0, '2014-01-01 12:30:55', 1);
insert into node(node_id, node_channel_fk, node_owner_fk, node_type_fk, node_deleted, node_date, node_status) values(301, 300, 300, 1, 0, '2014-01-01 12:30:55', 1);
insert into node(node_id, node_channel_fk, node_owner_fk, node_type_fk, node_deleted, node_date, node_status) values(302, 300, 300, 1, 1, '2014-01-01 12:30:55', 1);

insert into nprel(nprel_id, nprel_node_fk, nprel_party_fk, nprel_type_fk, nprel_rank) values(300, 300, 300, 1, 3000);
insert into nprel(nprel_id, nprel_node_fk, nprel_party_fk, nprel_type_fk) values(301, 301, 300, 1);
insert into nprel(nprel_id, nprel_node_fk, nprel_party_fk, nprel_type_fk) values(302, 302, 300, 1);


insert into party(party_id, party_deleted, party_status, party_date, party_creation_date, party_type_fk) values(310, 0, 1, '2014-01-01 12:30:55', '2014-01-01 10:30:55', 1);

insert into node(node_id, node_channel_fk, node_owner_fk, node_type_fk, node_deleted, node_date, node_status) values(310, 310, 300, 1, 0, '2014-01-01 12:30:55', 1);
insert into nprel(nprel_id, nprel_node_fk, nprel_party_fk, nprel_type_fk) values(310, 310, 300, 1);
