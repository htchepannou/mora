--
-- TABLES
--

-- Promotion
drop table if exists promo;
create table promo (
    promo_id bigint not null auto_increment,
    promo_code varchar(50) not null,
    promo_name varchar(50) not null,
    promo_start_date date,
    promo_end_date date,
    promo_percent decimal(10,3),
    promo_amount decimal(10,3),
    promo_billing_period varchar(10),
    promo_pkg_fk bigint,
    promo_node_fk bigint,
    
    primary key (promo_id),
    unique (promo_code)
) Engine=innodb;

    alter table promo 
        add index idx_promo__pkg_fk (promo_pkg_fk), 
        add constraint idx_promo__pkg_fk 
        foreign key (promo_pkg_fk) 
        references pkg (pkg_id);

    alter table promo 
        add index idx_promo__node_fk (promo_node_fk), 
        add constraint idx_promo__node_fk 
        foreign key (promo_node_fk) 
        references node (node_id);


-- Subscription
drop table if exists subscription;
create table subscription (
    subsc_id bigint not null auto_increment,
    subsc_date datetime not null,
    subsc_start_date date,
    subsc_expiry_date date,
    subsc_cancellation_date date,
    subsc_type int,
    subsc_status int,
    subsc_amount decimal(10,3),
    subsc_discount decimal(10,3),
    subsc_total decimal(10,3),
    subsc_method int,
    subsc_billing_period varchar(10),
    subsc_billing_frequency int,
    subsc_pp_profile_id varchar(14),
    subsc_pp_tran_id varchar(20),
    subsc_pp_cancelled tinyint(1) default 0,
    subsc_cancellation_reason mediumtext, 
    subsc_max_free_teams int default 0,
    subsc_last_payment_date datetime,
    subsc_next_payment_date datetime,
    subsc_update_cc tinyint(1) default 0,
    subsc_node_fk  bigint,
    subsc_party_fk bigint,
    subsc_pkg_fk  bigint,
    subsc_owner_fk bigint not null,
    subsc_promo_fk bigint,


    primary key (subsc_id)
) Engine=innodb;

    alter table subscription add index idx_subsc__type (subsc_type);
    alter table subscription add index idx_subsc__status (subsc_status);
    alter table subscription add index idx_subsc__date (subsc_date);
    alter table subscription add index idx_subsc__subsc_pp_profile_id (subsc_pp_profile_id);
    alter table subscription add index idx_subsc_pp_tran_id (subsc_pp_tran_id);

    alter table subscription 
        add index idx_subsc__party_fk (subsc_party_fk), 
        add constraint idx_subsc__party_fk 
        foreign key (subsc_party_fk) 
        references party (party_id);

    alter table subscription 
        add index idx_subsc__owner_fk (subsc_owner_fk), 
        add constraint idx_subsc__owner_fk 
        foreign key (subsc_owner_fk) 
        references party (party_id);

    alter table subscription 
        add index idx_subsc__pkg_fk (subsc_pkg_fk), 
        add constraint idx_subsc__pkg_fk 
        foreign key (subsc_pkg_fk) 
        references pkg (pkg_id);

    alter table subscription 
        add index idx_subsc__node_fk (subsc_node_fk), 
        add constraint idx_subsc__node_fk 
        foreign key (subsc_node_fk) 
        references node (node_id);

    alter table subscription 
        add index idx_subsc__promo_fk (subsc_promo_fk), 
        add constraint idx_subsc__promo_fk 
        foreign key (subsc_promo_fk) 
        references promo (promo_id);


-- Subscription
drop table if exists payment;
create table payment (
    payment_id bigint not null auto_increment,
    payment_date datetime,
    payment_amount decimal(10,3),
    payment_failed tinyint(1) default 0,
    payment_pp_tran_id varchar(20),
    payment_refund_reason mediumtext,
    payment_subsc_fk bigint,
    payment_promo_fk bigint,
    payment_user_fk bigint,

    primary key (payment_id)
) Engine=innodb;

    alter table payment add index idx_payment__pp_tran_id (payment_pp_tran_id);

    alter table payment 
        add index idx_payment__promo_fk (payment_promo_fk), 
        add constraint idx_payment__promo_fk 
        foreign key (payment_promo_fk) 
        references promo (promo_id);

    alter table payment 
        add index idx_payment__user_fk (payment_user_fk), 
        add constraint idx_payment__user_fk 
        foreign key (payment_user_fk) 
        references party (party_id);

    alter table payment 
        add index idx_payment__subsc_fk (payment_subsc_fk), 
        add constraint idx_payment__subsc_fk 
        foreign key (payment_subsc_fk) 
        references subscription (subsc_id);
