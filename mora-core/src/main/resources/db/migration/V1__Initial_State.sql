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


-- ====================
-- member
-- ====================
create table t_member(
    id int not null primary key auto_increment,
    space_id int not null,
    user_id int not null,
    role_id int not null,

    creation_date timestamp null,

    foreign key (space_id) references t_space(id),
    foreign key (user_id) references t_user(id),
    foreign key (role_id) references t_role(id),
    unique(space_id, user_id, role_id)
) engine=InnoDB;


-- ====================
-- media
-- ====================
create table t_media_type(
    id int not null primary key auto_increment,

    name varchar(32) not null,

    unique(name)
) engine=InnoDB;

create table t_media(
    id int not null primary key auto_increment,
    space_id int not null,
    user_id int not null,
    type_id int not null,

    title varchar(100) not null,
    description varchar(255) not null,
    content_type varchar(100),
    size int,
    oembed bit(1),
    url varchar(255),
    thumbnail_url varchar(255),
    image_url varchar(255),
    deleted bit(1),
    creation_date timestamp null,
    last_update timestamp null,

    foreign key (space_id) references t_space(id),
    foreign key (user_id) references t_user(id),
    foreign key (type_id) references t_media_type(id)
) engine=InnoDB;


-- ====================
-- attachment_type
-- ====================
create table t_attachment_type(
    id int not null primary key auto_increment,

    name varchar(32) not null,

    unique(name)
) engine=InnoDB;


-- ====================
-- attachment
-- ====================
create table t_attachment(
    id int not null primary key auto_increment,
    owner_id int not null,
    media_id int not null,
    type_id int not null,

    rank int,

    foreign key (media_id) references t_media(id),
    foreign key (type_id)  references t_attachment_type(id),
    unique(owner_id, media_id, type_id)
) engine=InnoDB;



-- ====================
-- posts
-- ====================
create table t_post(
    id int not null primary key auto_increment,
    space_id int not null,
    user_id int not null,

    title varchar(255),
    summary varchar(512),
    content text,
    deleted bit(1),
    creation_date timestamp null,
    last_update timestamp null,

    foreign key (space_id) references t_space(id),
    foreign key (user_id) references t_user(id),
    index (last_update)
) engine=InnoDB;

-- ====================
-- event_type
-- ====================
create table t_event_type(
    id int not null primary key auto_increment,

    name varchar(32) not null,

    unique(name)
) engine=InnoDB;

-- ====================
-- event
-- ====================
create table t_event(
    id int not null primary key auto_increment,
    space_id int not null,
    user_id int not null,
    type_id int not null,

    title varchar(255),
    notes text,
    start_datetime timestamp null,
    end_datetime timestamp null,
    location varchar(255),
    address varchar(255),
    url varchar(255),
    timezone varchar(50),
    require_rsvp bit(1),
    deleted bit(1),
    creation_date timestamp null,
    last_update timestamp null,

    foreign key (space_id) references t_space(id),
    foreign key (user_id) references t_user(id),
    foreign key (type_id) references t_user(id),
    index (start_datetime)
) engine=InnoDB;

