use penetration;

show tables;
desc users_tb;

select * from users_tb;

delete from users_tb where user_no = 6;

insert into users_tb(`user_id`, `user_password`, `user_name`)
value ("bbbb","3333","test_user_1");



drop table users_tb;