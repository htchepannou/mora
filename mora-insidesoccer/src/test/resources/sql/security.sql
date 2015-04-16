--
-- TABLES
--
drop table if exists role;
create table role (
    role_id bigint not null,
    role_name varchar(20),
    role_access_all_teams tinyint( 1 ) null default '0',

    primary key (role_id)
) Engine=innodb;



drop table if exists permission;
create table permission (
    perm_id bigint not null auto_increment,
    perm_value varchar(50),
    perm_app_fk bigint not null,
    perm_role_fk bigint not null,

    primary key (perm_id),
    unique (perm_role_fk, perm_app_fk, perm_value)
) Engine=innodb;

    alter table permission
        add index idx_perm__role_fk (perm_role_fk), 
        add constraint idx_perm__role_fk 
        foreign key (perm_role_fk) 
        references role (role_id);

    alter table permission
        add index idx_perm__app_fk (perm_app_fk), 
        add constraint idx_perm__app_fk 
        foreign key (perm_app_fk) 
        references application (app_id);


--
-- DATA
--
insert into role (role_id, role_name, role_access_all_teams) values
    (1,  'admin',               true)
,   (2,  'coach',               false)
,   (3,  'player',              false)
,   (6,  'member',              false)
,   (7,  'technical_director',  true)
,   (8,  'team_manager',        false)
,   (9,  'treasurer',           false)
,   (11, 'head_coach',          false)
,   (12, 'volunteer_coach',     false)
;


insert into permission(perm_app_fk, perm_role_fk, perm_value) values

-- DASHBOARD
   (98, 1,  'access')
,  (98, 2,  'access')
,  (98, 3,  'access')
,  (98, 6,  'access')
,  (98, 7,  'access')
,  (98, 8,  'access')
,  (98, 9,  'access')
,  (98, 11, 'access')
,  (98, 12, 'access')


-- PROFILE PERMISSION
,  (99, 1,  'edit')
,  (99, 1,  'add_team')
,  (99, 1,  'delete_team')
,  (99, 1,  'access')

,  (99, 2,  'edit')
,  (99, 2,  'access')

,  (99, 3,  'access')

,  (99, 6,  'access')

,  (99, 7,  'edit')
,  (99, 7,  'add_team')
,  (99, 7,  'delete_team')
,  (99, 7,  'access')

,  (99, 8,  'access')
,  (99, 8,  'edit')

,  (99, 9,  'access')

,  (99, 11,  'edit')
,  (99, 11, 'access')

,  (99, 12, 'access')


-- BLOG PERMISSION
,   (100, 1,  'access')
,   (100, 1,  'create')
,   (100, 1,  'edit')
,   (100, 1,  'delete')
,   (100, 1,  'security')
,   (100, 1,  'share')
,   (100, 1,  'delete_comment')

,   (100, 2,  'access')
,   (100, 2,  'create')
,   (100, 2,  'share')

,   (100, 3,  'access')

,   (100, 6,  'access')

,   (100, 7,  'access')
,   (100, 7,  'create')
,   (100, 7,  'edit')
,   (100, 7,  'delete')
,   (100, 7,  'security')
,   (100, 7,  'share')
,   (100, 7,  'delete_comment')

,   (100, 8,  'access')
,   (100, 8,  'create')

,   (100, 9,  'access')
,   (100, 9,  'create')

,   (100, 11, 'access')
,   (100, 11, 'create')
,   (100, 11, 'share')

,   (100, 12, 'access')
,   (100, 12, 'create')


-- DOCUMENT PERMISSION
,   (101, 1,  'access')
,   (101, 1,  'create')
,   (101, 1,  'edit')
,   (101, 1,  'delete')
,   (101, 1,  'security')
,   (101, 1,  'share')
,   (101, 1,  'delete_comment')

,   (101, 2,  'access')
,   (101, 2,  'create')
,   (101, 2,  'share')

,   (101, 3,  'access')

,   (101, 6,  'access')

,   (101, 7,  'access')
,   (101, 7,  'create')
,   (101, 7,  'edit')
,   (101, 7,  'delete')
,   (101, 7,  'security')
,   (101, 7,  'share')
,   (101, 7,  'delete_comment')

,   (101, 8,  'access')
,   (101, 8,  'create')

,   (101, 9,  'access')
,   (101, 9,  'create')

,   (101, 11, 'access')
,   (101, 11, 'create')
,   (101, 11, 'share')

,   (101, 12, 'access')
,   (101, 12, 'create')



-- SMS PERMISSION
,   (102, 1,  'access')
,   (102, 1,  'send')

,   (102, 2,  'access')
,   (102, 2,  'send')

,   (102, 7,  'access')
,   (102, 7,  'send')

