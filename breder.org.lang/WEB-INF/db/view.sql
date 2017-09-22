create or replace view lang_log_visit as 
	select currentDate 'date', 
		ip 'ip', 
		count(*) 'pages' 
	from lang_log
	group by ip
	order by id desc;

create or replace view lang_log_news as 
	select * from lang_log order by id desc;
	
create or replace view lang_log_news_ip as 
	select ln.* , li.username from lang_log_news ln, lang_ip li
	where ln.ip = li.ip
