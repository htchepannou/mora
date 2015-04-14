-- ====================
-- user
-- ====================
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

-- ====================
-- password
-- ====================
create table t_password(
    id int not null primary key  auto_increment,
    user_id int not null,

    value varchar(32),
    creation_date timestamp null,
    last_update timestamp null,

    unique (user_id),
    foreign key (user_id) references t_user(id)
) engine=InnoDB;

-- ====================
-- access_token
-- ====================
create table t_access_token(
    id int not null primary key auto_increment,
    user_id int not null,

    value varchar(32) not null,
    creation_date timestamp null,
    expiry_date timestamp null,

    unique(value),
    foreign key (user_id) references t_user(id)
) engine=InnoDB;

-- ====================
-- space_type
-- ====================
create table t_space_type(
    id int not null primary key auto_increment,

    name varchar(32) not null,

    unique(name)
) engine=InnoDB;

insert into t_space_type (id, name) values(1, 'club');
insert into t_space_type (id, name) values(2, 'team');

-- ====================
-- space
-- ====================
create table t_space(
    id int not null primary key auto_increment,
    user_id int not null,
    space_type_id int not null,

    name varchar(100) not null,
    abbreviation varchar(5),
    description varchar(1024),
    logo_url varchar(255),
    website_url varchar(255),
    email varchar(255),
    address varchar(255),
    deleted bit(1),
    creation_date timestamp null,
    last_update timestamp null,

    foreign key (space_type_id) references t_space_type(id),
    foreign key (user_id) references t_user(id)
) engine=InnoDB;

-- ====================
-- role
-- ====================
create table t_role(
    id int not null primary key auto_increment,

    name varchar(32) not null,

    unique(name)
) engine=InnoDB;

insert into t_role (id, name) values(1, 'admin');
insert into t_role (id, name) values(2, 'coach');
insert into t_role (id, name) values(3, 'player');


