create table t_user(
    id int not null primary key auto_increment,

    username varchar(20) not null,
    firstname varchar(50),
    lastname varchar(50),
    email varchar(255) not null,
    deleted bit(1),
    creation_date timestamp null default null,
    last_update timestamp null default null,

    index(email, deleted),
    index(username, deleted)
) engine=InnoDB;

create table t_password(
    id int not null primary key  auto_increment,
    user_id int not null,

    value varchar(32),
    creation_date timestamp null,
    last_update timestamp null,

    unique (user_id),
    foreign key (user_id) references t_user(id)
) engine=InnoDB;

create table t_access_token(
    id int not null primary key auto_increment,
    user_id int not null,

    value varchar(32) not null,
    creation_date timestamp null,
    expiry_date timestamp null,

    unique(value),
    foreign key (user_id) references t_user(id)
) engine=InnoDB;


create table t_space_type(
    id int not null primary key auto_increment,

    name varchar(32) not null,

    unique(name)
) engine=InnoDB;

insert into t_space_type (id, name) values(1, 'club');
insert into t_space_type (id, name) values(2, 'team');



