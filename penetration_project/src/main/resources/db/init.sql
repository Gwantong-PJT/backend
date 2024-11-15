CREATE SCHEMA if not exists `penetration`;
use penetration;

-- Create table "age_tb"
CREATE TABLE if not exists `AGE_TB` (
	`age_no`	int	NOT NULL auto_increment primary key,
	`age_value`	int	NULL
);

insert into age_tb(`age_value`)
values (10),(20),(30),(40),(50),(60),(70),(80),(90)
;

-- Create table "users_tb"
CREATE TABLE if not exists `USERS_TB` (
	`user_no`	int	NOT NULL auto_increment primary key,
	`user_id`	varchar(20) not null unique,
	`user_password`	varchar(20) not null,
	`user_name`	varchar(20)	NULL default "user",
	`user_role`	varchar(10)	NULL default "USER",
	`user_profile`	varchar(100)	NULL,
	`age_no`	int,
	`user_residence`	int,
    `user_sex` int,
    foreign key (age_no) references age_tb(age_no),
    foreign key (user_residence) references sidos(sido_code)
);

insert into users_tb(`user_id`, `user_password`, `user_name`, `user_role`)
values ("admin","1111","어드민1","ADMIN"),
("a","1","빠른관리자","ADMIN"),
("ssafy","1234","싸피인","USER"),
("aaa","4321","김싸피","USER")
;

-- Create table "hotplaces_tb"
CREATE TABLE if not exists `hotplaces_tb` (
	`hotplace_no` int NOT NULL auto_increment,
	`user_no` int	NOT NULL,
	`hotplace_title` varchar(100),
	`hotplace_text`	varchar(10000),
	`hotplace_date`	datetime NULL default CURRENT_TIMESTAMP,
	`hotplace_views` int default 0,
    primary key (hotplace_no),
    foreign key (user_no) references users_tb(user_no) on delete cascade
);

insert into hotplaces_tb(`user_no`, `hotplace_title`,`hotplace_text`)
values
(1, '테스트 글입니다', "테스트 글 본문입니다."),
(1, '1번 유저의 글', "1번 유저의 글 본문입니다."),
(2, '2번 유저의 글입니다.', "2번 유저의 테스트 글 본문입니다."),
(2, '관리자 테스트 글입니다.', "관리자 글 본문입니다."),
(3, '빠른 테스트 글입니다.', "사실 별 차이 없습니다."),
(3, '크아악', "으아아악"),
(4, '싸피이인', "댓글이 없는 글의 본문입니다.")
;

-- Create table "user_refresh_token_tb"
CREATE TABLE if not exists `user_refresh_token_tb` (
	`user_id`	varchar(20)	NOT NULL,
	`refresh_token`	varchar(1000)	NULL,
	foreign key (user_id) references users_tb(user_id) on delete cascade
);

-- Create table "inquiry_tb"
CREATE TABLE `inquiry_tb` (
	`attraction_no`	int	NOT NULL,
	`age_no`	int	NOT NULL,
	`views`	int	NULL,
    foreign key (attraction_no) references attractions(attraction_no),
    foreign key (age_no) references age_tb(age_no)
);

CREATE TABLE `comment_tb` (
	`comment_no`	int	NOT NULL auto_increment,
	`hotplace_no`	int	NOT NULL,
	`user_no`	int	NOT NULL,
	`comment_text`	varchar(10000)	NULL,
	`comment_date`	datetime NULL default CURRENT_TIMESTAMP,
    primary key (comment_no),
    foreign key (hotplace_no) references hotplaces_tb(hotplace_no) on delete cascade,
    foreign key (user_no) references users_tb(user_no) on delete cascade
);

insert into comment_tb(`hotplace_no`, `user_no`,`comment_text`)
values
(1, 1, "댓글 답니다"),
(1, 3, "너무 좋네요!"),
(1, 4, "1번 글에 달린 댓글입니다"),
(2, 2, "지나가요"),
(3, 1, "다 봤다"),
(3, 2, "좋았습니다"),
(4, 3, "4번 글에 3번 유저가 쓴 댓글입니다."),
(5, 4, "글 추가 합니다"),
(6, 4, "갸아아악")
;

CREATE TABLE `hotplace_picture_tb` (
	`picture_no`	int	NOT NULL auto_increment,
	`hotplace_no`	int	NOT NULL,
	`picture_url`	varchar(200),
    primary key (picture_no),
    foreign key (hotplace_no) references hotplaces_tb(hotplace_no) on delete cascade
);

insert into hotplace_picture_tb(`hotplace_no`, `picture_url`)
values
(1, "1번 글 1번 사진"),
(1, "1번 글 2번 사진"),
(1, "1번 글 3번 사진"),
(2, "2번 글 1번 사진"),
(3, "3번 글 사진"),
(4, "4번 글 1번 사진"),
(5, "5번 글 사진")
;
