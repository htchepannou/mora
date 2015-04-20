-- testFindByNodeByNames
insert into party(party_id, party_deleted, party_status, party_date, party_creation_date, party_type_fk) values(100, 0, 1, '2014-01-01 12:30:55', '2014-01-01 10:30:55', 1);
insert into node(node_id, node_channel_fk, node_owner_fk, node_type_fk, node_deleted, node_date, node_status) values(100, 100, 100, 1, 0, '2014-01-01 12:30:55', 1);

insert into nattr(nattr_id, nattr_node_fk, nattr_name, nattr_value) values(100, 100, 'title', 'title1');
insert into nattr(nattr_id, nattr_node_fk, nattr_name, nattr_value) values(101, 100, 'description', 'description1');
insert into nattr(nattr_id, nattr_node_fk, nattr_name, nattr_value) values(102, 100, 'age', '3');
insert into nattr(nattr_id, nattr_node_fk, nattr_name, nattr_value) values(103, 100, 'email', 'user1@gmail.com');

insert into node(node_id, node_deleted, node_status, node_date, node_type_fk, node_owner_fk) values(110, 0, 1, '2014-01-01 12:30:55', 1, 100);
insert into nattr(nattr_id, nattr_node_fk, nattr_name, nattr_value) values(110, 110, 'title', 'title2');
insert into nattr(nattr_id, nattr_node_fk, nattr_name, nattr_value) values(111, 110, 'description', 'description2');


-- testFindByNodeByNames_deletedNode
insert into party(party_id, party_deleted, party_status, party_date, party_creation_date, party_type_fk) values(200, 0, 1, '2014-01-01 12:30:55', '2014-01-01 10:30:55', 1);
insert into node(node_id, node_channel_fk, node_owner_fk, node_type_fk, node_deleted, node_date, node_status) values(200, 200, 200, 1, 1, '2014-01-01 12:30:55', 1);

insert into nattr(nattr_id, nattr_node_fk, nattr_name, nattr_value) values(200, 200, 'title', 'title1');
insert into nattr(nattr_id, nattr_node_fk, nattr_name, nattr_value) values(201, 200, 'description', 'description2');
