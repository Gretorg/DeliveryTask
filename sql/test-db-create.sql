drop database deliveryTest;
CREATE DATABASE deliveryTest
  CHARACTER SET utf8 COLLATE utf8_unicode_ci;
use deliveryTest;

create table if not exists status(
    status_id int,
    delivery_status varchar(30),
    primary key(status_id)
)ENGINE = InnoDB;

create table if not exists role(
    role_id int,
    role_description varchar(20),
    primary key(role_id)
)ENGINE = InnoDB;

create table if not exists cargo(
	cargo_id int auto_increment,
    weight int,
    length int,
    width int,
    height int,
    cargo_description varchar(100),
    primary key(cargo_id)
)ENGINE = InnoDB;

create table if not exists routes(
	route_id int auto_increment,
    city_from varchar(30),
    city_to varchar(30),
    route_distance int,
    primary key(route_id)
)ENGINE = InnoDB;


create table if not exists users(
	user_id int auto_increment,
    user_email varchar(40),
    user_password varchar(200),
    user_salt varchar(20),
    first_name varchar(20),
    last_name varchar(40),
    birth_date date,
    role_id int,
    foreign key (role_id) references role(role_id) on delete cascade,
    primary key(user_id)
)ENGINE = InnoDB;



create table if not exists delivery(
	delivery_id int auto_increment,
    cargo_id int,
    route_id int,
    user_id int,
    receiver_name varchar(20),
    receiver_last_name varchar(40),
    address varchar(50),
    send_date date,
    delivery_date date,
    price int,
    status_id int,
    foreign key (status_id) references status(status_id) on delete cascade,
    FOREIGN KEY (cargo_id) REFERENCES cargo(cargo_id) ON DELETE CASCADE,
    foreign key (route_id) references routes(route_id) on delete cascade,
    foreign key (user_id) references users(user_id) on delete cascade,
    primary key(delivery_id)
    
)ENGINE = InnoDB;

INSERT INTO role values(1,"manager");
INSERT INTO role values(2,"user");

INSERT INTO status values(1,"Заявка рассматривается");
INSERT INTO status values(2,"Ожидается оплата");
INSERT INTO status values(3,"Оплачено");


INSERT INTO users values(1,"admin@gmail.com",
    "B4BB0CED096BCBE452156EB8D26BDDA7C0DDE0A7F76F79C9D0456133C623CBBF","JVDJDOP",
    "admin","admin_last_name","1999-10-20",1);

INSERT INTO `routes` (`route_id`, `city_from`, `city_to`, `route_distance`) VALUES
(1, 'Одесса', 'Винница', 429),
(2, 'Одесса', 'Днепропетровск', 429),
(3, 'Одесса', 'Донецк', 713);
