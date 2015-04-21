-- findById
insert into party(party_id, party_deleted, party_status, party_date, party_creation_date, party_type_fk) values(100, 0, 1, '2014-01-01 12:30:55', '2014-01-01 10:30:55', 1);
insert into node(node_id, node_channel_fk, node_owner_fk, node_type_fk, node_deleted, node_date, node_status) values(100, 100, 100, 1, 0, '2014-01-01 12:30:55', 1);
insert into nattr(nattr_id, nattr_node_fk, nattr_name, nattr_value) values(100, 100, 'title', 'title1');
insert into nattr(nattr_id, nattr_node_fk, nattr_name, nattr_value) values(101, 100, 'content', 'This is a content1');
insert into nattr(nattr_id, nattr_node_fk, nattr_name, nattr_value) values(102, 100, 'foo', 'bar');
insert into nprel(nprel_id, nprel_node_fk, nprel_party_fk, nprel_type_fk) values(101, 100, 100, 1);

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

insert into node(node_id, node_channel_fk, node_owner_fk, node_type_fk, node_deleted, node_date) values(300, 300, 300, 1, 0, '2014-01-01 12:30:55');
insert into nprel(nprel_id, nprel_type_fk, nprel_node_fk, nprel_party_fk) values(300, 1, 300, 310);
insert into nprel(nprel_id, nprel_type_fk, nprel_node_fk, nprel_party_fk) values(301, 1, 300, 320);

insert into node(node_id, node_channel_fk, node_owner_fk, node_type_fk, node_deleted, node_date) values(310, 300, 300, 1, 0, '2014-01-01 12:30:55');
insert into nprel(nprel_id, nprel_type_fk, nprel_node_fk, nprel_party_fk) values(310, 1, 310, 310);
insert into nprel(nprel_id, nprel_type_fk, nprel_node_fk, nprel_party_fk) values(311, 1, 310, 320);

insert into node(node_id, node_channel_fk, node_owner_fk, node_type_fk, node_deleted, node_date) values(320, 300, 300, 1, 1, '2014-01-01 12:30:55');
insert into nprel(nprel_id, nprel_type_fk, nprel_node_fk, nprel_party_fk) values(320, 1, 320, 310);
insert into nprel(nprel_id, nprel_type_fk, nprel_node_fk, nprel_party_fk) values(321, 1, 320, 320);

-- testFindByIds
insert into party(party_id, party_deleted, party_status, party_date, party_creation_date, party_type_fk) values(400, 0, 1, '2014-01-01 12:30:55', '2014-01-01 10:30:55', 1);
insert into node(node_id, node_channel_fk, node_owner_fk, node_type_fk, node_deleted, node_date, node_status) values(400, 400, 400, 1, 0, '2014-01-01 12:30:55', 1);
insert into nattr(nattr_id, nattr_node_fk, nattr_name, nattr_value) values(400, 400, 'title', 'title1');
insert into nattr(nattr_id, nattr_node_fk, nattr_name, nattr_value) values(401, 400, 'content', 'This is a content1');
insert into nattr(nattr_id, nattr_node_fk, nattr_name, nattr_value) values(402, 400, 'foo', 'bar');
insert into nprel(nprel_id, nprel_node_fk, nprel_party_fk, nprel_type_fk) values(401, 400, 400, 1);

insert into party(party_id, party_deleted, party_status, party_date, party_creation_date, party_type_fk) values(410, 0, 1, '2014-01-01 12:30:55', '2014-01-01 10:30:55', 1);
insert into node(node_id, node_channel_fk, node_owner_fk, node_type_fk, node_deleted, node_date, node_status) values(410, 410, 410, 1, 0, '2014-01-01 12:30:55', 1);
insert into nattr(nattr_id, nattr_node_fk, nattr_name, nattr_value) values(410, 410, 'title', 'title2');
insert into nattr(nattr_id, nattr_node_fk, nattr_name, nattr_value) values(411, 410, 'content', 'This is a content2');
insert into nprel(nprel_id, nprel_node_fk, nprel_party_fk, nprel_type_fk) values(411, 410, 410, 1);
