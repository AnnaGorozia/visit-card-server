DROP database if exists evc_db;
CREATE database evc_db;
Use evc_db;

set foreign_key_checks=1;

Create table if not exists users (
	id int not null auto_increment primary key,
    first_name nvarchar(32),
    last_name nvarchar(32),
    image nvarchar(32),
    email nvarchar(32),
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
    foreign key(company_id) references companies(id) on delete cascade,
    foreign key(user_id) references users(id) on delete cascade
);

create table if not exists cards(
	id int not null auto_increment primary key,
    path text
);

create table if not exists templates(
    id int not null auto_increment primary key,
    width int,
    height int,
    owner text
);

create table if not exists company_templates(
    id int not null auto_increment primary key,
    company_id int,
    template_id int,
    foreign key(company_id) references companies(id),
    foreign key(template_id) references templates(id)
);

create table if not exists field_properties(
	id int not null auto_increment primary key,
    field_name_id int,
    template_id int,
    location_x int,
    location_y int,
    field_value text,
    field_color_id int,
    field_font_id int,
    font_size int,
    foreign key(template_id) references templates(id) on delete cascade
);

create table if not exists company_cards(
	id int not null auto_increment primary key,
    company_id int,
    card_id int,
    foreign key(card_id) references cards(id) on delete cascade,
    foreign key(company_id) references companies(id) on delete cascade
);

create table if not exists user_cards(
	id int not null auto_increment primary key,
    user_id int,
    card_id int,
    foreign key(user_id) references users(id) on delete cascade,
    foreign key(card_id) references cards(id) on delete cascade
);

create table if not exists histories(
	id int not null auto_increment primary key,
    sender_id int,
    receiver_id int,
    card_id int,
    date date,
    foreign key(sender_id) references users(id) on delete cascade,
    foreign key(receiver_id) references users(id) on delete cascade,
    foreign key(card_id) references cards(id) on delete cascade
);


insert into companies (name, email, password, address, phone)
values
('Redberry', 'redberry@gmail.com', 'anano@gmail.com', '3242342342', '568989898'),
('Azry', 'azry@gmail.com', 'anna@gmail.com', '1212121212', '595504124'),
('BOG', 'bog@gmail.com', 'mamuka@gmail.com', '5656565656', '555094043');

insert into users (first_name, last_name, email, password, phone)
values
('Anano', 'Bodokia', 'anano@gmail.com', '3242342342', '568989898'),
('Anna', 'Gorozia', 'anna@gmail.com', '1212121212', '595504124'),
('Mamuka', 'Sakhelashvili', 'mamuka@gmail.com', '5656565656', '555094043');


select * from users;


insert into templates(width, height, owner) values(50,50, 'user');


insert into templates(width, height, owner) values(50,50, 'Company');

insert into company_templates(company_id, template_id) values(1,1);

insert into field_properties(field_name_id, template_id, location_x, location_y, field_value,
                            field_color_id, field_font_id, font_size)
                            values(1, 1, 40, 40, "John", 1, 1, 17);

insert into cards(path) values('kakashi.png');
insert into user_cards(user_id, card_id) values(1,1);

insert into companies(name) values('ss');

insert into company_cards(company_id, card_id) values(1,1);


select * from users;