drop table if exists roster;
create table roster (
    roster_id bigint not null auto_increment,
    roster_first_name varchar(100),
    roster_last_name varchar(100),
    roster_jersey_number varchar(20),
    roster_league_id varchar(36),
    roster_phone varchar(20),
    roster_birth_date date,
    roster_type int,
    roster_party_fk bigint,
    roster_channel_fk bigint not null,

    primary key (roster_id)
);

    alter table roster 
        add index idx_roster__party_fk (roster_party_fk), 
        add constraint idx_roster__party_fk 
        foreign key (roster_party_fk) 
        references party (party_id);

    alter table roster 
        add index idx_roster__channel_fk (roster_channel_fk), 
        add constraint idx_roster__channel_fk 
        foreign key (roster_channel_fk) 
        references party (party_id);
