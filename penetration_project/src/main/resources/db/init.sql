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

-- Create table "age_tb"
CREATE TABLE if not exists `AGE_TB` (
	`age_no`	int	NOT NULL auto_increment primary key,
	`age_value`	int	NULL
);

insert into age_tb(`age_value`)
values (10),(20),(30),(40),(50),(60),(70),(80),(90)
;

-- Create table "hotplaces_tb"
CREATE TABLE if not exists `hotplaces_tb` (
	`hotplace_no` int NOT NULL auto_increment primary key,
	`user_no` int	NOT NULL,
	`hotplace_title` varchar(100) NULL,
	`hotplace_text`	varchar(10000)	NULL,
	`hotplace_date`	datetime NULL default CURRENT_TIMESTAMP,
	`hotplace_views` int default 0
);

insert into hotplaces_tb(`user_no`, `hotplace_title`,`hotplace_text`)
values
(1, '테스트 글입니다', "테스트 글 본문입니다."),
(1, '1번 유저의 글', "1번 유저의 글 본문입니다."),
(2, '2번 유저의 글입니다.', "2번 유저의 테스트 글 본문입니다."),
(2, '관리자 테스트 글입니다.', "관리자 글 본문입니다."),
(3, '빠른 테스트 글입니다.', "사실 별 차이 없습니다."),
(3, '크아악', "으아아악"),
(4, '싸피이인', "핫플 게시글 테스트입니다.")
;

-- Create table "user_refresh_token_tb"
CREATE TABLE if not exists `user_refresh_token_tb` (
	`user_id`	varchar(20)	NOT NULL,
	`refresh_token`	varchar(1000)	NULL
);