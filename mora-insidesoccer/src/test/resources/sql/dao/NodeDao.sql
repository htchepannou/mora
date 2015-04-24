-- findById
insert into party(party_id, party_deleted, party_status, party_date, party_creation_date, party_type_fk) values(101, 0, 1, '2014-01-01 12:30:55', '2014-01-01 10:30:55', 1);
insert into party(party_id, party_deleted, party_status, party_date, party_creation_date, party_type_fk) values(102, 0, 1, '2014-01-01 12:30:55', '2014-01-01 10:30:55', 1);

insert into node(node_id, node_channel_fk, node_owner_fk, node_type_fk, node_deleted, node_date, node_status) values(100, 101, 102, 1, 0, '2014-01-01 12:30:55', 3);

-- findById_deleted
insert into party(party_id, party_deleted, party_status, party_date, party_creation_date, party_type_fk) values(201, 0, 1, '2014-01-01 12:30:55', '2014-01-01 10:30:55', 1);
insert into party(party_id, party_deleted, party_status, party_date, party_creation_date, party_type_fk) values(202, 0, 1, '2014-01-01 12:30:55', '2014-01-01 10:30:55', 1);

insert into node(node_id, node_channel_fk, node_owner_fk, node_type_fk, node_deleted, node_date, node_status) values(200, 201, 202, 1, 1, '2014-01-01 12:30:55', 3);

-- findInNodes
insert into party(party_id, party_deleted, party_status, party_date, party_creation_date, party_type_fk) values(301, 0, 1, '2014-01-01 12:30:55', '2014-01-01 10:30:55', 1);
insert into party(party_id, party_deleted, party_status, party_date, party_creation_date, party_type_fk) values(302, 0, 1, '2014-01-01 12:30:55', '2014-01-01 10:30:55', 1);

insert into node(node_id, node_channel_fk, node_owner_fk, node_type_fk, node_deleted, node_date, node_status) values(300, 301, 302, 1, 0, '2014-01-01 12:30:55', 3);
insert into node(node_id, node_channel_fk, node_owner_fk, node_type_fk, node_deleted, node_date, node_status) values(311, 301, 302, 1, 0, '2014-01-01 12:30:55', 3);
insert into node(node_id, node_channel_fk, node_owner_fk, node_type_fk, node_deleted, node_date, node_status) values(312, 301, 302, 1, 0, '2014-01-01 12:30:55', 3);
insert into node(node_id, node_channel_fk, node_owner_fk, node_type_fk, node_deleted, node_date, node_status) values(313, 301, 302, 1, 1, '2014-01-01 12:30:55', 3);
insert into node(node_id, node_channel_fk, node_owner_fk, node_type_fk, node_deleted, node_date, node_status) values(314, 301, 302, 1, 0, '2014-01-01 12:30:55', 3);

insert into nrel(nrel_id, nrel_type_fk, nrel_source_fk, nrel_dest_fk, nrel_rank) values(300, 100, 311, 300, 1);
insert into nrel(nrel_id, nrel_type_fk, nrel_source_fk, nrel_dest_fk, nrel_rank) values(301, 100, 312, 300, 2);
insert into nrel(nrel_id, nrel_type_fk, nrel_source_fk, nrel_dest_fk, nrel_rank) values(302, 100, 313, 300, 3);
insert into nrel(nrel_id, nrel_type_fk, nrel_source_fk, nrel_dest_fk, nrel_rank) values(303, 30,  314, 300, 4);
