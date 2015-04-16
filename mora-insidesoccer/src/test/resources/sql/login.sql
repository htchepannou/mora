--
-- TABLES
--
drop table if exists login;
create table login (
    login_id bigint not null auto_increment,
    login_active tinyint( 1 ) null default '0',
    login_admin tinyint( 1 ) null default '0',
    login_date datetime,
    login_logout_date datetime,
    login_party_fk bigint not null,

    primary key (login_id)
) Engine=innodb;

    alter table login 
        add index idx_login__party_fk (login_party_fk), 
        add constraint idx_login__party_fk 
        foreign key (login_party_fk) 
        references party (party_id);
