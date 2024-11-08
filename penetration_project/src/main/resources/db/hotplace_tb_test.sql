use penetration;

select * from hotplaces_tb;

select hotplace_no, hotplace_title, hotplace_text, hotplace_date, hotplace_views, user_no, user_name from hotplaces_tb left join users_tb using(user_no);
update hotplaces_tb set hotplace_views = hotplace_views + 1 where hotplace_no = 2;

select hotplace_no, hotplace_title, hotplace_text, hotplace_date, hotplace_views, user_no, user_name
        from hotplaces_tb
        left join users_tb 
        using(user_no)
        where hotplace_no = 2;
        
update hotplaces_tb set
hotplace_title = "테스트 글 수정입니다",
hotplace_text = "테스트 글 수정된 본문입니다",
hotplace_date = now()
where hotplace_no = 1;

insert into hotplaces_tb(`user_no`, `hotplace_title`,`hotplace_text`)
value
(2, '곧 삭제될 글입니다', "제 곧 내");

delete from hotplaces_tb where hotplace_no = 9;

drop table if exists hotplaces_tb;