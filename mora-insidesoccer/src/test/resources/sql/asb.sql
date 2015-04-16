drop table if exists asbtpl;
create table asbtpl(
    asbtpl_id bigint not null,
    asbtpl_name varchar(20),
    asbtpl_type varchar(20),
    asbtpl_width int,
    asbtpl_height int,

    primary key (asbtpl_id)
) Engine=innodb;


INSERT INTO asbtpl(asbtpl_id, asbtpl_name, asbtpl_type, asbtpl_width, asbtpl_height) VALUES(1, 'Full Pitch', 'FULL_PITCH', 580, 327);
INSERT INTO asbtpl(asbtpl_id, asbtpl_name, asbtpl_type, asbtpl_width, asbtpl_height) VALUES(2, 'Half Pitch', 'HALF_PITCH', 580, 327);
INSERT INTO asbtpl(asbtpl_id, asbtpl_name, asbtpl_type, asbtpl_width, asbtpl_height) VALUES(3, 'Penalty Area', 'PENALTY_AREA', 580, 327);
INSERT INTO asbtpl(asbtpl_id, asbtpl_name, asbtpl_type, asbtpl_width, asbtpl_height) VALUES(7, 'Blank', 'BLANK', 580, 327);
INSERT INTO asbtpl(asbtpl_id, asbtpl_name, asbtpl_type, asbtpl_width, asbtpl_height) VALUES(8, 'Square', 'SQUARE', 580, 327);
INSERT INTO asbtpl(asbtpl_id, asbtpl_name, asbtpl_type, asbtpl_width, asbtpl_height) VALUES(9, 'Circle', 'CIRCLE', 580, 327);
INSERT INTO asbtpl(asbtpl_id, asbtpl_name, asbtpl_type, asbtpl_width, asbtpl_height) VALUES(10, 'Rectangle', 'RECTANGLE', 580, 327);
INSERT INTO asbtpl(asbtpl_id, asbtpl_name, asbtpl_type, asbtpl_width, asbtpl_height) VALUES(11, 'Triangle', 'TRIANGLE', 580, 327);
INSERT INTO asbtpl(asbtpl_id, asbtpl_name, asbtpl_type, asbtpl_width, asbtpl_height) VALUES(12, 'Diamond', 'DIAMOND', 580, 327);

