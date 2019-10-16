CREATE TABLE user_test(
	username varchar(16) PRIMARY KEY,
	password varchar(16),
	firstName varchar(30),
	lastName varchar(30),
	roles varchar[],
	reportsTo varchar(16)
);

CREATE TABLE reimbursement_test (
	reimbursment_id serial PRIMARY KEY,
	employee varchar(16),
	direct_sup varchar(16),
	dep_head varchar(16),
	ben_co varchar(16),
	email varchar(50),
	phone varchar(10),
	eventTime timestamp(0),
	submisionDate timestamp(0),
	location varchar(50),
	eventName varchar(50),
	eventType(50),
	description text,
	cost NUMERIC(6, 2),
	format_id integer, --foriegn key
	amount double precision(6,2)
	FOREIGN KEY (status_id) refrences status_table status_id
);
?
CREATE TABLE status_test(
	status_id serial PRIMARY KEY,
	direct_sup_status varchar(10),
	dep_head_status varchar(10),
	ben_co_status varchar(10),
	
);
?
CREATE TABLE grading_format_test(
	format_id serial PRIMARY KEY,
	format varchar(),
);
