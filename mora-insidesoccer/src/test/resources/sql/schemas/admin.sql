create table sysadmin
(
    sysadmin_id bigint not null,
    sysadmin_message text default null,

    primary key (sysadmin_id)
) Engine=innodb;

-- Default admin
insert into sysadmin(sysadmin_id) values(1);