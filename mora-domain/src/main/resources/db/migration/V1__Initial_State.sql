create table  t_user(
    id int not null primary key,
    username varchar(20) not null,
    firstname varchar(50),
    lastname varchar(50),
    email varchar(255) not null,
    deleted bit(1),
    creation_date timestamp,
    last_update timestamp,

    unique(email),
    unique(username)
);

create table t_password(
    id int primary key,
    value varchar(16),
    user_id int not null,
    creation_date timestamp,
    last_update timestamp,

    unique (user_id),
    foreign key (user_id) references t_user(id)
)
