drop table if exists sms;
create table sms (
    sms_id bigint not null auto_increment,
    sms_date date,
    sms_to varchar(15),
    sms_user_fk bigint not null,
    sms_channel_fk bigint not null,
    sms_transactionId varchar(255),
    sms_successful boolean,

    primary key (sms_id)
) Engine=innodb;

alter table sms
    add index idx_sms__user_fk (sms_user_fk),
    add constraint idx_sms__user_fk
    foreign key (sms_user_fk)
    references party (party_id);
alter table sms
    add index idx_sms__channel_fk (sms_channel_fk),
    add constraint idx_sms__channel_fk
    foreign key (sms_channel_fk)
    references party (party_id);