
-- PaypalTX
drop table if exists pptx;
create table pptx(
    pptx_id bigint  not null auto_increment,
    pptx_date datetime,
    pptx_tran_id varchar(20),
    pptx_method varchar(50),
    pptx_amount decimal(10,3),
    pptx_profile_id varchar(14),
    pptx_approved tinyint(1) default 0,
    pptx_response_code varchar(6),
    pptx_customer varchar(100),
    pptx_request mediumtext,
    pptx_response mediumtext,
    pptx_user_fk bigint,

    primary key (pptx_id)
) Engine=innodb;

    alter table pptx add index idx_pptx__profile_id (pptx_profile_id);
    alter table pptx add index idx_pptx__tran_id (pptx_tran_id);
    alter table pptx add index idx_pptx__date (pptx_date);

    alter table pptx 
        add index idx_pptx__user_fk (pptx_user_fk), 
        add constraint idx_pptx__user_fk 
        foreign key (pptx_user_fk) 
        references party (party_id);

-- PaypalIPN
drop table if exists ppipn;
create table ppipn(
    ppipn_id bigint  not null auto_increment,
    ppipn_date datetime,
    ppipn_tran_id varchar(20),
    ppipn_type varchar(100),
    ppipn_amount decimal(10,3),
    ppipn_profile_id varchar(14),
    ppipn_params mediumtext,

    primary key (ppipn_id)
) Engine=innodb;

    alter table ppipn add index idx_ppipn__profile_id (ppipn_profile_id);
    alter table ppipn add index idx_ppipn__tran_id (ppipn_tran_id);
    alter table ppipn add index idx_ppipn__date (ppipn_date);