,   (102, 8,  'access')
,   (102, 8,  'send')

,   (102, 9,  'access')
,   (102, 9,  'send')

,   (102, 11, 'access')
,   (102, 11, 'send')



-- MAIL PERMISSION
,   (103, 1,  'access')
,   (103, 1,  'send')

,   (103, 2,  'access')
,   (103, 2,  'send')

,   (103, 7,  'access')
,   (103, 7,  'send')

,   (103, 8,  'access')
,   (103, 8,  'send')

,   (103, 9,  'access')
,   (103, 9,  'send')

,   (103, 11, 'access')
,   (103, 11, 'send')


-- MEMBER PERMISSION
,   (105, 1, 'access')
,   (105, 1, 'create')
,   (105, 1, 'edit')
,   (105, 1, 'delete')
,   (105, 1, 'add_player_to_team')

,   (105, 2, 'access')
,   (105, 2, 'create')
,   (105, 2, 'edit')
,   (105, 2, 'delete')
,   (105, 2, 'add_player_to_team')

,   (105, 3,  'access')

,   (105, 6,  'access')

,   (105, 7, 'access')
,   (105, 7, 'create')
,   (105, 7, 'edit')
,   (105, 7, 'delete')
,   (105, 7, 'add_player_to_team')

,   (105, 8,  'access')
,   (105, 8,  'create')
,   (105, 8,  'edit')
,   (105, 8,  'delete')
,   (105, 8,  'add_player_to_team')

,   (105, 9,  'access')

,   (105, 11, 'access')
,   (105, 11, 'create')
,   (105, 11, 'edit')
,   (105, 11, 'delete')
,   (105, 11, 'add_player_to_team')

,   (105, 12, 'access')


-- PAGE PERMISSION
,   (107, 1, 'access')
,   (107, 1, 'create')
,   (107, 1, 'edit')
,   (107, 1, 'delete')
,   (107, 1, 'share')
,   (107, 1, 'security')
,   (107, 1, 'delete_comment')

,   (107, 2, 'access')
,   (107, 2, 'create')
,   (107, 2, 'share')

,   (107, 3, 'access')

,   (107, 6, 'access')

,   (107, 7, 'access')
,   (107, 7, 'create')
,   (107, 7, 'edit')
,   (107, 7, 'delete')
,   (107, 7, 'share')
,   (107, 7, 'security')
,   (107, 7, 'delete_comment')

,   (107, 8, 'access')
,   (107, 8, 'create')

,   (107, 9, 'access')

,   (107, 11, 'access')
,   (107, 11, 'create')
,   (107, 11, 'share')

,   (107, 12, 'access')


-- SESSION PERMISSION
,   (108, 1, 'access')
,   (108, 1, 'create')
,   (108, 1, 'edit')
,   (108, 1, 'delete')
,   (108, 1, 'share')
,   (108, 1, 'security')
,   (108, 1, 'delete_comment')

,   (108, 2, 'access')
,   (108, 2, 'create')
,   (108, 2, 'share')

,   (108, 3, 'access')

,   (108, 6, 'access')

,   (108, 7, 'access')
,   (108, 7, 'create')
,   (108, 7, 'edit')
,   (108, 7, 'delete')
,   (108, 7, 'share')
,   (108, 7, 'security')
,   (108, 7, 'delete_comment')

,   (108, 8, 'access')
,   (108, 8, 'create')

,   (108, 11, 'access')
,   (108, 11, 'create')
,   (108, 11, 'share')

,   (108, 12, 'access')


-- CALENDAR PERMISSION
,   (109, 1, 'access')
,   (109, 1, 'create')
,   (109, 1, 'edit')
,   (109, 1, 'delete')
,   (109, 1, 'share')
,   (109, 1, 'security')
,   (109, 1, 'delete_comment')

,   (109, 2, 'access')
,   (109, 2, 'create')
,   (109, 2, 'share')

,   (109, 3, 'access')

,   (109, 6, 'access')

,   (109, 7, 'access')
,   (109, 7, 'create')
,   (109, 7, 'edit')
,   (109, 7, 'delete')
,   (109, 7, 'share')
,   (109, 7, 'security')
,   (109, 7, 'delete_comment')

,   (109, 8, 'access')
,   (109, 8, 'create')

,   (109, 9, 'access')

,   (109, 11, 'access')
,   (109, 11, 'create')
,   (109, 11, 'share')

,   (109, 12, 'access')
,   (109, 12, 'create')


-- ASB PERMISSION
,   (110, 1, 'access')
,   (110, 1, 'create')
,   (110, 1, 'edit')
,   (110, 1, 'delete')
,   (110, 1, 'share')
,   (110, 1, 'security')
,   (110, 1, 'delete_comment')
,   (110, 1, 'send_homework')

