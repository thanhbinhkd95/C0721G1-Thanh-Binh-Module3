CREATE DATABASE casestudy_furama_web;
USE casestudy_furama_web;
CREATE TABLE customer_type(
	customer_type_id int PRIMARY KEY,
    customer_type_name VARCHAR(50)
);

INSERT INTO customer_type
VALUES (1,"Diamond"),
		 (2,"Platinium"),
		 (3,"Gold"),
		 (4,"Silver"),
		 (5,"Member");

CREATE TABLE customer(
	customer_id int PRIMARY KEY AUTO_INCREMENT,
    customer_type_id int,
    customer_name varchar(50),
    customer_birthday date,
    customer_gender varchar(30),
    customer_id_card varchar(45),
    customer_phone varchar(45),
    customer_email varchar(45),
    customer_address varchar(45),
    foreign key (customer_type_id) references customer_type(customer_type_id)
    on DELETE CASCADE
);

INSERT INTO customer(customer_type_id,customer_name,customer_birthday,customer_gender,customer_id_card,customer_phone,customer_email,customer_address)
VALUES (1,"Van Hung","1990-12-03","Male","201210210","0905040506","anhtannguyen@gmail.com","Da Nang"),
		(2,"Lam Tac","1989-12-03","Famale","201210210","0905040506","lam@gmail.com","Quang Ngai"),
		(3,"Hoang Linh","1992-12-03","Famale","201210210","0905040506","linh@gmail.com","Hue"),
		(4,"Van An","2001-12-03","Male","201210210","0905040506","ann@gmail.com","Sai Gon");


CREATE TABLE service_type(
	service_type_id int PRIMARY key,
    service_type_name varchar(50)
);

INSERT INTO service_type
VALUES (1,"Villa"),(2,"House"),(3,"Room");


CREATE TABLE rent_type(
	rent_type_id int PRIMARY KEY,
    rent_type_name VARCHAR(45),
    rent_type_cost double
);

INSERT INTO rent_type
VALUES (1,"DAY",50000),(2,"WEEK","1200000"),(3,"MONTH",30000000),(4,"YEAH",600000000);

CREATE TABLE service(
	service_id int primary KEY AUTO_INCREMENT,
    service_name varchar(45),
    service_area int,
    service_cost double,
    service_max_people int,
    rent_type_id int,
    service_type_id int,
    strandard_room varchar(45),
    description_other_convenience varchar(45),
    pool_area double,
    number_of_floor int,
    FOREIGN KEY (rent_type_id) REFERENCES rent_type(rent_type_id),
    FOREIGN KEY (service_type_id) REFERENCES service_type(service_type_id)
    on DELETE CASCADE    
);

insert into service(service_name,service_area,service_cost,service_max_people,rent_type_id,service_type_id,strandard_room,description_other_convenience,pool_area,number_of_floor)
VALUES ("VIP",200,500000,20,1,2,"Mat Me","Tra Dao",300,2);


CREATE table `position`(
	position_id int primary key,
    position_name varchar(45)
);

create table education_degree(
	education_degree_id int PRIMARY key,
    education_degree_name varchar(45)
);

create table division(
	division_id int PRIMARY key,
    division_name varchar(45)
);	

create table employee(
	employee_id int PRIMARY key AUTO_INCREMENT,
    employee_name varchar(45),
    employee_birthday date,
    employee_id_card varchar(45),
    employee_salary double,
    employee_phone varchar(45),
    employee_email varchar(45),
    employee_address varchar(45),
    position_id int,
    education_degree_id int,
    division_id int,
	username varchar(255),
	FOREIGN KEY (position_id) REFERENCES `position`(position_id),
	FOREIGN KEY (education_degree_id) REFERENCES education_degree(education_degree_id),
	FOREIGN KEY (division_id) REFERENCES division(division_id),
	FOREIGN KEY (username) REFERENCES `user`(username)
    on DELETE CASCADE
);

create table `role` (
	role_id int PRIMARY key,
    role_name varchar(45)

);

create table user_role(
	role_id int,
    username varchar(255),
    FOREIGN KEY (role_id) REFERENCES `role`(role_id),
    FOREIGN KEY (username) REFERENCES `user`(username)
    on delete CASCADE
);
ALTER TABLE user_role
ADD PRIMARY KEY(role_id,username);

create table `user`(
	username varchar(255) PRIMARY KEY,
    `password` varchar(255)
);


create table contract(
	contract_id int primary key AUTO_INCREMENT,
    contract_start_date date,
    contract_end_date date,
    contract_deposit double,
    contract_total_money double,
    employee_id int,
    customer_id int,
    service_id int,
    foreign key (employee_id) references employee(employee_id),
    foreign key (customer_id) references customer(customer_id),
    foreign key (service_id) references service(service_id)
    on delete CASCADE
);

create table attach_service(
	attach_service_id int primary key,
    attach_service_name varchar(45),
    attach_service_cost double,
    attach_service_unit int,
    attach_service_status varchar(45)
    
	
);


create table contract_detail(
	contract_detail int primary key AUTO_INCREMENT,
    contract_id int,
    attach_service_id int,
    quantity int,
    foreign key (contract_id) references contract(contract_id),
    foreign key (attach_service_id) references attach_service(attach_service_id)
    on delete CASCADE   
);


select c.contract_id,cus.customer_id,cus.customer_name,c.contract_start_date,c.contract_end_date,att.attach_service_id,cd.quantity,att.attach_service_name
from customer cus join  contract c on cus.customer_id = c.customer_id
join contract_detail cd on c.contract_id = cd.contract_id
join attach_service att on att.attach_service_id = cd.attach_service_id;


CREATE view view_user as
SELECT u.username,u.`password`, r.role_id from `user` u left join user_role ur on u.username = ur.username
left join `role` r on r.role_id = ur.role_id; 

SELECT * from view_user where username = "anhtan" AND `password` = "123";


ALTER TABLE customer
ADD CONSTRAINT UNIQUE (customer_code);


ALTER TABLE service
ADD CONSTRAINT UNIQUE (service_code);


ALTER TABLE customer
CHANGE customer_birthday  customer_birthday varchar(45);

ALTER TABLE contract
CHANGE contract_start_date contract_start_date VARCHAR(45);

ALTER TABLE contract
CHANGE contract_end_date contract_end_date VARCHAR(45);