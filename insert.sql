insert into borrower values
	(bid.nextval,
	'e2n7',
	'Robin Newhouse',
	'123 abc street',
	7788380782,
	'email@address.com', 
	36106094,
	'2020-01-01',
	'student' );

insert into borrower values
	(bid.nextval,
	'cs304',
	'Laks',
	'UBC',
	55551235,
	'email', 
	1441,
	'2020-01-01',
	'faculty' );


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
	'callno1','copyno2','in');

insert into book_copy values (
	'callno1','copyno3','in');

insert into book_copy values (
	'callno1','copyno4','out');

insert into book_copy values (
	'callno2','copyno1','out');

insert into book_copy values (
	'callno2','copyno2','out');

insert into book_copy values (
	'callno3','copyno1','in');

insert into book_copy values (
	'callno4','copyno1','out');


insert into borrowing values 
	(101,101,'callno1','copyno1','2013-01-11','2013-02-01','in');

insert into borrowing values 
	(102,101,'callno2','copyno2','2013-03-31','2013-04-14','in');

insert into borrowing values 
	(103,101,'callno3','copyno3','2012-11-11','2012-12-22','out');

insert into borrowing values 
	(104,101,'callno4','copyno4','1111-11-11','2012-12-22','out');

insert into hold_request values
	(hid_counter.nextval, 101, 'callno1','2013-03-31') ;

insert into hold_request values
	(hid_counter.nextval, 101, 'callno2','2013-03-31') ;

insert into hold_request values
	(hid_counter.nextval, 101, 'callno3','2013-03-31') ;

insert into hold_request values
	(hid_counter.nextval, 101, 'callno1','2013-03-31') ;

insert into hold_request values
	(hid_counter.nextval, 102, 'callno1','2013-03-31') ;

insert into fine values
	(fine_counter.nextval, 100, '2013-03-31', null, 101) ;

insert into fine values
	(fine_counter.nextval, 100, '2013-03-31', null, 102);

insert into fine values
	(fine_counter.nextval, 100, '2013-03-31', null, 103);

insert into fine values
	(fine_counter.nextval, 100, '2013-03-31', '2013-04-01', 104);

commit;
