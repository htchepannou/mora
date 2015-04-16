--
-- TABLES
--

-- PartyType
drop table if exists ptype;
create table ptype (
    ptype_id bigint not null,
    ptype_name varchar(20),

    primary key (ptype_id)
) Engine=innodb;


-- PartyRypeRole
drop table if exists ptrole;
create table ptrole (
    ptrole_id   bigint not null  auto_increment,
    ptrole_rank int,
    ptrole_access tinyint (1) default 0,
    ptrole_view_drills tinyint (1) default 0,
    ptrole_max_members int,
    ptrole_role_fk bigint not null,
    ptrole_type_fk bigint not null,

    primary key (ptrole_id),
    unique(ptrole_role_fk, ptrole_type_fk)
) Engine=innodb;

    alter table ptrole 
        add index idx_ptrole__type_fk (ptrole_type_fk), 
        add constraint idx_ptrole__type_fk 
        foreign key (ptrole_type_fk) 
        references ptype (ptype_id);

    alter table ptrole 
        add index idx_ptrole__role_fk (ptrole_role_fk), 
        add constraint idx_ptrole__role_fk 
        foreign key (ptrole_role_fk) 
        references role (role_id);



-- PartyRypeApplication
drop table if exists ptapp;
create table ptapp (
    ptapp_id   bigint not null  auto_increment,
    ptapp_type_fk bigint not null,
    ptapp_app_fk bigint not null,

    primary key (ptapp_id),
    unique(ptapp_type_fk, ptapp_app_fk)
) Engine=innodb;

    alter table ptapp 
        add index idx_ptapp__app_fk (ptapp_app_fk), 
        add constraint idx_ptapp__app_fk 
        foreign key (ptapp_app_fk) 
        references application (app_id);

    alter table ptapp 
        add index idx_ptapp__type_fk (ptapp_type_fk), 
        add constraint idx_ptapp__type_fk 
        foreign key (ptapp_type_fk) 
        references ptype (ptype_id);



-- Party
drop table if exists party;
create table party (
    party_id bigint not null auto_increment,
    party_deleted tinyint( 1 ) null default '0',
    party_status int,
    party_date datetime not null,
    party_creation_date datetime not null,
    party_type_fk bigint not null,
    party_owner_fk bigint,

    primary key (party_id)
) Engine=innodb;

    alter table party 
        add index idx_party__deleted (party_deleted);

    alter table party 
        add index idx_party__type_fk (party_type_fk), 
        add constraint idx_party__type_fk 
        foreign key (party_type_fk) 
        references ptype (ptype_id);

    alter table party 
        add index idx_party__owner_fk (party_owner_fk), 
        add constraint idx_party__owner_fk 
        foreign key (party_owner_fk) 
        references party (party_id);


-- PartyAttribute
drop table if exists pattr;
create table pattr (
    pattr_id bigint not null auto_increment,
    pattr_name varchar(50),
    pattr_value mediumtext,
    pattr_party_fk bigint not null,

    primary key (pattr_id),
    unique (pattr_name, pattr_party_fk)
) Engine=innodb;

    alter table pattr 
        add index idx_pattr__party_fk (pattr_party_fk), 
        add constraint idx_pattr__party_fk 
        foreign key (pattr_party_fk) 
        references party (party_id);



-- PartyRelationshipType
drop table if exists preltype;
create table preltype (
    preltype_id bigint not null,
    preltype_name varchar(20),

    primary key (preltype_id)
) Engine=innodb;



-- PartyRelationship
drop table if exists prel;
create table prel (
    prel_id bigint not null auto_increment,
    prel_rank bigint,
    prel_qualifier varchar(50),
    prel_type_fk bigint not null,
    prel_source_fk bigint not null,
    prel_dest_fk bigint not null,

    primary key (prel_id),
    unique (prel_type_fk, prel_source_fk, prel_dest_fk)
) Engine=innodb;

    alter table prel 
        add index idx_prel__qualifier (prel_qualifier); 

    alter table prel 
        add index idx_prel__type_fk (prel_type_fk), 
        add constraint idx_prel__type_fk 
        foreign key (prel_type_fk) 
        references preltype (preltype_id);

    alter table prel 
        add index idx_prel__source_fk (prel_source_fk), 
        add constraint idx_prel__source_fk 
        foreign key (prel_source_fk) 
        references party (party_id);

    alter table prel 
        add index idx_prel__dest_fk (prel_dest_fk), 
        add constraint idx_prel__dest_fk 
        foreign key (prel_dest_fk) 
        references party (party_id);


--
-- DATA
--
insert into ptype(ptype_id, ptype_name) values
    (1, 'person')
,   (2, 'family')
,   (3, 'team')
,   (4, 'club')
;


insert into ptrole(ptrole_type_fk, ptrole_role_fk, ptrole_rank, ptrole_access, ptrole_view_drills, ptrole_max_members) values
-- team: coach, player, member, head_coach
    (3, 8,  1,  false, false, 0) 
,   (3, 11, 20, true,  true,  1) 
,   (3, 2,  21, true,  false, 5) 
,   (3, 12, 22, true,  false, 3) 
,   (3, 3,  30, true,  false, 25) 
,   (3, 6,  40, true,  false, 0) 

-- Club: admin, coach, player, member, head_coach
,   (4, 1,  1,  true,  true,  10) 
,   (4, 7,  2,  true,  true,  1) 
,   (4, 9,  3,  true,  false, 0) 
,   (4, 8,  4,  true,  false, 0) 
,   (4, 11, 20, true,  false, 0) 
,   (4, 2,  21, true,  false, 0) 
,   (4, 12, 22, true,  false, 0) 
,   (4, 3,  30, false, false, 0) 
,   (4, 6,  40, false, false, 0) 
;

insert into ptapp(ptapp_type_fk, ptapp_app_fk) values

-- team: dashboard, calendar, blog, session, pages, ASB, document, gallery, members, roster, performance, registration, iplay, myreel
    (3, 98)
,   (3, 99)
,   (3, 109)
,   (3, 100)
,   (3, 108)
,   (3, 107)
,   (3, 110)
,   (3, 101)
,   (3, 113)
,   (3, 105)
,   (3, 103)
,   (3, 112)
,   (3, 114)
,   (3, 115)
,   (3, 120)
,   (3, 130)

-- club: dashboard, calendar, blog, session, pages, ASB, document, gallery, members, performance, registration, myreel
,   (4, 98)
,   (4, 99)
,   (4, 109)
,   (4, 100)
,   (4, 108)
,   (4, 107)
,   (4, 110)
,   (4, 101)
,   (4, 113)
,   (4, 105)
,   (4, 102)
,   (4, 103)
,   (4, 112)
,   (4, 114)
,   (4, 130)
;


insert into preltype(preltype_id, preltype_name) values
    (1,   'child')
,   (4,   'club')
,   (10,  'member')
,   (11,  'owner')
;