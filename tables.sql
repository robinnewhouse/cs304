drop table borrower;

create table borrower (
	bid int not null PRIMARY KEY,
	password varchar(15),
	name varchar(50),
	address varchar(25),
	phone int null,
	emailAddress varchar(50), 
	sinOrStNo int not null UNIQUE,
	expiryDate date,
	type varchar(15) );

drop sequence bid;

create sequence bid
minvalue 100 
start with 100;

drop table borrower_type;

create table borrower_type (
	type varchar(15) not null PRIMARY KEY,
	book_time_limit int);

drop table book cascade constraints;

create table book (
	call_number varchar(50) not null PRIMARY KEY,
	isbn varchar(25),
	title varchar(50),
	main_author varchar(50),
	publisher varchar(50),
	year int null );

drop table has_author;

create table has_author (
	call_number varchar(25) not null,
	name char(50) not null,
	PRIMARY KEY (call_number, name),
	foreign key (call_number) references book );

drop table has_subject;

create table has_subject (
	call_number varchar(25) not null,
	subject varchar(25) not null,
	PRIMARY KEY (call_number, subject),
	foreign key (call_number) references book );

drop table book_copy;

create table book_copy (
	call_number varchar(25) not null,
	copy_no varchar(25) not null,
	status char(10),
	PRIMARY KEY (call_number, copy_no),
	foreign key (call_number) references book );

drop table hold_request;

create table hold_request (
	hid varchar(20) not null PRIMARY KEY,
	bid int,
	call_number varchar(25),
	issuedDate date );

ALTER TABLE hold_request
ADD CONSTRAINT hold_constraint UNIQUE (bid,call_number);

drop sequence hid_counter;

create sequence hid_counter
minvalue 1000 
start with 1000;

drop table borrowing;

create table borrowing (
	borid int not null PRIMARY KEY,
	bid int,
	call_number varchar(25),
	copy_no varchar(25),
	outDate date,
	inDate date ,
	status varchar(10));
	
drop sequence borid_counter;

create sequence borid_counter
minvalue 1000 
start with 1000;

drop table fine;

create table fine (
	fid varchar(10) not null PRIMARY KEY,
	amount int null,
	issuedDate date,
	paidDate date,
	borid int );

drop sequence fine_counter;

create sequence fine_counter;

insert into borrower_type values
	('student', 2);

insert into borrower_type values
	('faculty', 12);

insert into borrower_type values
	('staff', 6);

commit;
