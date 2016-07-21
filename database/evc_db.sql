DROP database if exists evc_db;
CREATE database evc_db;
Use evc_db;

set foreign_key_checks=0;

Create table if not exists users (
	id int not null auto_increment primary key,
    first_name nvarchar(32),
    last_name nvarchar(32),
    image nvarchar(32),
    main_email nvarchar(32),
    password nvarchar(64),
    phone nvarchar(32)
);

create table if not exists companies(
	id int not null auto_increment primary key,
    name text,
    email text,
    password text,
    address text, 
    phone text,
    logo text,
    background text
);

create table if not exists company_employees(
	id int not null auto_increment primary key,
    company_id int,
    user_id int,
    foreign key(company_id) references companies(id),
    foreign key(user_id) references users(id)
);

create table if not exists cards(
	id int not null auto_increment primary key,
    height int,
    width int
);

create table if not exists field_names(
	id int not null auto_increment primary key,
    name text
);

create table if not exists field_properties(
	id int not null auto_increment primary key,
    field_name_id int,
    card_id int,
    location_x int,
    location_y int,
    field_value text,
    foreign key(card_id) references cards(id),
    foreign key(field_name_id) references field_name(id)
);

create table if not exists company_cards(
	id int not null auto_increment primary key,
    company_id int,
    card_id int,
    foreign key(card_id) references cards(id),
    foreign key(company_id) references companies(id)
);

create table if not exists user_cards(
	id int not null auto_increment primary key,
    user_id int,
    card_id int,
    foreign key(user_id) references users(id),
    foreign key(card_id) references cards(id)
);

create table if not exists histories(
	id int not null auto_increment primary key,
    sender_id int,
    receiver_id int,
    card_id int,
    date date,
    foreign key(sender_id) references users(id),
    foreign key(receiver_id) references users(id),
    foreign key(card_id) references cards(id)
);


insert into users (first_name, last_name, main_email, password, phone)
values
('Anano', 'Bodokia', 'anano@gmail.com', '3242342342', '568989898'),
('Anna', 'Gorozia', 'anna@gmail.com', '1212121212', '595504124'),
('Mamuka', 'Sakhelashvili', 'mamuka@gmail.com', '5656565656', '555094043');

select * from users