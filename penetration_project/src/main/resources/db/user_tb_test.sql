use penetration;

show tables;
desc users_tb;

select * from users_tb;

delete from users_tb where user_no = 6;

insert into users_tb(`user_id`, `user_password`, `user_name`)
value ("bbbb","3333","test_user_1");


select * from users_tb where user_id = "ppman";

update users_tb
set 
user_password = "aapa",
user_name = "hi_postman",
user_profile = null,
age_no = null,
user_residence = null
where user_id = "ppman";

drop table users_tb;