,   (110, 2, 'access')
,   (110, 2, 'create')
,   (110, 2, 'share')

,   (110, 3, 'access')

,   (110, 6, 'access')

,   (110, 7, 'access')
,   (110, 7, 'create')
,   (110, 7, 'edit')
,   (110, 7, 'delete')
,   (110, 7, 'share')
,   (110, 7, 'security')
,   (110, 7, 'delete_comment')
,   (110, 7, 'send_homework')

,   (110, 8, 'access')
,   (110, 8, 'create')

,   (110, 11, 'access')
,   (110, 11, 'create')
,   (110, 11, 'share')


-- PERFORMANCE PERMISSION
,   (112, 2, 'access')
,   (112, 2, 'create')
,   (112, 2, 'lock')
,   (112, 2, 'unlock')
,   (112, 2, 'email')

,   (112, 3, 'access')
,   (112, 3, 'edit')
,   (112, 3, 'delete')
,   (112, 6, 'access')
,   (112, 6, 'edit')
,   (112, 6, 'delete')

,   (112, 7, 'read_all')
,   (112, 7, 'access')
,   (112, 7, 'create')
,   (112, 7, 'edit')
,   (112, 7, 'delete')
,   (112, 7, 'lock')
,   (112, 7, 'unlock')


,   (112, 11, 'access')
,   (112, 11, 'create')
,   (112, 11, 'lock')
,   (112, 11, 'unlock')


-- PHOTO PERMISSION
,   (113, 1, 'access')
,   (113, 1, 'create')
,   (113, 1, 'edit')
,   (113, 1, 'delete')
,   (113, 1, 'share')
,   (113, 1, 'security')
,   (113, 1, 'delete_comment')
,   (113, 1, 'send_homework')

,   (113, 2, 'access')
,   (113, 2, 'create')
,   (113, 2, 'share')

,   (113, 3, 'access')

,   (113, 6, 'access')

,   (113, 7, 'access')
,   (113, 7, 'create')
,   (113, 7, 'edit')
,   (113, 7, 'delete')
,   (113, 7, 'share')
,   (113, 7, 'security')
,   (113, 7, 'delete_comment')
,   (113, 7, 'send_homework')

,   (113, 8, 'access')
,   (113, 8, 'create')

,   (112, 9, 'access')

,   (113, 11, 'access')
,   (113, 11, 'create')
,   (113, 11, 'share')

,   (113, 12, 'access')
,   (113, 12, 'create')


-- REGISTRATION PERMISSION
,   (114, 1,  'access')

,   (114, 2,  'access')

,   (114, 3,  'access')

,   (114, 6,  'access')

,   (114, 7,  'access')

,   (114, 9,  'access')

,   (114, 11, 'access')

,   (114, 12, 'access')

-- ROSTER
,   (115, 1,  'access')
,   (115, 1,  'create')
,   (115, 1,  'edit')
,   (115, 1,  'delete')

,   (115, 2,  'access')
,   (115, 2,  'create')
,   (115, 2,  'edit')
,   (115, 2,  'delete')

,   (115, 3,  'access')

,   (115, 6,  'access')

,   (115, 7,  'access')
,   (115, 7,  'create')
,   (115, 7,  'edit')
,   (115, 7,  'delete')

,   (115, 8,  'access')
,   (115, 8,  'create')
,   (115, 8,  'edit')
,   (115, 8,  'delete')

,   (115, 9,  'access')

,   (115, 11, 'access')
,   (115, 11, 'create')
,   (115, 11, 'edit')
,   (115, 11, 'delete')

,   (115, 12, 'access')

-- iPLAY
,   (120, 1,  'access')
,   (120, 1,  'create')
,   (120, 1,  'edit')
,   (120, 1,  'delete')
,   (120, 1,  'read_all')

,   (120, 2,  'access')
,   (120, 2,  'create')
,   (120, 2,  'edit')
,   (120, 2,  'delete')
,   (120, 2,  'read_all')

,   (120, 3,  'access')

,   (120, 6,  'access')

,   (120, 7,  'access')
,   (120, 7,  'create')
,   (120, 7,  'edit')
,   (120, 7,  'delete')
,   (120, 7,  'read_all')

,   (120, 8,  'access')
,   (120, 8,  'create')
,   (120, 8,  'edit')
,   (120, 8,  'delete')

,   (120, 9,  'access')

,   (120, 11, 'access')
,   (120, 11, 'create')
,   (120, 11, 'edit')
,   (120, 11, 'delete')
,   (120, 11,  'read_all')

,   (120, 12, 'access')

,   (130, 1, 'access')
,   (130, 2, 'access')
;