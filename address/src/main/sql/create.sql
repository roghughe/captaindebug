drop table addresses;

create table addresses (
	id integer unique,
	street varchar(50),
	town varchar(30),
	post_code varchar(12),
	country varchar(15)
);

insert into addresses (id,street,town,post_code,country)
  values(10000,'15 Cred Street','Anytown','DD3 TT5', 'England');

  
insert into addresses (id,street,town,post_code,country)
  values(9,'16 Cred Street','Anytown','DD3 TT5', 'England');
  
select * from addresses;

