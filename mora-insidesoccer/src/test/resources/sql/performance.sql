--
-- SCHEMAS
--
-- ReportPartyType
drop table if exists rptpreltype;
create table rptpreltype (
    rptpreltype_id bigint not null,
    rptpreltype_name varchar(20),

    primary key (rptpreltype_id)
) Engine=innodb;


-- ReportPackage
drop table if exists rptpkg;
create table rptpkg (
    rptpkg_id bigint not null,
    rptpkg_name varchar(20),

    primary key (rptpkg_id)
) Engine=innodb;



-- ReportGroup
drop table if exists rptgrp;
create table rptgrp (
    rptgrp_id bigint not null,
    rptgrp_name varchar(50),
    rptgrp_rank int,
    rptgrp_pkg_fk bigint not null,
    rptgrp_graph_url varchar(255),

    primary key (rptgrp_id)
) Engine=innodb;

    alter table rptgrp 
        add index idx_rptgrp__pkg_fk (rptgrp_pkg_fk), 
        add constraint idx_rptgrp__pkg_fk 
        foreign key (rptgrp_pkg_fk) 
        references rptpkg (rptpkg_id);


-- rpttype
drop table if exists rpttype;
create table rpttype (
    rpttype_id bigint not null,
    rpttype_name varchar(50),
    rpttype_rank varchar(50),
    rpttype_url varchar(255),
    rpttype_color varchar(6),
    rpttype_deleted tinyint(1) default 0,
    rpttype_kind int,
    rpttype_grp_fk bigint not null,

    primary key (rpttype_id)
) Engine=innodb;

    alter table rpttype 
        add index idx_rpttype__grp_fk (rpttype_grp_fk), 
        add constraint idx_rpttype__grp_fk 
        foreign key (rpttype_grp_fk) 
        references rptgrp (rptgrp_id);



-- Report
drop table if exists rpt;
create table rpt (
    rpt_id bigint not null auto_increment,
    rpt_date date,
    rpt_deleted tinyint( 1 ) null default '0',
    rpt_locked tinyint( 1 ) null default '0',
    rpt_creation_date timestamp,
    rpt_modif_date timestamp,
    rpt_type_fk bigint not null,
    rpt_channel_fk bigint not null,
    rpt_player_fk bigint,
    rpt_owner_fk bigint,

    primary key (rpt_id)
) Engine=innodb;

    alter table rpt 
        add index idx_rpt__deleted (rpt_deleted);

    alter table rpt 
        add index idx_rpt__type_fk (rpt_type_fk),
        add constraint idx_rpt__type_fk 
        foreign key (rpt_type_fk) 
        references rpttype (rpttype_id);

    alter table rpt 
        add index idx_rpt__channel_fk (rpt_channel_fk),
        add constraint idx_rpt__channel_fk 
        foreign key (rpt_channel_fk) 
        references party (party_id);

    alter table rpt 
        add index idx_rpt__player_fk (rpt_player_fk),
        add constraint idx_rpt__player_fk 
        foreign key (rpt_player_fk) 
        references party (party_id);

    alter table rpt 
        add index idx_rpt__owner_fk (rpt_owner_fk),
        add constraint idx_rpt__owner_fk 
        foreign key (rpt_owner_fk) 
        references party (party_id);


-- Report
drop table if exists rptprel;
create table rptprel (
    rptprel_id bigint not null auto_increment,
    rptprel_rank bigint,
    rptprel_party_fk bigint not null,
    rptprel_rpt_fk bigint not null,
    rptprel_type_fk bigint not null,

    primary key (rptprel_id)
) Engine=innodb;

    alter table rptprel 
        add index idx_rptprel__party_fk (rptprel_party_fk),
        add constraint idx_rptprel__party_fk 
        foreign key (rptprel_party_fk) 
        references party (party_id);

    alter table rptprel 
        add index idx_rptprel__type_fk (rptprel_type_fk),
        add constraint idx_rpttype__type_fk 
        foreign key (rptprel_type_fk) 
        references rptpreltype (rptpreltype_id);

    alter table rptprel 
        add index idx_rpttype__rpt_fk (rptprel_rpt_fk),
        add constraint idx_rptprel__rpt_fk 
        foreign key (rptprel_rpt_fk) 
        references rpt (rpt_id);


-- rptcelltype
drop table if exists rptcelltype;
create table rptcelltype (
    rptcelltype_id bigint not null auto_increment,
    rptcelltype_name varchar(50) not null unique,
    rptcelltype_choices mediumtext,
    rptcelltype_data_type integer,
    rptcelltype_default_value mediumtext,
    rptcelltype_format varchar(50),

    primary key (rptcelltype_id)
) Engine=innodb;

    alter table rptcelltype add index idx_rptcelltype__name (rptcelltype_name);


-- ReportCell
drop table if exists rptcell;
create table rptcell (
    rptcell_id bigint not null auto_increment,
    rptcell_index integer,
    rptcell_value mediumtext,
    rptcell_person_fk bigint,
    rptcell_rpt_fk bigint not null,
    rptcell_type_fk bigint not null,
    primary key (rptcell_id)
) Engine=innodb;

    alter table rptcell
        add index idx_rptcell__person_fk (rptcell_person_fk),
        add constraint idx_rptcell__person_fk
        foreign key (rptcell_person_fk) 
        references party (party_id);

    alter table rptcell
        add index idx_rptcell__rpt_fk (rptcell_rpt_fk),
        add constraint idx_rptcell__rpt_fk
        foreign key (rptcell_rpt_fk) 
        references rpt (rpt_id);

    alter table rptcell
        add index idx_rptcell__type_fk (rptcell_type_fk),
        add constraint idx_rptcell__type_fk
        foreign key (rptcell_type_fk) 
        references rptcelltype (rptcelltype_id);



--
-- DATA
--
-- ReportPartyType
insert into rptpreltype(rptpreltype_id, rptpreltype_name) values (1, 'player');

-- ReportPackage
insert into rptpkg (rptpkg_id, rptpkg_name) VALUES
(1, 'Default'),
(2, 'Chelsea');

-- ReportGroup
insert into rptgrp (rptgrp_id, rptgrp_name, rptgrp_rank, rptgrp_pkg_fk, rptgrp_graph_url) VALUES
(100, 'Coaches Log', 1, 1,  'is/coachGraph'),
(101, 'Players Log', 2, 1,  null),
(103, 'Player Evaluation', 3, 1,  null),
(104, 'Coach Evaluation', 4, 1,  null),


(200, 'Coaches Planner',                1, 2, '/chelsea/coachGraph'),
(201, 'Performance Education',          2, 2, null),
(202, 'Individual Development Plan',    3, 2, null),
(203, 'Player Evaluation Report',       4, 2, null)
;


-- ReportType
insert into rpttype (rpttype_id, rpttype_name, rpttype_rank, rpttype_url, rpttype_color, rpttype_kind, rpttype_deleted, rpttype_grp_fk) values
(1,   'Practice Report',                1,  '/is/practice',                 '1BC6E0',    0,  false, 100),
(2,   'Match Report',                   2,  '/is/match',                    'E0DD1B',    0,  false, 100),
(4,   'Planner',                        3,  '/is/planner',                   null,       0,  false, 100),
(10,  'Result Summary Report',          4,  '/is/resultSummary',             null,       0,  false, 100),
(7,   'Player Performance Summary',     5,  '/is/playerPerformanceSummary',  null,       0,  false, 100),
(8,   'Tournament Report',              10, '/is/tournament',                null,       0,  false, 100),
(9,   'Tournament Summary',             11, '/is/tournamentSummary',         null,       0,  false, 100),

(20,  'Coach Profile',                  20, '/is/coachProfile',              null,       0,  false, 100),

(21,  'Coach Session Evaluation',       21, '/is/coachSessionEval',          null,       0,  false, 104),
(22,  'Coach Match Evaluation',         22, '/is/coachMatchEval',            null,       0,  false, 104),
(23,  'Coach Periodic Evaluation',      24, '/is/coachPeriodicEval',         null,       0,  false, 104),
(24,  'Coach Annual Evaluation',        25, '/is/coachAnnualEval',           null,       0,  false, 104),

