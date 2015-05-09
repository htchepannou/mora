-- findById
insert into party(party_id, party_deleted, party_status, party_date, party_creation_date, party_type_fk) values(100, 0, 1, '2014-01-01 12:30:55', '2014-01-01 10:30:55', 1);
insert into node(node_id, node_channel_fk, node_owner_fk, node_type_fk, node_deleted, node_date, node_status) values(100, 100, 100, 4, 0, '2014-01-01 12:30:55', 1);
insert into nattr(nattr_id, nattr_node_fk, nattr_name, nattr_value) values(100, 100, 'title', 'title1');
insert into nattr(nattr_id, nattr_node_fk, nattr_name, nattr_value) values(101, 100, 'notes', 'This is a content1');
insert into nattr(nattr_id, nattr_node_fk, nattr_name, nattr_value) values(102, 100, 'hour', '10');
insert into nattr(nattr_id, nattr_node_fk, nattr_name, nattr_value) values(103, 100, 'minute', '30');
insert into nattr(nattr_id, nattr_node_fk, nattr_name, nattr_value) values(104, 100, 'end_hour', '15');
insert into nattr(nattr_id, nattr_node_fk, nattr_name, nattr_value) values(105, 100, 'end_time', '30');
insert into nattr(nattr_id, nattr_node_fk, nattr_name, nattr_value) values(106, 100, 'event_type', 'game');
insert into nprel(nprel_id, nprel_node_fk, nprel_party_fk, nprel_type_fk, nprel_rank) values(101, 100, 100, 4, 10000);

-- findById_invalid_type
insert into party(party_id, party_deleted, party_status, party_date, party_creation_date, party_type_fk) values(200, 0, 1, '2014-01-01 12:30:55', '2014-01-01 10:30:55', 1);
insert into node(node_id, node_channel_fk, node_owner_fk, node_type_fk, node_deleted, node_date, node_status) values(200, 200, 200, 2, 0, '2014-01-01 12:30:55', 1);
insert into nprel(nprel_id, nprel_node_fk, nprel_party_fk, nprel_type_fk) values(200, 200, 200, 1);

-- testFindIdsPublishedForUser
insert into party(party_id, party_deleted, party_status, party_date, party_creation_date, party_type_fk) values(300, 0, 1, '2014-01-01 12:30:55', '2014-01-01 10:30:55', 1);

insert into party(party_id, party_deleted, party_status, party_date, party_creation_date, party_type_fk) values(310, 0, 1, '2014-01-01 12:30:55', '2014-01-01 10:30:55', 3);
insert into prel(prel_id, prel_type_fk, prel_source_fk, prel_dest_fk) values(310, 10, 300, 310);

insert into party(party_id, party_deleted, party_status, party_date, party_creation_date, party_type_fk) values(320, 0, 1, '2014-01-01 12:30:55', '2014-01-01 10:30:55', 3);
insert into prel(prel_id, prel_type_fk, prel_source_fk, prel_dest_fk) values(320, 10, 300, 320);

insert into node(node_id, node_channel_fk, node_owner_fk, node_type_fk, node_deleted, node_date) values(300, 300, 300, 4, 0, '2014-01-01 12:30:55');
insert into nprel(nprel_id, nprel_type_fk, nprel_node_fk, nprel_party_fk) values(300, 4, 300, 310);
insert into nprel(nprel_id, nprel_type_fk, nprel_node_fk, nprel_party_fk) values(301, 4, 300, 320);

insert into node(node_id, node_channel_fk, node_owner_fk, node_type_fk, node_deleted, node_date) values(310, 300, 300, 4, 0, '2014-01-01 12:30:55');
insert into nprel(nprel_id, nprel_type_fk, nprel_node_fk, nprel_party_fk) values(310, 4, 310, 310);
insert into nprel(nprel_id, nprel_type_fk, nprel_node_fk, nprel_party_fk) values(311, 4, 310, 320);

insert into node(node_id, node_channel_fk, node_owner_fk, node_type_fk, node_deleted, node_date) values(320, 300, 300, 4, 1, '2014-01-01 12:30:55');
insert into nprel(nprel_id, nprel_type_fk, nprel_node_fk, nprel_party_fk) values(320, 4, 320, 310);
insert into nprel(nprel_id, nprel_type_fk, nprel_node_fk, nprel_party_fk) values(321, 4, 320, 320);
