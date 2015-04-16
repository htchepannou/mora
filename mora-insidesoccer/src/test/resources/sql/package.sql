--
-- TABLES
--

-- Package
drop table if exists pkg;
create table pkg (
    pkg_id bigint not null auto_increment,
    pkg_name varchar(20),
    pkg_active boolean,
    pkg_price_monthly decimal(10, 3),
    pkg_price_yearly decimal(10, 3),
    pkg_price_biyearly decimal(10, 3),
    pkg_team_price_monthly decimal(10, 3),
    pkg_team_price_yearly decimal(10, 3),
    pkg_max_free_teams int,
    pkg_can_create_teams tinyint(1),
    pkg_can_access_site tinyint(1),
    pkg_descr mediumtext,

    primary key (pkg_id)
) Engine=innodb;


-- PackageApplication
drop table if exists pkapp;
create table pkapp (
    pkapp_id   bigint not null  auto_increment,
    pkapp_pkg_fk bigint not null,
    pkapp_app_fk bigint not null,

    primary key (pkapp_id),
    unique(pkapp_pkg_fk, pkapp_app_fk)
) Engine=innodb;


    alter table pkapp 
        add index idx_pkapp__app_fk (pkapp_app_fk), 
        add constraint idx_pkapp__app_fk 
        foreign key (pkapp_app_fk) 
        references application (app_id);

    alter table pkapp 
        add index idx_pkapp__pkg_fk (pkapp_pkg_fk), 
        add constraint idx_pkapp__pkg_fk 
        foreign key (pkapp_pkg_fk) 
        references pkg (pkg_id);

--
-- DATA
--
insert into pkg (pkg_id, pkg_name, pkg_active, pkg_price_monthly, pkg_price_biyearly, pkg_price_yearly, pkg_team_price_monthly, pkg_team_price_yearly, pkg_max_free_teams, pkg_can_create_teams, pkg_can_access_site) value
    (1,   'BootRoom',           true,  9.99,  59,   89,   1.99, 25,  1, true,  true)
,   (2,   'Coach',              false, 8.95,  null, 75,   2.99, 25,  0, true, true)
,   (3,   'Manage',             false, 4.95,  null, 45,   1.99, 25,  0, true,  true)
,   (4,   'Team',               false, 0,     null, 0,    1.99, 25,  0, true,  false)
,   (10,  'BootRoom+',          true,  9.99,  59,   89,   1.99, 25,  1, true,  true)
;


insert into pkapp (pkapp_pkg_fk, pkapp_app_fk) value

-- BOOTROOM: Dashboard, Calendar, Blog, Pages, Session, Animations, Documents, Gallery,  Mail, SMS, Profile, Account, Members, Registration, Roster
    (1, 98) 
,   (1, 109)
,   (1, 100)
,   (1, 108)
,   (1, 110)
,   (1, 101)
,   (1, 112)
,   (1, 113)
,   (1, 115)

,   (1, 107)
,   (1, 103) 
,   (1, 102)
,   (1, 99)
,   (1, 1)
,   (1, 105)
,   (1, 114)
,   (1, 120)


-- Manage: Dashboard, Calendar, Blog, Pages, Session, Animations, Documents, Gallery,  Mail, SMS, Profile, Account, Members
,   (3, 98) 
,   (3, 109)
,   (3, 100)
,   (3, 108)
,   (3, 110)
,   (3, 101)
,   (3, 112)
,   (3, 113)
,   (3, 115)

,   (3, 107)
,   (3, 103) 
,   (3, 99)
,   (3, 1)
,   (3, 105)


-- Team: Dashboard, Calendar, Blog, Pages, Session, Animations, Documents, Gallery,  Mail, SMS, Profile, Account, Members
,   (4, 98) 
,   (4, 109)
,   (4, 100)
,   (4, 108)
,   (4, 110)
,   (4, 101)
,   (4, 112)
,   (4, 113)
,   (4, 115)

,   (4, 107)
,   (4, 103) 
,   (4, 99)
,   (4, 1)
,   (4, 105)


-- BOOTROOM+: Dashboard, Calendar, Blog, Pages, Session, Animations, Documents, Gallery,  Mail, SMS, Profile, Account, Members, Registration, iPLAY
,   (10, 98) 
,   (10, 109)
,   (10, 100)
,   (10, 108)
,   (10, 110)
,   (10, 101)
,   (10, 112)
,   (10, 113)
,   (10, 115)

,   (10, 107)
,   (10, 103) 
,   (10, 102)
,   (10, 99)
,   (10, 1)
,   (10, 105)
,   (10, 114)
,   (10, 120)
;