(3,   'Player Report',                   3, '/is/player',                    '1BE035',   1,  false, 101),

(30,  'Player Profile',                  30, '/is/playerProfile',            null,       1,  false, 103),
(31,  'Player Self Evaluation-Daily',    31, '/is/playerSelfEvalD',          null,       1,  false, 103),
(32,  'Goal Setting',                    32, '/is/playerGoal',               null,       1,  false, 103)
;

-- REPORT CELL TYPE
insert into rptcelltype(rptcelltype_id, rptcelltype_name, rptcelltype_data_type, rptcelltype_choices, rptcelltype_format) values(100, 'ReportPositive', 0, null, null);
insert into rptcelltype(rptcelltype_id, rptcelltype_name, rptcelltype_data_type, rptcelltype_choices, rptcelltype_format) values(101, 'ReportChallenge', 0, null, null);
insert into rptcelltype(rptcelltype_id, rptcelltype_name, rptcelltype_data_type, rptcelltype_choices, rptcelltype_format) values(102, 'ReportIndividualWorkedWith', 0, null, null);
insert into rptcelltype(rptcelltype_id, rptcelltype_name, rptcelltype_data_type, rptcelltype_choices, rptcelltype_format) values(103, 'ReportPositionSpecificWork', 0, null, null);
insert into rptcelltype(rptcelltype_id, rptcelltype_name, rptcelltype_data_type, rptcelltype_choices, rptcelltype_format) values(104, 'ReportUseGK', 0, null, null);
insert into rptcelltype(rptcelltype_id, rptcelltype_name, rptcelltype_data_type, rptcelltype_choices, rptcelltype_format) values(105, 'ReportAreaForImprovement', 0, null, null);
insert into rptcelltype(rptcelltype_id, rptcelltype_name, rptcelltype_data_type, rptcelltype_choices, rptcelltype_format) values(106, 'ReportKeyCovered1', 1, null, null);
insert into rptcelltype(rptcelltype_id, rptcelltype_name, rptcelltype_data_type, rptcelltype_choices, rptcelltype_format) values(107, 'ReportKeyCovered2', 1, null, null);
insert into rptcelltype(rptcelltype_id, rptcelltype_name, rptcelltype_data_type, rptcelltype_choices, rptcelltype_format) values(108, 'ReportKeyCovered3', 1, null, null);
insert into rptcelltype(rptcelltype_id, rptcelltype_name, rptcelltype_data_type, rptcelltype_choices, rptcelltype_format) values(109, 'ReportKeyCovered4', 1, null, null);
insert into rptcelltype(rptcelltype_id, rptcelltype_name, rptcelltype_data_type, rptcelltype_choices, rptcelltype_format) values(110, 'ReportKeyCovered5', 1, null, null);
insert into rptcelltype(rptcelltype_id, rptcelltype_name, rptcelltype_data_type, rptcelltype_choices, rptcelltype_format) values(111, 'ReportPlannedMinute', 3, null, null);
insert into rptcelltype(rptcelltype_id, rptcelltype_name, rptcelltype_data_type, rptcelltype_choices, rptcelltype_format) values(112, 'ReportActualMinute', 3, null, null);
insert into rptcelltype(rptcelltype_id, rptcelltype_name, rptcelltype_data_type, rptcelltype_choices, rptcelltype_format) values(113, 'ReportCommentDrill1', 0, null, null);
insert into rptcelltype(rptcelltype_id, rptcelltype_name, rptcelltype_data_type, rptcelltype_choices, rptcelltype_format) values(114, 'ReportCommentDrill2', 0, null, null);
insert into rptcelltype(rptcelltype_id, rptcelltype_name, rptcelltype_data_type, rptcelltype_choices, rptcelltype_format) values(115, 'ReportCommentDrill3', 0, null, null);
insert into rptcelltype(rptcelltype_id, rptcelltype_name, rptcelltype_data_type, rptcelltype_choices, rptcelltype_format) values(116, 'ReportCommentDrill4', 0, null, null);
insert into rptcelltype(rptcelltype_id, rptcelltype_name, rptcelltype_data_type, rptcelltype_choices, rptcelltype_format) values(117, 'ReportCommentDrill5', 0, null, null);
insert into rptcelltype(rptcelltype_id, rptcelltype_name, rptcelltype_data_type, rptcelltype_choices, rptcelltype_format) values(118, 'ReportOpposition', 1, null, null);
insert into rptcelltype(rptcelltype_id, rptcelltype_name, rptcelltype_data_type, rptcelltype_choices, rptcelltype_format) values(119, 'ReportCondition', 1, null, null);
insert into rptcelltype(rptcelltype_id, rptcelltype_name, rptcelltype_data_type, rptcelltype_choices, rptcelltype_format) values(120, 'ReportLengthOfGame', 3, null, null);
insert into rptcelltype(rptcelltype_id, rptcelltype_name, rptcelltype_data_type, rptcelltype_choices, rptcelltype_format) values(121, 'ReportMinute', 3, null, null);
insert into rptcelltype(rptcelltype_id, rptcelltype_name, rptcelltype_data_type, rptcelltype_choices, rptcelltype_format) values(122, 'ReportResult', 1, null, null);
insert into rptcelltype(rptcelltype_id, rptcelltype_name, rptcelltype_data_type, rptcelltype_choices, rptcelltype_format) values(123, 'ReportOpponent', 1, null, null);
insert into rptcelltype(rptcelltype_id, rptcelltype_name, rptcelltype_data_type, rptcelltype_choices, rptcelltype_format) values(124, 'ReportFinalScore', 1, null, null);
insert into rptcelltype(rptcelltype_id, rptcelltype_name, rptcelltype_data_type, rptcelltype_choices, rptcelltype_format) values(125, 'ReportTeamPerformance1', 3, '1,2,2,3,4,5,6,7,8,9,10', null);
insert into rptcelltype(rptcelltype_id, rptcelltype_name, rptcelltype_data_type, rptcelltype_choices, rptcelltype_format) values(126, 'ReportTeamPerformance2', 3, '1,2,2,3,4,5,6,7,8,9,10', null);
insert into rptcelltype(rptcelltype_id, rptcelltype_name, rptcelltype_data_type, rptcelltype_choices, rptcelltype_format) values(127, 'ReportTeamPerformanceOverall', 3, '1,2,2,3,4,5,6,7,8,9,10', null);
insert into rptcelltype(rptcelltype_id, rptcelltype_name, rptcelltype_data_type, rptcelltype_choices, rptcelltype_format) values(128, 'ReportDrillName', 1, null, null);
insert into rptcelltype(rptcelltype_id, rptcelltype_name, rptcelltype_data_type, rptcelltype_choices, rptcelltype_format) values(129, 'ReportNote', 0, null, null);
insert into rptcelltype(rptcelltype_id, rptcelltype_name, rptcelltype_data_type, rptcelltype_choices, rptcelltype_format) values(130, 'ReportFormation', 1, null, null);
insert into rptcelltype(rptcelltype_id, rptcelltype_name, rptcelltype_data_type, rptcelltype_choices, rptcelltype_format) values(131, 'ReportSessionTitle', 1, null, null);
insert into rptcelltype(rptcelltype_id, rptcelltype_name, rptcelltype_data_type, rptcelltype_choices, rptcelltype_format) values(132, 'ReportSessionURL', 7, null, null);
insert into rptcelltype(rptcelltype_id, rptcelltype_name, rptcelltype_data_type, rptcelltype_choices, rptcelltype_format) values(133, 'ReportCommentDrill', 0, null, null);
insert into rptcelltype(rptcelltype_id, rptcelltype_name, rptcelltype_data_type, rptcelltype_choices, rptcelltype_format) values(134, 'ReportKeyCovered', 1, null, null);
insert into rptcelltype(rptcelltype_id, rptcelltype_name, rptcelltype_data_type, rptcelltype_choices, rptcelltype_format) values(135, 'ReportCategory', 8, null, null);
insert into rptcelltype(rptcelltype_id, rptcelltype_name, rptcelltype_data_type, rptcelltype_choices, rptcelltype_format) values(136, 'ReportExercise', 1, null, null);
insert into rptcelltype(rptcelltype_id, rptcelltype_name, rptcelltype_data_type, rptcelltype_choices, rptcelltype_format) values(137, 'ReportTrainingFocus', 1, "Strength,Resistance,Speed,Reactions,2nd Day Recovery,Off", null);
insert into rptcelltype(rptcelltype_id, rptcelltype_name, rptcelltype_data_type, rptcelltype_choices, rptcelltype_format) values(138, 'ReportPhilosophy', 5, null, null);
insert into rptcelltype(rptcelltype_id, rptcelltype_name, rptcelltype_data_type, rptcelltype_choices, rptcelltype_format) values(139, 'ReportSpecificPhilosophy', 6, null, null);
insert into rptcelltype(rptcelltype_id, rptcelltype_name, rptcelltype_data_type, rptcelltype_choices, rptcelltype_format) values(145, 'ReportDateFrom', 9, null, null);
insert into rptcelltype(rptcelltype_id, rptcelltype_name, rptcelltype_data_type, rptcelltype_choices, rptcelltype_format) values(146, 'ReportDateTo', 9, null, null);
insert into rptcelltype(rptcelltype_id, rptcelltype_name, rptcelltype_data_type, rptcelltype_choices, rptcelltype_format) values(147, 'ReportCoach', 1, null, null);
insert into rptcelltype(rptcelltype_id, rptcelltype_name, rptcelltype_data_type, rptcelltype_choices, rptcelltype_format) values(150, 'ReportDescription', 1, 'Practice, Game, Tournament, Event', null);
insert into rptcelltype(rptcelltype_id, rptcelltype_name, rptcelltype_data_type, rptcelltype_choices, rptcelltype_format) values(151, 'ReportDuration', 3, null, null);
insert into rptcelltype(rptcelltype_id, rptcelltype_name, rptcelltype_data_type, rptcelltype_choices, rptcelltype_format) values(152, 'ReportSubstitue', 2, null, null);
insert into rptcelltype(rptcelltype_id, rptcelltype_name, rptcelltype_data_type, rptcelltype_choices, rptcelltype_format) values(153, 'ReportTotal', 3, null, null);
insert into rptcelltype(rptcelltype_id, rptcelltype_name, rptcelltype_data_type, rptcelltype_choices, rptcelltype_format)values(161, 'ReportDestination', 1, 'Home, Away', null);
insert into rptcelltype(rptcelltype_id, rptcelltype_name, rptcelltype_data_type, rptcelltype_choices, rptcelltype_format, rptcelltype_default_value)values(162, 'ReportTeam', 1, null, null, 'NYSC');
insert into rptcelltype(rptcelltype_id, rptcelltype_name, rptcelltype_data_type, rptcelltype_choices, rptcelltype_format)values(163, 'ReportScore', 3, null, null);
insert into rptcelltype(rptcelltype_id, rptcelltype_name, rptcelltype_data_type, rptcelltype_choices, rptcelltype_format) values(164, 'ReportSessionLength', 3, null, null);
insert into rptcelltype(rptcelltype_id, rptcelltype_name, rptcelltype_data_type, rptcelltype_choices, rptcelltype_format) values(165, 'ReportSessionObjective', 0, null, null);
insert into rptcelltype(rptcelltype_id, rptcelltype_name, rptcelltype_data_type, rptcelltype_choices, rptcelltype_format)values(166, 'ReportDate', 9, null, null);
insert into rptcelltype(rptcelltype_id, rptcelltype_name, rptcelltype_data_type, rptcelltype_choices, rptcelltype_format)values(167, 'ReportReimbursementTeam', 3, null, null);
insert into rptcelltype(rptcelltype_id, rptcelltype_name, rptcelltype_data_type, rptcelltype_choices, rptcelltype_format)values(168, 'ReportReimbursementType', 1, 'Referee fees, Equipment, Hotel Expense, Meals, Tournament Fee, Transportation', null);
insert into rptcelltype(rptcelltype_id, rptcelltype_name, rptcelltype_data_type, rptcelltype_choices, rptcelltype_format)values(169, 'ReportLocationOpponent', 1, null, null);
insert into rptcelltype(rptcelltype_id, rptcelltype_name, rptcelltype_data_type, rptcelltype_choices, rptcelltype_format)values(170, 'ReportAmount', 3, null, null);
insert into rptcelltype(rptcelltype_id, rptcelltype_name, rptcelltype_data_type, rptcelltype_choices, rptcelltype_format) values(171, 'ReportFirstName', 1, null, null);
insert into rptcelltype(rptcelltype_id, rptcelltype_name, rptcelltype_data_type, rptcelltype_choices, rptcelltype_format) values(172, 'ReportLastName', 1, null, null);
insert into rptcelltype(rptcelltype_id, rptcelltype_name, rptcelltype_data_type, rptcelltype_choices, rptcelltype_format) values(173, 'ReportAgeGroup', 1, 'U6, U7, U8, U9, U10, U11, U12, U13, U14, U15, U16, U17, U18', null);
insert into rptcelltype(rptcelltype_id, rptcelltype_name, rptcelltype_data_type, rptcelltype_choices, rptcelltype_format) values(174, 'ReportDateOfBirth', 9, null, null);
insert into rptcelltype(rptcelltype_id, rptcelltype_name, rptcelltype_data_type, rptcelltype_choices, rptcelltype_format) values(175, 'ReportHeight', 4, null, '##.##');
insert into rptcelltype(rptcelltype_id, rptcelltype_name, rptcelltype_data_type, rptcelltype_choices, rptcelltype_format) values(176, 'Reportweight', 4, null, '##.##');
insert into rptcelltype(rptcelltype_id, rptcelltype_name, rptcelltype_data_type, rptcelltype_choices, rptcelltype_format) values(177, 'ReportBuild', 1, 'Short, Slight, Stocky, Tall, Average for age', null);
insert into rptcelltype(rptcelltype_id, rptcelltype_name, rptcelltype_data_type, rptcelltype_choices, rptcelltype_format) values(178, 'ReportClub', 1, null, null);
insert into rptcelltype(rptcelltype_id, rptcelltype_name, rptcelltype_data_type, rptcelltype_choices, rptcelltype_format) values(179, 'ReportStrength', 0, null, null);
insert into rptcelltype(rptcelltype_id, rptcelltype_name, rptcelltype_data_type, rptcelltype_choices, rptcelltype_format) values(180, 'ReportRating', 3, '1, 2, 3, 4, 5, 6, 7, 8, 9, 10', null);
insert into rptcelltype(rptcelltype_id, rptcelltype_name, rptcelltype_data_type, rptcelltype_choices, rptcelltype_format) values(181, 'ReportOverallAssessment', 1, 'Impact Player- Must Have, Strong player with Potential, Given Time may be a good team Player, Not suitable', null);
insert into rptcelltype(rptcelltype_id, rptcelltype_name, rptcelltype_data_type, rptcelltype_choices, rptcelltype_format) values(182, 'ReportNextStep', 1, 'Invite for trial, Offer position, Assess again at later date', null);
insert into rptcelltype(rptcelltype_id, rptcelltype_name, rptcelltype_data_type, rptcelltype_choices, rptcelltype_format) values(183, 'ReportPositionPlayed', 1, 'GK, RFB, LFB, CD, RM, HM, AM, LM, RW, LW, ST', null);
insert into rptcelltype(rptcelltype_id, rptcelltype_name, rptcelltype_data_type, rptcelltype_choices, rptcelltype_format) values(184, 'ReportAge', 3, null, null);
insert into rptcelltype(rptcelltype_id, rptcelltype_name, rptcelltype_data_type, rptcelltype_choices, rptcelltype_format) values(185, 'ReportOverallPhysical', 1, null, null);
insert into rptcelltype(rptcelltype_id, rptcelltype_name, rptcelltype_data_type, rptcelltype_choices, rptcelltype_format) values(186, 'ReportIndividualReport', 2, null, null);
insert into rptcelltype(rptcelltype_id, rptcelltype_name, rptcelltype_data_type, rptcelltype_choices, rptcelltype_format) values(187, 'ReportGuardianName', 1, null, null);
insert into rptcelltype(rptcelltype_id, rptcelltype_name, rptcelltype_data_type, rptcelltype_choices, rptcelltype_format) values(188, 'ReportEmail', 1, null, null);
insert into rptcelltype(rptcelltype_id, rptcelltype_name, rptcelltype_data_type, rptcelltype_choices, rptcelltype_format) values(189, 'ReportTelephone', 1, null, null);
insert into rptcelltype(rptcelltype_id, rptcelltype_name, rptcelltype_data_type, rptcelltype_choices, rptcelltype_format) values(190, 'ReportDominantFoot', 1, 'Left, Right', null);
insert into rptcelltype(rptcelltype_id, rptcelltype_name, rptcelltype_data_type, rptcelltype_choices, rptcelltype_format) values(191, 'ReportTeamRating', 3, '1, 2, 3, 4, 5, 6, 7, 8, 9, 10', null);
insert into rptcelltype(rptcelltype_id, rptcelltype_name, rptcelltype_data_type, rptcelltype_choices, rptcelltype_format) values(195, 'ReportGameType', 1, 'GROUP, SEMI, FINAL', null);
insert into rptcelltype(rptcelltype_id, rptcelltype_name, rptcelltype_data_type, rptcelltype_choices, rptcelltype_format, rptcelltype_default_value)values(196, 'ReportTeamOpponent', 1, null, null, null);



