use penetration;

show tables;
desc users_tb;

select * from users_tb
left join age_tb
using (age_no);

delete from users_tb where user_no = 6;

insert into users_tb(`user_id`, `user_password`, `user_name`)
value ("bbbb","3333","test_user_1");


select * from users_tb where user_id = "ppman";

update users_tb
set 
user_password = "aapa",
user_name = "hi_postman",
user_profile = null,
age_no = 4,
user_residence = null
where user_id = "ppman";


select * from user_refresh_token_tb where user_id = ;
drop table  user_refresh_token_tb;