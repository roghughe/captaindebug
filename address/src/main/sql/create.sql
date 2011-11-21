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

create table properties (
	id integer primary key,
	propkey varchar(32) not null,
	propval varchar(255) not null,
	locale varchar(8) 
);

insert into properties (id,propkey,propval,locale) values(1,'key1','val1','');
insert into properties (id,propkey,propval,locale) values(2,'key2','val2','fr_FR');
insert into properties (id,propkey,propval,locale) values(3,'key3','val3','en_US');
insert into properties (id,propkey,propval,locale) values(4,'key4','val4','en_GB');
insert into properties (id,propkey,propval,locale) values(5,'key5','val5','');
insert into properties (id,propkey,propval,locale) values(6,'key6','val6','en_GB');
insert into properties (id,propkey,propval,locale) values(7,'key7','val7','');
insert into properties (id,propkey,propval,locale) values(8,'key8','val8','en_GB');
insert into properties (id,propkey,propval,locale) values(9,'key9','val9','');
insert into properties (id,propkey,propval,locale) values(10,'key10','val10','');
insert into properties (id,propkey,propval,locale) values(11,'key11','val11','en_US');
insert into properties (id,propkey,propval,locale) values(12,'key12','val12','fr_FR');

insert into properties (id,propkey,propval,locale) values(1,'key1','val1a','fr_FR');
insert into properties (id,propkey,propval,locale) values(2,'key2','val2a','en_US');
insert into properties (id,propkey,propval,locale) values(3,'key3','val3a','en_GB');
insert into properties (id,propkey,propval,locale) values(4,'key4','val4a','en_GB');
insert into properties (id,propkey,propval,locale) values(5,'key5','val5a','en_GB');
insert into properties (id,propkey,propval,locale) values(6,'key6','val6a','en_GB');
insert into properties (id,propkey,propval,locale) values(7,'key7','val7a','fr_FR');
insert into properties (id,propkey,propval,locale) values(8,'key8','val8a','fr_FR');
insert into properties (id,propkey,propval,locale) values(9,'key9','val9a','fr_FR');
insert into properties (id,propkey,propval,locale) values(10,'key10','val10a','fr_FR');
insert into properties (id,propkey,propval,locale) values(11,'key11','val11a','fr_FR');
insert into properties (id,propkey,propval,locale) values(12,'key12','val12a','en_GB');