insert into rptcelltype(rptcelltype_id, rptcelltype_name, rptcelltype_data_type, rptcelltype_choices, rptcelltype_format) values(500, 'PlayerPositionPlayed11', 1, 'GK, RCB, RB, LCB, LB, RMD, LDM, RAM, CT, ST', null);
insert into rptcelltype(rptcelltype_id, rptcelltype_name, rptcelltype_data_type, rptcelltype_choices, rptcelltype_format) values(501, 'PlayerPositionPlayed12', 1, 'GK, RCB, RB, LCB, LB, RMD, LDM, RAM, CT, ST', null);
insert into rptcelltype(rptcelltype_id, rptcelltype_name, rptcelltype_data_type, rptcelltype_choices, rptcelltype_format) values(502, 'PlayerPositionPlayed13', 1, 'GK, RCB, RB, LCB, LB, RMD, LDM, RAM, CT, ST', null);
insert into rptcelltype(rptcelltype_id, rptcelltype_name, rptcelltype_data_type, rptcelltype_choices, rptcelltype_format) values(503, 'PlayerPositionPlayed21', 1, 'GK, RCB, RB, LCB, LB, RMD, LDM, RAM, CT, ST', null);
insert into rptcelltype(rptcelltype_id, rptcelltype_name, rptcelltype_data_type, rptcelltype_choices, rptcelltype_format) values(504, 'PlayerPositionPlayed22', 1, 'GK, RCB, RB, LCB, LB, RMD, LDM, RAM, CT, ST', null);
insert into rptcelltype(rptcelltype_id, rptcelltype_name, rptcelltype_data_type, rptcelltype_choices, rptcelltype_format) values(505, 'PlayerPositionPlayed23', 1, 'GK, RCB, RB, LCB, LB, RMD, LDM, RAM, CT, ST', null);
insert into rptcelltype(rptcelltype_id, rptcelltype_name, rptcelltype_data_type, rptcelltype_choices, rptcelltype_format, rptcelltype_default_value) values(506, 'PlayerAttendance', 2, null, null, 'true');
insert into rptcelltype(rptcelltype_id, rptcelltype_name, rptcelltype_data_type, rptcelltype_choices, rptcelltype_format) values(507, 'PlayerAreaForImprovement', 0, null, null);
insert into rptcelltype(rptcelltype_id, rptcelltype_name, rptcelltype_data_type, rptcelltype_choices, rptcelltype_format) values(508, 'PlayerPositive', 0, null, null);
insert into rptcelltype(rptcelltype_id, rptcelltype_name, rptcelltype_data_type, rptcelltype_choices, rptcelltype_format) values(509, 'PlayerRating', 3, '1,2,2,3,4,5,6,7,8,9,10', null);
insert into rptcelltype(rptcelltype_id, rptcelltype_name, rptcelltype_data_type, rptcelltype_choices, rptcelltype_format) values(510, 'PlayerMinute', 3, null, null);
insert into rptcelltype(rptcelltype_id, rptcelltype_name, rptcelltype_data_type, rptcelltype_choices, rptcelltype_format) values(511, 'PlayerAbsenceReason', 1, 'SI, INJ, RES, HOL, SC, PU, UNK, LAT, FAM, OTH, REH, ID, SG, GK, F1, R1', null);
insert into rptcelltype(rptcelltype_id, rptcelltype_name, rptcelltype_data_type, rptcelltype_choices, rptcelltype_format) values(512, 'PlayerPhilosophy', 5, null, null);
insert into rptcelltype(rptcelltype_id, rptcelltype_name, rptcelltype_data_type, rptcelltype_choices, rptcelltype_format) values(513, 'PlayerSpecificPhilosophy', 6, null, null);
insert into rptcelltype(rptcelltype_id, rptcelltype_name, rptcelltype_data_type, rptcelltype_choices, rptcelltype_format) values(514, 'PlayerPositionPlayed', 1, 'GK, RFB, LFB, CD, RM, HM, AM, LM, RW, LW, ST', null);
insert into rptcelltype(rptcelltype_id, rptcelltype_name, rptcelltype_data_type, rptcelltype_choices, rptcelltype_format) values(515, 'PlayerBehaviourIssue', 2, null, null);
insert into rptcelltype(rptcelltype_id, rptcelltype_name, rptcelltype_data_type, rptcelltype_choices, rptcelltype_format) values(516, 'PlayerMinutePercentage', 3, null, null);
insert into rptcelltype(rptcelltype_id, rptcelltype_name, rptcelltype_data_type, rptcelltype_choices, rptcelltype_format) values(517, 'PlayerGuestComment', 0, null, null);
insert into rptcelltype(rptcelltype_id, rptcelltype_name, rptcelltype_data_type, rptcelltype_choices, rptcelltype_format) values(526, 'PlayerPracticeTotalNumber', 3, null, null);
insert into rptcelltype(rptcelltype_id, rptcelltype_name, rptcelltype_data_type, rptcelltype_choices, rptcelltype_format) values(527, 'PlayerPracticeAttendedinFull', 3, null, null);
insert into rptcelltype(rptcelltype_id, rptcelltype_name, rptcelltype_data_type, rptcelltype_choices, rptcelltype_format) values(528, 'PlayerPracticePercentageAttended', 3, null, null);
insert into rptcelltype(rptcelltype_id, rptcelltype_name, rptcelltype_data_type, rptcelltype_choices, rptcelltype_format) values(529, 'PlayerGameTotalNumber', 3, null, null);
insert into rptcelltype(rptcelltype_id, rptcelltype_name, rptcelltype_data_type, rptcelltype_choices, rptcelltype_format) values(530, 'PlayerGameAttendedinFull', 3, null, null);
insert into rptcelltype(rptcelltype_id, rptcelltype_name, rptcelltype_data_type, rptcelltype_choices, rptcelltype_format) values(531, 'PlayerGamePercentageAttended', 3, null, null);
insert into rptcelltype(rptcelltype_id, rptcelltype_name, rptcelltype_data_type, rptcelltype_choices, rptcelltype_format) values(532, 'PlayerOverallRating', 3, null, null);
insert into rptcelltype(rptcelltype_id, rptcelltype_name, rptcelltype_data_type, rptcelltype_choices, rptcelltype_format) values(533, 'PlayerPracticeRating', 4, null, null);
insert into rptcelltype(rptcelltype_id, rptcelltype_name, rptcelltype_data_type, rptcelltype_choices, rptcelltype_format) values(534, 'PlayerGameRating', 4, null, null);
insert into rptcelltype(rptcelltype_id, rptcelltype_name, rptcelltype_data_type, rptcelltype_choices, rptcelltype_format) values(535, 'PlayerAreaToWorkOn', 0, null, null);
insert into rptcelltype(rptcelltype_id, rptcelltype_name, rptcelltype_data_type, rptcelltype_choices, rptcelltype_format) values(536, 'PlayerRecommendedActionPlan', 0, null, null);
insert into rptcelltype(rptcelltype_id, rptcelltype_name, rptcelltype_data_type, rptcelltype_choices, rptcelltype_format)values(567, 'PlayerCaution', 1, 'Yellow, Red', null);
insert into rptcelltype(rptcelltype_id, rptcelltype_name, rptcelltype_data_type, rptcelltype_choices, rptcelltype_format, rptcelltype_default_value)values(568, 'PlayerIndividualTime', 2, null, null, 'false');
insert into rptcelltype(rptcelltype_id, rptcelltype_name, rptcelltype_data_type, rptcelltype_choices, rptcelltype_format)values(569, 'PlayerReviewDate', 9, null, null);
insert into rptcelltype(rptcelltype_id, rptcelltype_name, rptcelltype_data_type, rptcelltype_choices, rptcelltype_format)values(570, 'PlayerNumberIndividualSession', 3, null, null);
insert into rptcelltype(rptcelltype_id, rptcelltype_name, rptcelltype_data_type, rptcelltype_choices, rptcelltype_format)values(571, 'PlayerSummaryPositionPlayed', 1, null, null);
insert into rptcelltype(rptcelltype_id, rptcelltype_name, rptcelltype_data_type, rptcelltype_choices, rptcelltype_format)values(572, 'PlayerPitchTime', 3, null, null);

