-- testFindByValue
insert into party(party_id, party_deleted, party_status, party_date, party_creation_date, party_type_fk) values(100, 0, 1, now(), now(), 1);
insert into login(login_id, login_active, login_admin, login_date, login_logout_date, login_party_fk) values(100, 1, 0, '2014-01-01 10:30:55', null, 100);

-- testFindByUserByExpired
insert into party(party_id, party_deleted, party_status, party_date, party_creation_date, party_type_fk) values(200, 0, 1, now(), now(), 1);
insert into login(login_id, login_active, login_admin, login_date, login_logout_date, login_party_fk) values(200, 1, 0, '2014-01-01 10:30:55', null, 200);
insert into login(login_id, login_active, login_admin, login_date, login_logout_date, login_party_fk) values(201, 1, 0, '2014-01-01 10:30:55', null, 200);
insert into login(login_id, login_active, login_admin, login_date, login_logout_date, login_party_fk) values(202, 0, 0, '2014-01-01 10:30:55', '2014-01-01 12:30:55', 200);


