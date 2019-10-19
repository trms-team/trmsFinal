insert into user_test values ('jboni', 'password', 'Jacob', 'Boni', array['DEPARTMENT_HEAD'], 'nickrulez');

insert into user_test values ('jane', 'password', 'Jane', 'Shin', array['DIRECT_SUPERVISOR', 'DEPARTMENT_HEAD'], 'nickrulez');

insert into user_test values ('nickrulez', 'password', 'Nick', 'Jurczak', array['BENCO'], null);

insert into user_test values ('shalom', 'password', 'Shalom', 'Nguyen', array['DIRECT_SUPERVISOR'], 'jboni');

insert into user_test values ('brian', 'password', 'Brian', 'Money', array['EMPLOYEE'], 'shalom');

insert into user_test values ('jeff', 'password', 'Jeff', 'Franken', array['EMPLOYEE'], 'jane');


insert into reimbursement_test values (1, 'brian', 'brian@gmail.com', '5555555555', '2019-10-01 17:05:00',
	'Tampa', 'History Class', 'UNIVERSITY_COURSE', 'I really really like history', 123.45, 1, 
	'History is cool', 5.50, 98.76, '2019-10-02 00:00:00', 'REJECTED', 'PENDING', 'PENDING',
	'History is lame and boring', '2019-10-02 00:01:00', null, null);

insert into reimbursement_test values (2, 'brian', 'brian@gmail.com', '5555555555', '2019-10-10 12:00:00',
	'Tampa', 'OCA', 'CERTIFICATION', 'I really really like Java', 245.00, 2, 
	'I need a job', 2.00, 245.00, '2019-10-12 00:00:00', 'ACCEPTED', 'ACCEPTED', 'ACCEPTED',
	null, '2019-10-15 16:24:00', '2019-10-17 12:01:00', '2019-10-17 16:30:00');


insert into reimbursement_test values (3, 'jeff', 'jeff@gmail.com', '1111111111', '2019-05-11 13:00:00',
	'Austin, TX', 'Dental Seminar', 'SEMINAR', 'I needed a new toothbrush', 597.00, 3, 
	'Dental hygiene is important', 24.00, 358.20, '2019-05-20 00:00:00', 'ACCEPTED', 'ACCEPTED', 'PENDING',
	null, '2019-05-25 12:14:00', '2019-05-25 12:14:00', null);



insert into grading_format_test values (1, 'A - F', 'F', 'C', 'A');

insert into grading_format_test values (2, '0 - 100', '0', '65', '100');

insert into grading_format_test values (3, 'PRESENTATION', 'Fail', 'Pass', 'Pass');

