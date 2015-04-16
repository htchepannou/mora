drop database if exists is5;
create database is5 CHARACTER SET utf8 COLLATE utf8_general_ci;

GRANT ALL PRIVILEGES ON is5.* TO isadmin@localhost IDENTIFIED BY 'isadmin' WITH GRANT OPTION;
GRANT ALL PRIVILEGES ON is5.* TO isadmin@'%' IDENTIFIED BY 'isadmin' WITH GRANT OPTION;