insert into rptcelltype(rptcelltype_id, rptcelltype_name, rptcelltype_data_type, rptcelltype_choices, rptcelltype_format, rptcelltype_default_value)values(700, 'ReportLocation', 1, null, null, null);
insert into rptcelltype(rptcelltype_id, rptcelltype_name, rptcelltype_data_type, rptcelltype_choices, rptcelltype_format, rptcelltype_default_value)values(701, 'PlayerTrainingType', 1, 'Individual,Team', null, null);
insert into rptcelltype(rptcelltype_id, rptcelltype_name, rptcelltype_data_type, rptcelltype_choices, rptcelltype_format, rptcelltype_default_value)values(702, 'PlayerIntensityRating', 3, '1,2,3,4,5,6,7,8,9,10', null, null);
insert into rptcelltype(rptcelltype_id, rptcelltype_name, rptcelltype_data_type, rptcelltype_choices, rptcelltype_format, rptcelltype_default_value)values(703, 'PlayerEffortRating', 3, '1,2,3,4,5,6,7,8,9,10', null, null);
insert into rptcelltype(rptcelltype_id, rptcelltype_name, rptcelltype_data_type, rptcelltype_choices, rptcelltype_format, rptcelltype_default_value)values(704, 'PlayerTechniqueRating', 3, '1,2,3,4,5,6,7,8,9,10', null, null);
insert into rptcelltype(rptcelltype_id, rptcelltype_name, rptcelltype_data_type, rptcelltype_choices, rptcelltype_format, rptcelltype_default_value)values(705, 'PlayerWhatCovered', 0, null, null, null);
insert into rptcelltype(rptcelltype_id, rptcelltype_name, rptcelltype_data_type, rptcelltype_choices, rptcelltype_format, rptcelltype_default_value)values(706, 'PlayerPrincipeCovered', 0, null, null, null);
insert into rptcelltype(rptcelltype_id, rptcelltype_name, rptcelltype_data_type, rptcelltype_choices, rptcelltype_format, rptcelltype_default_value)values(707, 'PlayerTechnical_PassingReceiving', 2, null, null, null);
insert into rptcelltype(rptcelltype_id, rptcelltype_name, rptcelltype_data_type, rptcelltype_choices, rptcelltype_format, rptcelltype_default_value)values(708, 'PlayerTechnical_Heading', 2, null, null, null);
insert into rptcelltype(rptcelltype_id, rptcelltype_name, rptcelltype_data_type, rptcelltype_choices, rptcelltype_format, rptcelltype_default_value)values(709, 'PlayerTechnical_Dribbling', 2, null, null, null);
insert into rptcelltype(rptcelltype_id, rptcelltype_name, rptcelltype_data_type, rptcelltype_choices, rptcelltype_format, rptcelltype_default_value)values(710, 'PlayerTechnical_Shooting', 2, null, null, null);
insert into rptcelltype(rptcelltype_id, rptcelltype_name, rptcelltype_data_type, rptcelltype_choices, rptcelltype_format, rptcelltype_default_value)values(711, 'PlayerTechnical_CrossingFinishing', 2, null, null, null);
insert into rptcelltype(rptcelltype_id, rptcelltype_name, rptcelltype_data_type, rptcelltype_choices, rptcelltype_format, rptcelltype_default_value)values(712, 'PlayerTechnical_BallControl', 2, null, null, null);
insert into rptcelltype(rptcelltype_id, rptcelltype_name, rptcelltype_data_type, rptcelltype_choices, rptcelltype_format, rptcelltype_default_value)values(713, 'PlayerTechnical_Defending', 2, null, null, null);
insert into rptcelltype(rptcelltype_id, rptcelltype_name, rptcelltype_data_type, rptcelltype_choices, rptcelltype_format, rptcelltype_default_value)values(714, 'PlayerTactical_AttackingPrinciples', 2, null, null, null);
insert into rptcelltype(rptcelltype_id, rptcelltype_name, rptcelltype_data_type, rptcelltype_choices, rptcelltype_format, rptcelltype_default_value)values(715, 'PlayerTactical_Possession', 2, null, null, null);
insert into rptcelltype(rptcelltype_id, rptcelltype_name, rptcelltype_data_type, rptcelltype_choices, rptcelltype_format, rptcelltype_default_value)values(716, 'PlayerTactical_Defending', 2, null, null, null);
insert into rptcelltype(rptcelltype_id, rptcelltype_name, rptcelltype_data_type, rptcelltype_choices, rptcelltype_format, rptcelltype_default_value)values(717, 'PlayerTactical_Finishing', 2, null, null, null);
insert into rptcelltype(rptcelltype_id, rptcelltype_name, rptcelltype_data_type, rptcelltype_choices, rptcelltype_format, rptcelltype_default_value)values(718, 'PlayerTactical_Combination', 2, null, null, null);
insert into rptcelltype(rptcelltype_id, rptcelltype_name, rptcelltype_data_type, rptcelltype_choices, rptcelltype_format, rptcelltype_default_value)values(719, 'PlayerTactical_CounterAttack', 2, null, null, null);
insert into rptcelltype(rptcelltype_id, rptcelltype_name, rptcelltype_data_type, rptcelltype_choices, rptcelltype_format, rptcelltype_default_value)values(720, 'PlayerTactical_PlayingOutTheBack', 2, null, null, null);
insert into rptcelltype(rptcelltype_id, rptcelltype_name, rptcelltype_data_type, rptcelltype_choices, rptcelltype_format, rptcelltype_default_value)values(721, 'PlayerTactical_Transition', 2, null, null, null);
insert into rptcelltype(rptcelltype_id, rptcelltype_name, rptcelltype_data_type, rptcelltype_choices, rptcelltype_format, rptcelltype_default_value)values(722, 'PlayerFitness_Jog', 2, null, null, null);
insert into rptcelltype(rptcelltype_id, rptcelltype_name, rptcelltype_data_type, rptcelltype_choices, rptcelltype_format, rptcelltype_default_value)values(723, 'PlayerFitness_JogDistance', 1, null, null, null);
insert into rptcelltype(rptcelltype_id, rptcelltype_name, rptcelltype_data_type, rptcelltype_choices, rptcelltype_format, rptcelltype_default_value)values(724, 'PlayerFitness_JogMinute', 1, null, null, null);
insert into rptcelltype(rptcelltype_id, rptcelltype_name, rptcelltype_data_type, rptcelltype_choices, rptcelltype_format, rptcelltype_default_value)values(725, 'PlayerFitness_Speed', 2, null, null, null);
insert into rptcelltype(rptcelltype_id, rptcelltype_name, rptcelltype_data_type, rptcelltype_choices, rptcelltype_format, rptcelltype_default_value)values(726, 'PlayerFitness_SpeedDistance', 1, null, null, null);
insert into rptcelltype(rptcelltype_id, rptcelltype_name, rptcelltype_data_type, rptcelltype_choices, rptcelltype_format, rptcelltype_default_value)values(727, 'PlayerFitness_SpeedMinute', 1, null, null, null);
insert into rptcelltype(rptcelltype_id, rptcelltype_name, rptcelltype_data_type, rptcelltype_choices, rptcelltype_format, rptcelltype_default_value)values(728, 'PlayerFitness_Agility', 2, null, null, null);
insert into rptcelltype(rptcelltype_id, rptcelltype_name, rptcelltype_data_type, rptcelltype_choices, rptcelltype_format, rptcelltype_default_value)values(729, 'PlayerFitness_Weight', 2, null, null, null);
insert into rptcelltype(rptcelltype_id, rptcelltype_name, rptcelltype_data_type, rptcelltype_choices, rptcelltype_format, rptcelltype_default_value)values(730, 'PlayerFitness_BodyPart_Legs', 2, null, null, null);
insert into rptcelltype(rptcelltype_id, rptcelltype_name, rptcelltype_data_type, rptcelltype_choices, rptcelltype_format, rptcelltype_default_value)values(731, 'PlayerFitness_BodyPart_Chest', 2, null, null, null);
insert into rptcelltype(rptcelltype_id, rptcelltype_name, rptcelltype_data_type, rptcelltype_choices, rptcelltype_format, rptcelltype_default_value)values(732, 'PlayerFitness_BodyPart_Back', 2, null, null, null);
insert into rptcelltype(rptcelltype_id, rptcelltype_name, rptcelltype_data_type, rptcelltype_choices, rptcelltype_format, rptcelltype_default_value)values(733, 'PlayerFitness_BodyPart_Arms', 2, null, null, null);
insert into rptcelltype(rptcelltype_id, rptcelltype_name, rptcelltype_data_type, rptcelltype_choices, rptcelltype_format, rptcelltype_default_value)values(734, 'PlayerFitness_BodyPart_Stomach', 2, null, null, null);
insert into rptcelltype(rptcelltype_id, rptcelltype_name, rptcelltype_data_type, rptcelltype_choices, rptcelltype_format, rptcelltype_default_value)values(735, 'PlayerFitness_BodyPart_Shoulders', 2, null, null, null);
insert into rptcelltype(rptcelltype_id, rptcelltype_name, rptcelltype_data_type, rptcelltype_choices, rptcelltype_format, rptcelltype_default_value)values(736, 'PlayerFitness_NumberExercices', 3, null, null, null);
insert into rptcelltype(rptcelltype_id, rptcelltype_name, rptcelltype_data_type, rptcelltype_choices, rptcelltype_format, rptcelltype_default_value)values(737, 'PlayerFitness_NumberSets', 3, null, null, null);
insert into rptcelltype(rptcelltype_id, rptcelltype_name, rptcelltype_data_type, rptcelltype_choices, rptcelltype_format, rptcelltype_default_value)values(738, 'PlayerFitness_NumberRepetitions', 3, null, null, null);
insert into rptcelltype(rptcelltype_id, rptcelltype_name, rptcelltype_data_type, rptcelltype_choices, rptcelltype_format, rptcelltype_default_value)values(740, 'PlayerNutrition_Carbs', 2, null, null, null);
insert into rptcelltype(rptcelltype_id, rptcelltype_name, rptcelltype_data_type, rptcelltype_choices, rptcelltype_format, rptcelltype_default_value)values(741, 'PlayerNutrition_Protein', 2, null, null, null);
insert into rptcelltype(rptcelltype_id, rptcelltype_name, rptcelltype_data_type, rptcelltype_choices, rptcelltype_format, rptcelltype_default_value)values(742, 'PlayerNutrition_VegFruit', 2, null, null, null);
insert into rptcelltype(rptcelltype_id, rptcelltype_name, rptcelltype_data_type, rptcelltype_choices, rptcelltype_format, rptcelltype_default_value)values(743, 'PlayerNutrition_Drink', 2, null, null, null);
insert into rptcelltype(rptcelltype_id, rptcelltype_name, rptcelltype_data_type, rptcelltype_choices, rptcelltype_format, rptcelltype_default_value)values(744, 'PlayerNutrition_WheatBread', 2, null, null, null);
insert into rptcelltype(rptcelltype_id, rptcelltype_name, rptcelltype_data_type, rptcelltype_choices, rptcelltype_format, rptcelltype_default_value)values(745, 'PlayerNutrition_Dairy', 2, null, null, null);
insert into rptcelltype(rptcelltype_id, rptcelltype_name, rptcelltype_data_type, rptcelltype_choices, rptcelltype_format, rptcelltype_default_value)values(746, 'PlayerNutrition_Water', 2, null, null, null);
insert into rptcelltype(rptcelltype_id, rptcelltype_name, rptcelltype_data_type, rptcelltype_choices, rptcelltype_format, rptcelltype_default_value)values(747, 'PlayerNutrition_WhiteBread', 2, null, null, null);
insert into rptcelltype(rptcelltype_id, rptcelltype_name, rptcelltype_data_type, rptcelltype_choices, rptcelltype_format, rptcelltype_default_value)values(748, 'PlayerNutrition_Beans', 2, null, null, null);
insert into rptcelltype(rptcelltype_id, rptcelltype_name, rptcelltype_data_type, rptcelltype_choices, rptcelltype_format, rptcelltype_default_value)values(749, 'PlayerNutrition_Fizzy', 2, null, null, null);
insert into rptcelltype(rptcelltype_id, rptcelltype_name, rptcelltype_data_type, rptcelltype_choices, rptcelltype_format, rptcelltype_default_value)values(750, 'PlayerNutrition_Pasta', 2, null, null, null);
insert into rptcelltype(rptcelltype_id, rptcelltype_name, rptcelltype_data_type, rptcelltype_choices, rptcelltype_format, rptcelltype_default_value)values(751, 'PlayerNutrition_Meat', 2, null, null, null);
insert into rptcelltype(rptcelltype_id, rptcelltype_name, rptcelltype_data_type, rptcelltype_choices, rptcelltype_format, rptcelltype_default_value)values(752, 'PlayerNutrition_Sports', 2, null, null, null);
insert into rptcelltype(rptcelltype_id, rptcelltype_name, rptcelltype_data_type, rptcelltype_choices, rptcelltype_format, rptcelltype_default_value)values(753, 'PlayerNutrition_Rice', 2, null, null, null);
insert into rptcelltype(rptcelltype_id, rptcelltype_name, rptcelltype_data_type, rptcelltype_choices, rptcelltype_format, rptcelltype_default_value)values(754, 'PlayerNutrition_Chicken', 2, null, null, null);
insert into rptcelltype(rptcelltype_id, rptcelltype_name, rptcelltype_data_type, rptcelltype_choices, rptcelltype_format, rptcelltype_default_value)values(755, 'PlayerNutrition_Milk', 2, null, null, null);
insert into rptcelltype(rptcelltype_id, rptcelltype_name, rptcelltype_data_type, rptcelltype_choices, rptcelltype_format, rptcelltype_default_value)values(756, 'PlayerNutrition_Cereal', 2, null, null, null);
insert into rptcelltype(rptcelltype_id, rptcelltype_name, rptcelltype_data_type, rptcelltype_choices, rptcelltype_format, rptcelltype_default_value)values(757, 'PlayerNutrition_Fish', 2, null, null, null);
insert into rptcelltype(rptcelltype_id, rptcelltype_name, rptcelltype_data_type, rptcelltype_choices, rptcelltype_format, rptcelltype_default_value)values(758, 'PlayerNutrition_Pizza', 2, null, null, null);
insert into rptcelltype(rptcelltype_id, rptcelltype_name, rptcelltype_data_type, rptcelltype_choices, rptcelltype_format, rptcelltype_default_value)values(759, 'PlayerNutrition_NutsSeeds', 2, null, null, null);
insert into rptcelltype(rptcelltype_id, rptcelltype_name, rptcelltype_data_type, rptcelltype_choices, rptcelltype_format, rptcelltype_default_value)values(760, 'PlayerNutrition_VegFruit_1', 2, null, null, null);
insert into rptcelltype(rptcelltype_id, rptcelltype_name, rptcelltype_data_type, rptcelltype_choices, rptcelltype_format, rptcelltype_default_value)values(761, 'PlayerNutrition_VegFruit_2', 2, null, null, null);
insert into rptcelltype(rptcelltype_id, rptcelltype_name, rptcelltype_data_type, rptcelltype_choices, rptcelltype_format, rptcelltype_default_value)values(763, 'PlayerNutrition_VegFruit_3', 2, null, null, null);
insert into rptcelltype(rptcelltype_id, rptcelltype_name, rptcelltype_data_type, rptcelltype_choices, rptcelltype_format, rptcelltype_default_value)values(764, 'PlayerNutrition_VegFruit_4', 2, null, null, null);
insert into rptcelltype(rptcelltype_id, rptcelltype_name, rptcelltype_data_type, rptcelltype_choices, rptcelltype_format, rptcelltype_default_value)values(765, 'PlayerNutrition_VegFruit_Other', 2, null, null, null);
insert into rptcelltype(rptcelltype_id, rptcelltype_name, rptcelltype_data_type, rptcelltype_choices, rptcelltype_format, rptcelltype_default_value)values(770, 'PlayerPersonalGoal', 0, null, null, null);
insert into rptcelltype(rptcelltype_id, rptcelltype_name, rptcelltype_data_type, rptcelltype_choices, rptcelltype_format, rptcelltype_default_value)values(771, 'PlayerPersonalAction', 0, null, null, null);
insert into rptcelltype(rptcelltype_id, rptcelltype_name, rptcelltype_data_type, rptcelltype_choices, rptcelltype_format, rptcelltype_default_value)values(772, 'Player3YearGoal', 0, null, null, null);
insert into rptcelltype(rptcelltype_id, rptcelltype_name, rptcelltype_data_type, rptcelltype_choices, rptcelltype_format, rptcelltype_default_value)values(773, 'Player3YearAction', 0, null, null, null);
insert into rptcelltype(rptcelltype_id, rptcelltype_name, rptcelltype_data_type, rptcelltype_choices, rptcelltype_format, rptcelltype_default_value)values(774, 'PlayerSeasonGoal', 0, null, null, null);
insert into rptcelltype(rptcelltype_id, rptcelltype_name, rptcelltype_data_type, rptcelltype_choices, rptcelltype_format, rptcelltype_default_value)values(775, 'PlayerSeasonAction', 0, null, null, null);
insert into rptcelltype(rptcelltype_id, rptcelltype_name, rptcelltype_data_type, rptcelltype_choices, rptcelltype_format, rptcelltype_default_value)values(776, 'PlayerHomeGoal', 0, null, null, null);
insert into rptcelltype(rptcelltype_id, rptcelltype_name, rptcelltype_data_type, rptcelltype_choices, rptcelltype_format, rptcelltype_default_value)values(798, 'PlayerHomeAction', 0, null, null, null);
insert into rptcelltype(rptcelltype_id, rptcelltype_name, rptcelltype_data_type, rptcelltype_choices, rptcelltype_format, rptcelltype_default_value)values(777, 'PlayerSchoolGoal', 0, null, null, null);
insert into rptcelltype(rptcelltype_id, rptcelltype_name, rptcelltype_data_type, rptcelltype_choices, rptcelltype_format, rptcelltype_default_value)values(799, 'PlayerSchoolAction', 0, null, null, null);
insert into rptcelltype(rptcelltype_id, rptcelltype_name, rptcelltype_data_type, rptcelltype_choices, rptcelltype_format, rptcelltype_default_value)values(778, 'Player3MonthGoal', 0, null, null, null);
insert into rptcelltype(rptcelltype_id, rptcelltype_name, rptcelltype_data_type, rptcelltype_choices, rptcelltype_format, rptcelltype_default_value)values(779, 'Player3MonthAction', 0, null, null, null);
insert into rptcelltype(rptcelltype_id, rptcelltype_name, rptcelltype_data_type, rptcelltype_choices, rptcelltype_format, rptcelltype_default_value)values(780, 'Player3MonthPerformance', 0, null, null, null);
insert into rptcelltype(rptcelltype_id, rptcelltype_name, rptcelltype_data_type, rptcelltype_choices, rptcelltype_format, rptcelltype_default_value)values(781, 'Player3MonthImprovement', 0, null, null, null);
insert into rptcelltype(rptcelltype_id, rptcelltype_name, rptcelltype_data_type, rptcelltype_choices, rptcelltype_format, rptcelltype_default_value)values(782, 'PlayerTeamPracticeTotalNumber', 3, null, null, null);
insert into rptcelltype(rptcelltype_id, rptcelltype_name, rptcelltype_data_type, rptcelltype_choices, rptcelltype_format, rptcelltype_default_value)values(783, 'PlayerIndividualPracticeTotalNumber', 3, null, null, null);
insert into rptcelltype(rptcelltype_id, rptcelltype_name, rptcelltype_data_type, rptcelltype_choices, rptcelltype_format, rptcelltype_default_value)values(784, 'PlayerTeamPracticeTotalMinute', 3, null, null, null);
insert into rptcelltype(rptcelltype_id, rptcelltype_name, rptcelltype_data_type, rptcelltype_choices, rptcelltype_format, rptcelltype_default_value)values(785, 'PlayerIndividualPracticeTotalMinute', 3, null, null, null);
insert into rptcelltype(rptcelltype_id, rptcelltype_name, rptcelltype_data_type, rptcelltype_choices, rptcelltype_format, rptcelltype_default_value)values(786, 'PlayerPerformanceRating', 3, '1, 2, 3, 4, 5, 6, 7, 8, 9, 10', null, null);
insert into rptcelltype(rptcelltype_id, rptcelltype_name, rptcelltype_data_type, rptcelltype_choices, rptcelltype_format, rptcelltype_default_value)values(787, 'PlayerNutritionRating', 3, '1, 2, 3, 4, 5, 6, 7, 8, 9, 10', null, null);
insert into rptcelltype(rptcelltype_id, rptcelltype_name, rptcelltype_data_type, rptcelltype_choices, rptcelltype_format, rptcelltype_default_value)values(788, 'PlayerNutritionComment', 0, null, null, null);
insert into rptcelltype(rptcelltype_id, rptcelltype_name, rptcelltype_data_type, rptcelltype_choices, rptcelltype_format, rptcelltype_default_value)values(789, 'PlayerRestRecovery_8', 2, null, null, null);
insert into rptcelltype(rptcelltype_id, rptcelltype_name, rptcelltype_data_type, rptcelltype_choices, rptcelltype_format, rptcelltype_default_value)values(790, 'PlayerRestRecovery_7_8', 2, null, null, null);
insert into rptcelltype(rptcelltype_id, rptcelltype_name, rptcelltype_data_type, rptcelltype_choices, rptcelltype_format, rptcelltype_default_value)values(791, 'PlayerRestRecovery_6_7', 2, null, null, null);
insert into rptcelltype(rptcelltype_id, rptcelltype_name, rptcelltype_data_type, rptcelltype_choices, rptcelltype_format, rptcelltype_default_value)values(792, 'PlayerRestRecovery_6', 2, null, null, null);
insert into rptcelltype(rptcelltype_id, rptcelltype_name, rptcelltype_data_type, rptcelltype_choices, rptcelltype_format, rptcelltype_default_value)values(793, 'PlayerTotalSleepHours', 3, '1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16', null, null);
insert into rptcelltype(rptcelltype_id, rptcelltype_name, rptcelltype_data_type, rptcelltype_choices, rptcelltype_format, rptcelltype_default_value)values(794, 'PlayerWeekRating', 3, '1, 2, 3, 4, 5, 6, 7, 8, 9, 10', null, null);
insert into rptcelltype(rptcelltype_id, rptcelltype_name, rptcelltype_data_type, rptcelltype_choices, rptcelltype_format, rptcelltype_default_value)values(795, 'PlayerTrainingRating', 3, '1, 2, 3, 4, 5, 6, 7, 8, 9, 10', null, null);
insert into rptcelltype(rptcelltype_id, rptcelltype_name, rptcelltype_data_type, rptcelltype_choices, rptcelltype_format, rptcelltype_default_value)values(796, 'PlayerTrainingMinute', 3, null, null, null);

