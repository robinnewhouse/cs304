insert into borrower values
	(123,
	'password',
	'name2',
	'address',
	5555555,
	'email', 
	111222333,
	'2020-01-01',
	'student' );

insert into borrower values
	(195,
	'password',
	'name2',
	'address',
	55551235,
	'email', 
	444555666,
	'2020-01-01',
	'faculty' );

insert into borrowing values 
	(1111,123,'callno1','copyno1','1111-11-11',null);

insert into borrowing values 
	(1112,123,'callno2','copyno2','1111-11-11',null);

insert into borrowing values 
	(1113,123,'callno3','copyno3','1111-11-11','1111-11-12');

insert into borrowing values 
	(1114,195,'callno4','copyno4','1111-11-11',null);


insert into book values (
	'callno1' ,
	'isbn1',
	'title1',
	'mainauthor1',
	'publisher1',
	2001 );

insert into book values (
	'callno2' ,
	'isbn2',
	'title2',
	'mainauthor2',
	'publisher2',
	2002 );

insert into book values (
	'callno3' ,
	'isbn3',
	'title3',
	'mainauthor3',
	'publisher3',
	2003 );

commit;