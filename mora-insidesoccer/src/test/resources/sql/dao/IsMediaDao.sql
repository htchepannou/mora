-- findById
insert into party(party_id, party_deleted, party_status, party_date, party_creation_date, party_type_fk) values(100, 0, 1, '2014-01-01 12:30:55', '2014-01-01 10:30:55', 1);

insert into node(node_id, node_channel_fk, node_owner_fk, node_type_fk, node_deleted, node_date, node_status) values(100, 100, 100, 100, 0, '2014-01-01 12:30:55', 1);
insert into nattr(nattr_id, nattr_node_fk, nattr_name, nattr_value) values(100, 100, 'title', 'title1');
insert into nattr(nattr_id, nattr_node_fk, nattr_name, nattr_value) values(101, 100, 'description', 'This is a content1');
insert into nattr(nattr_id, nattr_node_fk, nattr_name, nattr_value) values(103, 100, 'url', 'http://www.google.ca/logo.jpg');
insert into nattr(nattr_id, nattr_node_fk, nattr_name, nattr_value) values(104, 100, 'image_url', 'http://www.google.ca/logo.jpg');
insert into nattr(nattr_id, nattr_node_fk, nattr_name, nattr_value) values(105, 100, 'foo', 'bar');


-- findById_badType
insert into party(party_id, party_deleted, party_status, party_date, party_creation_date, party_type_fk) values(200, 0, 1, '2014-01-01 12:30:55', '2014-01-01 10:30:55', 1);

insert into node(node_id, node_channel_fk, node_owner_fk, node_type_fk, node_deleted, node_date, node_status) values(200, 200, 200, 1, 0, '2014-01-01 12:30:55', 1);


-- testFindByOwnerByAttachmentType
insert into party(party_id, party_deleted, party_status, party_date, party_creation_date, party_type_fk) values(300, 0, 1, '2014-01-01 12:30:55', '2014-01-01 10:30:55', 1);

insert into node(node_id, node_channel_fk, node_owner_fk, node_type_fk, node_deleted, node_date, node_status) values(300, 300, 300, 100, 0, '2014-01-01 12:30:55', 1);
insert into nattr(nattr_id, nattr_node_fk, nattr_name, nattr_value) values(300, 300, 'title', 'title1');
insert into nattr(nattr_id, nattr_node_fk, nattr_name, nattr_value) values(301, 300, 'description', 'This is a content1');
insert into nattr(nattr_id, nattr_node_fk, nattr_name, nattr_value) values(303, 300, 'url', 'http://www.google.ca/logo.jpg');
insert into nattr(nattr_id, nattr_node_fk, nattr_name, nattr_value) values(304, 300, 'image_url', 'http://www.google.ca/logo.jpg');
insert into nattr(nattr_id, nattr_node_fk, nattr_name, nattr_value) values(305, 300, 'foo', 'bar');

insert into node(node_id, node_channel_fk, node_owner_fk, node_type_fk, node_deleted, node_date, node_status) values(310, 300, 300, 100, 0, '2014-01-01 12:31:55', 1);
insert into nattr(nattr_id, nattr_node_fk, nattr_name, nattr_value) values(310, 310, 'title', 'title1');
insert into nattr(nattr_id, nattr_node_fk, nattr_name, nattr_value) values(311, 310, 'description', 'This is a content1');
insert into nattr(nattr_id, nattr_node_fk, nattr_name, nattr_value) values(313, 310, 'url', 'http://www.google.ca/logo.jpg');
insert into nattr(nattr_id, nattr_node_fk, nattr_name, nattr_value) values(314, 310, 'image_url', 'http://www.google.ca/logo.jpg');
insert into nattr(nattr_id, nattr_node_fk, nattr_name, nattr_value) values(315, 310, 'foo', 'bar');


insert into node(node_id, node_channel_fk, node_owner_fk, node_type_fk, node_deleted, node_date, node_status) values(399, 300, 300, 100, 0, '2014-01-01 12:31:55', 1);
insert into nprel(nprel_id, nprel_node_fk, nprel_party_fk, nprel_type_fk, nprel_rank) values(399, 399, 300, 1, 10000);
insert into nrel(nrel_id, nrel_type_fk, nrel_source_fk, nrel_dest_fk, nrel_rank) values(300, 100, 310, 399, 1);
insert into nrel(nrel_id, nrel_type_fk, nrel_source_fk, nrel_dest_fk, nrel_rank) values(301, 100, 300, 399, 1);

