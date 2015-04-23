-- findById
insert into party(party_id, party_deleted, party_status, party_date, party_creation_date, party_type_fk) values(100, 0, 1, '2014-01-01 12:30:55', '2014-01-01 10:30:55', 1);

insert into node(node_id, node_channel_fk, node_owner_fk, node_type_fk, node_deleted, node_date, node_status) values(100, 100, 100, 100, 0, '2014-01-01 12:30:55', 1);
insert into nattr(nattr_id, nattr_node_fk, nattr_name, nattr_value) values(100, 100, 'title', 'title1');
insert into nattr(nattr_id, nattr_node_fk, nattr_name, nattr_value) values(101, 100, 'description', 'This is a content1');
insert into nattr(nattr_id, nattr_node_fk, nattr_name, nattr_value) values(103, 100, 'url', 'http://www.google.ca/logo.jpg');
insert into nattr(nattr_id, nattr_node_fk, nattr_name, nattr_value) values(104, 100, 'image_url', 'http://www.google.ca/logo.jpg');
insert into nattr(nattr_id, nattr_node_fk, nattr_name, nattr_value) values(105, 100, 'foo', 'bar');

insert into nprel(nprel_id, nprel_node_fk, nprel_party_fk, nprel_type_fk) values(101, 100, 100, 1);
