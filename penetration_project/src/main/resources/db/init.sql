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
	`user_region`	int,
    `user_sex` int,
    foreign key (age_no) references age_tb(age_no),
    foreign key (user_region) references sidos(sido_code)
);


insert into users_tb(`user_id`, `user_password`, `user_name`, `age_no`, `user_region`, `user_sex`)
values ("admin","1111","어드민1", 1, 1, 1),
("a","1","빠른관리자",2, 1, 2),
("ssafy","1234","싸피인",3, 6, 1),
("aaa","4321","김싸피",2, 7, 2),
("test1","111","테스터1",3, 7, 1),
("test2","111","테스터2",2, 2, 2)
;

-- Create table "hotplaces_tb"
CREATE TABLE if not exists `hotplaces_tb` (
	`hotplace_no` int NOT NULL auto_increment,
	`user_no` int	NOT NULL,
	`hotplace_title` varchar(100),
	`hotplace_text`	varchar(10000),
	`hotplace_date`	datetime NULL default CURRENT_TIMESTAMP,
	`hotplace_views` int default 0,
    `latitude` decimal(20,17),
    `longitude` decimal(20,17),
    primary key (hotplace_no),
    foreign key (user_no) references users_tb(user_no) on delete cascade
);

-- Create table "user_refresh_token_tb"
CREATE TABLE if not exists `user_refresh_token_tb` (
	`user_id`	varchar(20)	NOT NULL,
	`refresh_token`	varchar(1000)	NULL,
	foreign key (user_id) references users_tb(user_id) on delete cascade
);

-- Create table "inquiry_tb"
CREATE TABLE `inquiry_tb` (
	`attraction_no`	int	NOT NULL,
	`user_no`	int	NOT NULL,
	`views`	int	NULL,
	primary key (attraction_no, user_no),
    foreign key (attraction_no) references attractions(attraction_no) on delete cascade,
    foreign key (user_no) references users_tb(user_no) on delete cascade
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

CREATE TABLE `hotplace_picture_tb` (
	`picture_no`	int	NOT NULL auto_increment,
	`hotplace_no`	int	NOT NULL,
	`picture_url`	varchar(200),
    primary key (picture_no),
    foreign key (hotplace_no) references hotplaces_tb(hotplace_no) on delete cascade
);

Create table `like_tb` (
	`user_no` int not null,
	`attraction_no` int not null,
	`liked` boolean not null,
	primary key (user_no, attraction_no),
	foreign key (user_no) references users_tb(user_no) on delete cascade,
	foreign key (attraction_no) references attractions(attraction_no) on delete cascade
);