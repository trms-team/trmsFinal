CREATE TABLE user_test(
	username varchar(16) PRIMARY KEY,
	password varchar(16),
	firstName varchar(30),
	lastName varchar(30),
	roles varchar[],
	reportsTo varchar(16)
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
	status_id integer,
	submission_date timestamp(0),
	FOREIGN KEY (employee_username) REFERENCES user_test (username),
	FOREIGN KEY (format_id) REFERENCES grading_format_test (format_id),
	FOREIGN KEY (status_id) references status_test (status_id)
);

CREATE TABLE status_test(
	status_id serial PRIMARY KEY,
	direct_sup_username varchar(16),
	dep_head_username varchar(16),
	ben_co_username varchar(16),
	direct_sup_status varchar(10),
	dep_head_status varchar(10),
	ben_co_status varchar(10),
	direct_sup_time timestamp(0),
	dep_head_time timestamp(0),
	ben_co_time timestamp(0),
	FOREIGN KEY (direct_sup_username) REFERENCES user_test (username),
	FOREIGN KEY (dep_head_username) REFERENCES user_test (username),
	FOREIGN KEY (ben_co_username) REFERENCES user_test (username)
);

CREATE TABLE grading_format_test(
	format_id serial PRIMARY KEY,
	format varchar(30)
);

create table file_table (
	file_id serial primary key,
	FOREIGN KEY (reimbursment_id) references reimbursement_test reimbursment_id,
	filename varchar(100),
	file_type varchar(3),
	file bytea
	);