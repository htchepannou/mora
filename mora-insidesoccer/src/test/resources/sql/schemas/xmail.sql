drop table if exists xmail;
create table xmail(
    xmail_id bigint not null auto_increment,
    xmail_email varchar(255) not null,

    primary key (xmail_id)
) Engine=innodb;

alter table xmail
    add index idx_xmail__email (xmail_email);

