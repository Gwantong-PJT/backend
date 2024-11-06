CREATE SCHEMA if not exists `penetration`;
use penetration;

-- Create table "users_tb"
CREATE TABLE if not exists `USERS_TB` (
	`user_no`	int	NOT NULL auto_increment primary key,
	`user_id`	varchar(20) not null unique,
	`user_password`	varchar(20) not null,
	`user_name`	varchar(20)	NULL default "user",
	`user_role`	varchar(10)	NULL default "USER",
	`user_profile`	varchar(100)	NULL,
	`age_no`	int,
	`user_residence`	int
);

insert into users_tb(`user_id`, `user_password`, `user_name`, `user_role`)
values ("admin","1111","어드민1","ADMIN"),
("a","1","빠른관리자","ADMIN"),
("ssafy","1234","싸피인","USER"),
("aaa","4321","김싸피","USER")
;

-- Create table "age"
CREATE TABLE if not exists `AGE_TB` (
	`age_no`	int	NOT NULL auto_increment primary key,
	`age_value`	int	NULL
);

insert into age_tb(`age_value`)
values (10),(20),(30),(40),(50),(60),(70),(80),(90)
;
