--
-- TABLES
--
drop table if exists ntype;
create table ntype (
    ntype_id bigint not null,
    ntype_name varchar(20),

    primary key (ntype_id)
) Engine=innodb;




drop table if exists node;
create table node (
    node_id bigint not null auto_increment,
    node_deleted tinyint( 1 ) null default '0',
    node_status int,
    node_date datetime not null,
    node_type_fk bigint not null,
    node_channel_fk bigint,
    node_owner_fk bigint not null,

    primary key (node_id)
) Engine=innodb;

    alter table node 
        add index idx_node__deleted (node_deleted);
    alter table node 
        add index idx_node__date (node_date);

    alter table node 
        add index idx_node__type_fk (node_type_fk), 
        add constraint idx_node__type_fk 
        foreign key (node_type_fk) 
        references ntype (ntype_id);

    alter table node 
        add index idx_node__channel_fk (node_channel_fk), 
        add constraint idx_node__channel_fk 
        foreign key (node_channel_fk) 
        references party (party_id);

    alter table node 
        add index idx_node__owner_fk (node_owner_fk), 
        add constraint idx_node__owner_fk 
        foreign key (node_owner_fk) 
        references party (party_id);




drop table if exists nattr;
create table nattr (
    nattr_id bigint not null auto_increment,
    nattr_name varchar(50),
    nattr_value mediumtext,
    nattr_node_fk bigint not null,

    primary key (nattr_id),
    unique (nattr_name, nattr_node_fk)
) Engine=innodb;

    alter table nattr 
        add index idx_nattr__node_fk (nattr_node_fk), 
        add constraint idx_nattr__node_fk 
        foreign key (nattr_node_fk) 
        references node (node_id);




drop table if exists npreltype;
create table npreltype (
    npreltype_id bigint not null,
    npreltype_name varchar(20),

    primary key (npreltype_id)
) Engine=innodb;



drop table if exists nprel;
create table nprel (
    nprel_id bigint not null auto_increment,
    nprel_rank bigint,
    nprel_primary tinyint( 1 ) null default '0',
    nprel_qualifier varchar(50),
    nprel_type_fk bigint not null,
    nprel_node_fk bigint not null,
    nprel_party_fk bigint not null,

    primary key (nprel_id),
    unique (nprel_type_fk, nprel_node_fk, nprel_party_fk)
) Engine=innodb;

    alter table nprel 
        add index idx_nprel__rank (nprel_rank);
    alter table nprel 
        add index idx_nprel__primary (nprel_primary);


    alter table nprel 
        add index idx_nprel__type_fk (nprel_type_fk), 
        add constraint idx_nprel__type_fk 
        foreign key (nprel_type_fk) 
        references npreltype (npreltype_id);

    alter table nprel 
        add index idx_nprel__node_fk (nprel_node_fk), 
        add constraint idx_nprel__node_fk 
        foreign key (nprel_node_fk) 
        references node (node_id);

    alter table nprel 
        add index idx_nprel__party_fk (nprel_party_fk), 
        add constraint idx_nprel__party_fk 
        foreign key (nprel_party_fk) 
        references party (party_id);




drop table if exists nreltype;
create table nreltype (
    nreltype_id bigint not null,
    nreltype_name varchar(20),

    primary key (nreltype_id)
) Engine=innodb;




drop table if exists nrel;
create table nrel (
    nrel_id bigint not null auto_increment,
    nrel_qualifier varchar(50),
    nrel_rank bigint,
    nrel_type_fk bigint not null,
    nrel_source_fk bigint not null,
    nrel_dest_fk bigint not null,

    primary key (nrel_id),
    unique (nrel_type_fk, nrel_source_fk, nrel_dest_fk)
) Engine=innodb;

    alter table nrel 
        add index idx_nrel__rank (nrel_rank);

    alter table nrel 
        add index idx_nrel__type_fk (nrel_type_fk), 
        add constraint idx_nrel__type_fk 
        foreign key (nrel_type_fk) 
        references nreltype (nreltype_id);

    alter table nrel 
        add index idx_nrel__source_fk (nrel_source_fk), 
        add constraint idx_nrel__source_fk 
        foreign key (nrel_source_fk) 
        references node (node_id);

    alter table nrel 
        add index idx_nrel__dest_fk (nrel_dest_fk), 
        add constraint idx_nrel__dest_fk 
        foreign key (nrel_dest_fk) 
        references node (node_id);




drop table if exists npkg;
create table npkg (
    npkg_id bigint not null auto_increment,
    npkg_node_fk bigint not null,
    npkg_pkg_fk bigint not null,

    primary key (npkg_id),
    unique (npkg_node_fk, npkg_pkg_fk)
) Engine=innodb;

    alter table npkg 
        add index idx_npkg__node_fk (npkg_node_fk), 
        add constraint idx_npkg__node_fk 
        foreign key (npkg_node_fk) 
        references node (node_id);

    alter table npkg 
        add index idx_npkg__pkg_fk (npkg_pkg_fk), 
        add constraint idx_npkg__pkg_fk 
        foreign key (npkg_pkg_fk) 
        references pkg (pkg_id);


drop table if exists nview;
create table nview (
    nview_id bigint not null auto_increment,
    nview_user_agent mediumtext,
    nview_date datetime,
    nview_user_fk bigint not null,
    nview_node_fk bigint not null,
    nview_channel_fk bigint not null,

    primary key (nview_id)
) Engine=innodb;

    alter table nview 
        add index idx_nview__user_fk (nview_user_fk), 
        add constraint idx_nview__user_fk 
        foreign key (nview_user_fk) 
        references party (party_id);

    alter table nview 
        add index idx_nview__channel_fk (nview_channel_fk), 
        add constraint idx_nview__channel_fk 
        foreign key (nview_channel_fk) 
        references party (party_id);

    alter table nview 
        add index idx_nview__node_fk (nview_node_fk), 
        add constraint idx_nview__node_fk 
        foreign key (nview_node_fk) 
        references node (node_id);

--
-- DATA
--
insert into ntype(ntype_id, ntype_name) values
    (1,     'blog')
,   (2,     'document')
,   (3,     'album')
,   (4,     'event')
,   (5,     'session')

,   (30,    'page')
,   (31,    'page_text')
,   (32,    'page_url')
,   (33,    'page_separator')
,   (34,    'page_file')

,   (40,    'asb')
,   (41,    'asb_folder')

,   (50,    'iplay')

,   (100,   'attachment')
,   (1000,  'library')
,   (1001,  'video')
,   (1002,  'group')
,   (1003,  'pro')
,   (1004,  'article')

,   (2000,  'folder')
;

insert into npreltype(npreltype_id, npreltype_name) values
    (1,     'blog')
,   (2,     'document')
,   (3,     'album')
,   (4,     'event')
,   (5,     'session')
,   (30,    'page')
,   (40,    'asb')
,   (50,    'iplay')
,   (1000,  'mylibrary')
,   (1001,  'rsvp')
;

insert into nreltype(nreltype_id, nreltype_name) values
    (30,    'page')
,   (31,    'page_section')
,   (41,    'asb_homework')
,   (50,    'iplay')
,   (100,   'attachment')
,   (1001,  'video')
,   (1003,  'pro')
,   (1002,  'group')
,   (1004,  'article')
,   (2000,  'folder')
,   (9000,  'cross_link')
;



