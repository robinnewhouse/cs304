drop table borrower;

create table borrower (
	bid char(12) not null PRIMARY KEY,
	password varchar(15),
	name varchar(50),
	address varchar(25),
	phone int null,
	emailAddress varchar(50), 
	sinOrStNo int null,
	expiryDate date,
	type varchar(15) );

drop table borrower_type;

create table borrower_type (
	type varchar(15) not null PRIMARY KEY,
	book_time_limit int null );

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

drop sequence copy_number;

create sequence copy_number
minvalue 0 
start with 0;
	

drop table hold_request;

create table hold_request (
	hid varchar(20) not null PRIMARY KEY,
	bid varchar(12),
	call_number varchar(25),
	issuedDate date );

drop table borrowing;

create table borrowing (
	borid varchar(20) not null PRIMARY KEY,
	bid varchar(12),
	call_number varchar(25),
	copy_no varchar(25),
	outDate date,
	inDate date );

drop table fine;

create table fine (
	fid varchar(10) not null PRIMARY KEY,
	amount int null,
	issuedDate date,
	paidDate date,
	borid varchar(20) );

insert into borrower_type values
	('student', 2);

insert into borrower_type values
	('faculy', 12);

insert into borrower_type values
	('staff', 6);