insert into rptcelltype(rptcelltype_id, rptcelltype_name, rptcelltype_data_type, rptcelltype_choices, rptcelltype_format, rptcelltype_default_value)values(800, 'CoachingMethodologyRating', 3, '1,2,3,4,5', null, null);
insert into rptcelltype(rptcelltype_id, rptcelltype_name, rptcelltype_data_type, rptcelltype_choices, rptcelltype_format, rptcelltype_default_value)values(801, 'CoachingMethodologyComment', 0, null, null, null);
insert into rptcelltype(rptcelltype_id, rptcelltype_name, rptcelltype_data_type, rptcelltype_choices, rptcelltype_format, rptcelltype_default_value)values(802, 'CoachEffectivenessRating', 3, '1,2,3,4,5', null, null);
insert into rptcelltype(rptcelltype_id, rptcelltype_name, rptcelltype_data_type, rptcelltype_choices, rptcelltype_format, rptcelltype_default_value)values(803, 'CoachEffectivenessComment', 0, null, null, null);
insert into rptcelltype(rptcelltype_id, rptcelltype_name, rptcelltype_data_type, rptcelltype_choices, rptcelltype_format, rptcelltype_default_value)values(804, 'PlayerDevelopmentRating', 3, '1,2,3,4,5', null, null);
insert into rptcelltype(rptcelltype_id, rptcelltype_name, rptcelltype_data_type, rptcelltype_choices, rptcelltype_format, rptcelltype_default_value)values(805, 'PlayerDevelopmentComment', 0, null, null, null);
insert into rptcelltype(rptcelltype_id, rptcelltype_name, rptcelltype_data_type, rptcelltype_choices, rptcelltype_format, rptcelltype_default_value)values(806, 'OnlinePlayerDevelopmentRating', 3, '1,2,3,4,5', null, null);
insert into rptcelltype(rptcelltype_id, rptcelltype_name, rptcelltype_data_type, rptcelltype_choices, rptcelltype_format, rptcelltype_default_value)values(807, 'OnlinePlayerDevelopmentComment', 0, null, null, null);
insert into rptcelltype(rptcelltype_id, rptcelltype_name, rptcelltype_data_type, rptcelltype_choices, rptcelltype_format, rptcelltype_default_value)values(808, 'OnlinePlayerPerformanceRating', 3, '1,2,3,4,5', null, null);
insert into rptcelltype(rptcelltype_id, rptcelltype_name, rptcelltype_data_type, rptcelltype_choices, rptcelltype_format, rptcelltype_default_value)values(809, 'OnlinePlayerPerformanceComment', 0, null, null, null);
insert into rptcelltype(rptcelltype_id, rptcelltype_name, rptcelltype_data_type, rptcelltype_choices, rptcelltype_format, rptcelltype_default_value)values(810, 'CoachProfessionalismRating', 3, '1,2,3,4,5', null, null);
insert into rptcelltype(rptcelltype_id, rptcelltype_name, rptcelltype_data_type, rptcelltype_choices, rptcelltype_format, rptcelltype_default_value)values(811, 'CoachProfessionalismComment', 0, null, null, null);
insert into rptcelltype(rptcelltype_id, rptcelltype_name, rptcelltype_data_type, rptcelltype_choices, rptcelltype_format, rptcelltype_default_value)values(812, 'GameManagementRating', 3, '1,2,3,4,5', null, null);
insert into rptcelltype(rptcelltype_id, rptcelltype_name, rptcelltype_data_type, rptcelltype_choices, rptcelltype_format, rptcelltype_default_value)values(813, 'GameManagementComment', 0, null, null, null);
insert into rptcelltype(rptcelltype_id, rptcelltype_name, rptcelltype_data_type, rptcelltype_choices, rptcelltype_format, rptcelltype_default_value)values(814, 'OverallTrainingEnvironmentRating', 3, '1,2,3,4,5', null, null);
insert into rptcelltype(rptcelltype_id, rptcelltype_name, rptcelltype_data_type, rptcelltype_choices, rptcelltype_format, rptcelltype_default_value)values(815, 'OverallTrainingEnvironmentComment', 0, null, null, null);
insert into rptcelltype(rptcelltype_id, rptcelltype_name, rptcelltype_data_type, rptcelltype_choices, rptcelltype_format, rptcelltype_default_value)values(816, 'Recommendation', 0, null, null, null);
insert into rptcelltype(rptcelltype_id, rptcelltype_name, rptcelltype_data_type, rptcelltype_choices, rptcelltype_format, rptcelltype_default_value)values(817, 'PlayersMentalityRating', 3, '1,2,3,4,5', null, null);
insert into rptcelltype(rptcelltype_id, rptcelltype_name, rptcelltype_data_type, rptcelltype_choices, rptcelltype_format, rptcelltype_default_value)values(818, 'PlayersMentalityComment', 0, null, null, null);
insert into rptcelltype(rptcelltype_id, rptcelltype_name, rptcelltype_data_type, rptcelltype_choices, rptcelltype_format, rptcelltype_default_value)values(819, 'AssistantCoachRating', 3, '1,2,3,4,5', null, null);
insert into rptcelltype(rptcelltype_id, rptcelltype_name, rptcelltype_data_type, rptcelltype_choices, rptcelltype_format, rptcelltype_default_value)values(820, 'AssistantCoachComment', 0, null, null, null);
insert into rptcelltype(rptcelltype_id, rptcelltype_name, rptcelltype_data_type, rptcelltype_choices, rptcelltype_format, rptcelltype_default_value)values(831, 'CoachingKnowledgeRating', 3, '1,2,3,4,5', null, null);
insert into rptcelltype(rptcelltype_id, rptcelltype_name, rptcelltype_data_type, rptcelltype_choices, rptcelltype_format, rptcelltype_default_value)values(832, 'CoachingKnowledgeGoals', 0, null, null, null);
insert into rptcelltype(rptcelltype_id, rptcelltype_name, rptcelltype_data_type, rptcelltype_choices, rptcelltype_format, rptcelltype_default_value)values(833, 'CoachingKnowledgeSelfEvaluation', 0, null, null, null);
insert into rptcelltype(rptcelltype_id, rptcelltype_name, rptcelltype_data_type, rptcelltype_choices, rptcelltype_format, rptcelltype_default_value)values(834, 'CoachingMethodologyGoals', 0, null, null, null);
insert into rptcelltype(rptcelltype_id, rptcelltype_name, rptcelltype_data_type, rptcelltype_choices, rptcelltype_format, rptcelltype_default_value)values(835, 'CoachingMethodologySelfEvaluation', 0, null, null, null);
insert into rptcelltype(rptcelltype_id, rptcelltype_name, rptcelltype_data_type, rptcelltype_choices, rptcelltype_format, rptcelltype_default_value)values(836, 'ExecutionCommunicationRating', 3, '1,2,3,4,5', null, null);
insert into rptcelltype(rptcelltype_id, rptcelltype_name, rptcelltype_data_type, rptcelltype_choices, rptcelltype_format, rptcelltype_default_value)values(837, 'ExecutionCommunicationGoals', 0, null, null, null);
insert into rptcelltype(rptcelltype_id, rptcelltype_name, rptcelltype_data_type, rptcelltype_choices, rptcelltype_format, rptcelltype_default_value)values(838, 'ExecutionCommunicationSelfEvaluation', 0, null, null, null);

insert into rptcelltype(rptcelltype_id, rptcelltype_name, rptcelltype_data_type, rptcelltype_choices, rptcelltype_format, rptcelltype_default_value)values(839, 'TrainingSessionQualityRating', 3, '1,2,3,4,5', null, null);
insert into rptcelltype(rptcelltype_id, rptcelltype_name, rptcelltype_data_type, rptcelltype_choices, rptcelltype_format, rptcelltype_default_value)values(840, 'TrainingSessionQualityComment', 0, null, null, null);
insert into rptcelltype(rptcelltype_id, rptcelltype_name, rptcelltype_data_type, rptcelltype_choices, rptcelltype_format, rptcelltype_default_value)values(841, 'PlayerDevelopmentPlanImplementationRating', 3, '1,2,3,4,5', null, null);
insert into rptcelltype(rptcelltype_id, rptcelltype_name, rptcelltype_data_type, rptcelltype_choices, rptcelltype_format, rptcelltype_default_value)values(842, 'PlayerDevelopmentPlanImplementationComment', 0, null, null, null);
