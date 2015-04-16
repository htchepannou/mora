drop table if exists comment;
create table comment (
    comment_id bigint not null auto_increment,
    comment_date timestamp,
    comment_deleted boolean,
    comment_body text,
    comment_channel_fk bigint,
    comment_user_fk bigint,
    comment_node_fk bigint,

    primary key (comment_id)
) Engine=innodb;

alter table comment
    add index idx_comment__user_fk (comment_user_fk),
    add constraint idx_comment__user_fk
    foreign key (comment_user_fk)
    references party (party_id);
alter table comment
    add index idx_comment__channel_fk (comment_channel_fk),
    add constraint idx_comment__channel_fk
    foreign key (comment_channel_fk)
    references party (party_id);
alter table comment
    add index idx_comment__node_fk (comment_node_fk),
    add constraint idx_comment__node_fk
    foreign key (comment_node_fk)
    references node (node_id);