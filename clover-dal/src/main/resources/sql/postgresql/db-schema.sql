drop table lop_test;
drop table lop_sys_user;
drop table lop_demo_version;
drop table lop_bank;

create table lop_bank  ( 
	id         	varchar(255) not null,
	asset      	float8 null,
	code       	varchar(255) null,
	create_time	timestamp null,
	name       	varchar(255) null,
	remark     	varchar(255) null,
	primary key(id)
);

create table lop_demo_version  ( 
	id     	varchar(255) not null,
	name   	varchar(255) null,
	remark 	varchar(255) null,
	version	int4 null,
	primary key(id)
);

create table lop_sys_user  ( 
	id         	varchar(255) not null,
	description	varchar(255) null,
	email      	varchar(255) null,
	login_name 	varchar(255) null,
	mobile     	varchar(255) null,
	password   	varchar(255) null,
	real_name  	varchar(255) null,
	status     	varchar(255) null,
	primary key(id)
);

create table lop_test  ( 
	id         	varchar(255) not null,
	age        	int4 null,
	asset      	float8 null,
	crate_time 	timestamp null,
	description	varchar(255) null,
	name       	varchar(255) null,
	sex        	varchar(255) null,
	primary key(id)
);