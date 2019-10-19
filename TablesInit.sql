CREATE TABLE user_test(
	username varchar(16) PRIMARY KEY,
	password varchar(16),
	firstName varchar(30),
	lastName varchar(30),
	roles varchar[],
	reportsTo varchar(16)
);

CREATE TABLE grading_format_test(
	format_id serial PRIMARY KEY,
	format varchar(30),
	lowest varchar(20),
	passing varchar(20),
	highest varchar(20)
);

CREATE TABLE reimbursement_test (
	reimbursement_id serial PRIMARY KEY,
	employee_username varchar(16),
	email varchar(50),
	phone varchar(10),
	event_time timestamp(0),
	location varchar(50),
	event_name varchar(50),
	event_type varchar(50),
	description text,
	cost numeric(6, 2),
	format_id integer,
	work_related_just text,
	work_hours_missed numeric(6, 2),
	awarded_amount numeric(6, 2),
	submission_time timestamp(0),
	direct_sup_status varchar(10),
	dep_head_status varchar(10),
	ben_co_status varchar(10),
	rejected_reason text,
	direct_sup_time timestamp(0),
	dep_head_time timestamp(0),
	ben_co_time timestamp(0),
	FOREIGN KEY (employee_username) REFERENCES user_test (username),
	FOREIGN KEY (format_id) REFERENCES grading_format_test (format_id)
);

create table file_table (
	file_id serial primary key,
	reimbursement_id integer,
	filename varchar(100),
	file bytea,
	FOREIGN KEY (reimbursement_id) references reimbursement_test (reimbursement_id)
);


