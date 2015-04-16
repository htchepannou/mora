drop table if exists optout;
create table optout(
    optout_id bigint not null auto_increment,
    optout_email varchar(255) not null,

    primary key (optout_id)
) Engine=innodb;

alter table optout
    add index idx_optout__email (optout_email);
