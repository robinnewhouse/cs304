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
	(1111,123,'callno1','copyno1','2013-01-11','2013-02-01');

insert into borrowing values 
	(1112,123,'callno2','copyno2','2013-03-31','2013-04-14');

insert into borrowing values 
	(1113,123,'callno3','copyno3','2012-11-11','2012-12-22');

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

insert into book values (
	'callno4' ,
	'isbn4',
	'title4',
	'mainauthor4',
	'publisher4',
	2004 );

insert into book_copy values (
	'callno1','copyno1','in');

insert into book_copy values (
	'callno2','copyno2','in');

insert into book_copy values (
	'callno3','copyno3','in');

insert into book_copy values (
	'callno4','copyno4','in');

commit;
