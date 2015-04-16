drop table if exists kpi_sales;
create table kpi_sales (
    kpi_id bigint not null auto_increment,
    kpi_period int not null,
    kpi_date date,
    kpi_amount decimal(10,3),

    primary key (kpi_id),
    unique (kpi_period, kpi_date)
) Engine=innodb;

alter table kpi_sales add index idx_kpi_sales__date(kpi_date);
alter table kpi_sales add index idx_kpi_sales__period(kpi_period);



drop table if exists kpi_user;
create table kpi_user (
    kpi_id bigint not null auto_increment,
    kpi_period int not null,
    kpi_date date,
    kpi_actual bigint,

    primary key (kpi_id),
    unique (kpi_period, kpi_date)
) Engine=innodb;

alter table kpi_user add index idx_kpi_user__date(kpi_date);
alter table kpi_user add index idx_kpi_user__period(kpi_period);


drop table if exists kpi_subsc;
create table kpi_subsc (
    kpi_id bigint not null auto_increment,
    kpi_period int not null,
    kpi_date date,
    kpi_type int,
    kpi_actual bigint,
    kpi_cancel bigint,
    kpi_renewal bigint,
    kpi_pkg_fk bigint,
    kpi_node_fk bigint,
    kpi_actual_sales decimal(10,3),
    kpi_renewal_sales decimal(10,3),

    primary key (kpi_id),
    unique (kpi_period, kpi_type, kpi_date, kpi_pkg_fk, kpi_node_fk)
) Engine=innodb;

alter table kpi_subsc add index idx_kpi_subsc__date(kpi_date);
alter table kpi_subsc add index idx_kpi_subsc__period(kpi_period);
alter table kpi_subsc add index idx_kpi_type__period(kpi_type);




alter table kpi_subsc
    add index idx_kpi_subsc__pkg_fk (kpi_pkg_fk),
    add constraint idx_kpi_subsc__pkg_fk
    foreign key (kpi_pkg_fk)
    references pkg (pkg_id);

alter table kpi_subsc
    add index idx_kpi_subsc__node_fk (kpi_node_fk),
    add constraint idx_kpi_subsc__node_fk
    foreign key (kpi_node_fk)
    references node (node_id);



drop table if exists kpi_sms;
create table kpi_sms (
    kpi_id bigint not null auto_increment,
    kpi_period int,
    kpi_date date,
    kpi_ttl_sent bigint,
    kpi_ttl_sentSuccess bigint,
    kpi_ttl_sentFailed bigint,
    kpi_user_fk bigint,
    kpi_channel_fk bigint,

    primary key (kpi_id),
    unique (kpi_date, kpi_channel_fk)
) Engine=innodb;

alter table kpi_sms
    add index idx_kpi_sms__channel_fk (kpi_channel_fk),
    add constraint idx_kpi_sms__channel_fk
    foreign key (kpi_channel_fk)
    references party (party_id);