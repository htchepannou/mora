--
-- TABLES
--
drop table if exists application;
create table application (
    app_id bigint not null,
    app_name varchar(50) not null,
    app_title varchar(50),
    app_menu_order int,
    app_icon_url varchar(255),
    app_web_url varchar(255),
    app_static_url varchar(255),
    app_menu_count_url varchar(255),
    app_show_in_menu tinyint( 1 ) null default '0',
    app_mandatory tinyint( 1 ) null default '0',
    app_rank int,

    primary key (app_id),
    unique (app_name)
) Engine=innodb;


--
-- DATA
--

insert into application (app_id, app_rank, app_mandatory, app_name, app_title, app_web_url, app_static_url, app_icon_url, app_show_in_menu) values
    (1,   0,   true,  'account',        'Account',      'http://localhost:8080/is-account-web', 'http://localhost:8080/is-account-web/static', null, false)
,   (99,  0,   true,  'profile',        'Profile',      'http://localhost:8080/is-profile-web',   'http://localhost:8080/is-profile-web/static',    '/images/icon.png', false)

,   (98,  1,   true,  'dashboard',      'Dashboard',    'http://localhost:8080/is-dashboard-web', 'http://localhost:8080/is-dashboard-web/static',  '/images/icon.png', true)
,   (100, 2,   false, 'blog',           'Blog',         'http://localhost:8080/is-blog-web',      'http://localhost:8080/is-blog-web/static',       '/images/icon.png', true)
,   (109, 3,   false, 'calendar',       'Calendar',     'http://localhost:8080/is-calendar-web',  'http://localhost:8080/is-calendar-web/static',   '/images/icon.png', true)
,   (110, 4,   false, 'asb',            'Animations',   'http://localhost:8080/is-asb-web',       'http://localhost:8080/is-asb-web/static',        '/images/icon.png', true)
,   (108, 5,   false, 'session',        'Sessions',     'http://localhost:8080/is-session-web',   'http://localhost:8080/is-session-web/static',    '/images/icon.png', true)
,   (101, 7,   false, 'document',       'Documents',    'http://localhost:8080/is-document-web',  'http://localhost:8080/is-document-web/static',   '/images/icon.png', true)
,   (113, 8,   false, 'photo',          'Gallery',      'http://localhost:8080/is-photo-web',     'http://localhost:8080/is-photo-web/static',      '/images/icon.png', true)
,   (115, 9,   false, 'roster',         'Roster',       'http://localhost:8080/is-roster-web',    'http://localhost:8080/is-roster-web/static',     '/images/icon.png', true)
,   (112, 100, false, 'performance',    'Performance',  'http://localhost:8080/is-perf-web',      'http://localhost:8080/is-perf-web/static',       '/images/icon.png', true)
,   (114, 101, false, 'registration',   'Registration', 'http://localhost:8080/is-reg-web',       'http://localhost:8080/is-reg-web/static',        '/images/icon.png', true)

,   (105, 0,   true,  'members',        'Members',      'http://localhost:8080/is-member-web',    'http://localhost:8080/is-member-web/static',     '/images/icon.png', false)
,   (102, 0,   false, 'sms',            'SMS',          'http://localhost:8080/is-sms-web',       'http://localhost:8080/is-sms-web/static',        '/images/icon.png', false)
,   (103, 0,   false, 'mail',           'Message',      'http://localhost:8080/is-mail-web',      'http://localhost:8080/is-mail-web/static',       '/images/icon.png', false)
,   (107, 0,   true,  'page',           'Pages',        'http://localhost:8080/is-page-web',      'http://localhost:8080/is-page-web/static',       '/images/icon.png', false)

,   (120, 10,  false, 'iplay',          'iPlay',        'http://localhost:8080/is-iplay-web',     'http://localhost:8080/is-iplay-web/static',      '/images/icon.png', true)
,   (130, 10,  false, 'myreel',         'MyReel',       'http://is-video-web.appspot.com/login/insidesoccer', 'http://is-video-web.appspot.com/static',         '/orange-replay-100x100.png', true)
;
