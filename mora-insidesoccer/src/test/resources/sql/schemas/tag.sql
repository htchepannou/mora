drop table if exists tagset;
create table tagset(
    tagset_id bigint not null auto_increment,
    tagset_name varchar(50) not null,
    tagset_size bigint, 
    tagset_rank int,

    primary key (tagset_id)
) Engine=innodb;

    alter table tagset add index idx_tagset__name (tagset_name);
    alter table tagset add index idx_tagset__rank (tagset_rank);

drop table if exists tag;
create table tag(
    tag_id bigint not null auto_increment,
    tag_name varchar(50) not null,
    tag_size bigint, 
    tag_rank int,
    tag_set_fk bigint,

    primary key (tag_id)
) Engine=innodb;

    alter table tag add index idx_tag__name (tag_name);
    alter table tag add index idx_tag__rank (tag_rank);

    alter table tag 
        add index idx_tag__set_fk (tag_set_fk), 
        add constraint idx_tag__set_fk 
        foreign key (tag_set_fk) 
        references tagset (tagset_id);



drop table if exists ntag;
create table ntag (
    ntag_id bigint not null auto_increment,
    ntag_node_fk bigint not null,
    ntag_tag_fk bigint not null,

    primary key (ntag_id),
    unique (ntag_node_fk, ntag_tag_fk)
) Engine=innodb;

    alter table ntag 
        add index idx_ntag__node_fk (ntag_node_fk), 
        add constraint idx_ntag__node_fk 
        foreign key (ntag_node_fk) 
        references node (node_id);

    alter table ntag 
        add index idx_ntag__tag_fk (ntag_tag_fk), 
        add constraint idx_ntag__tag_fk 
        foreign key (ntag_tag_fk) 
        references tag (tag_id);



drop table if exists ntagset;
create table ntagset (
    ntagset_id bigint not null auto_increment,
    ntagset_node_fk bigint not null,
    ntagset_set_fk bigint not null,

    primary key (ntagset_id),
    unique (ntagset_node_fk, ntagset_set_fk)
) Engine=innodb;

    alter table ntagset 
        add index idx_ntagset__node_fk (ntagset_node_fk), 
        add constraint idx_ntagset__node_fk 
        foreign key (ntagset_node_fk) 
        references node (node_id);

    alter table ntagset 
        add index idx_ntagset__set_fk (ntagset_set_fk), 
        add constraint idx_ntagset__set_fk 
        foreign key (ntagset_set_fk) 
        references tagset (tagset_id);
