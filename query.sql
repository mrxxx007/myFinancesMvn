-- Create table User

create table user ( 
    id int not null auto_increment, 
    login varchar(25),
    balance double,
    password varchar(255),
    primary key(id)
);

-- Create table Category

create table category ( 
    id int not null auto_increment, 
    name varchar(50) not null,
    user_id int,
    foreign key (user_id)
    references user(id)
);

-- Create table Importancy

create table importancy ( 
    id int, 
    name varchar(50) not null
);

-- Create table note

create table note (
    id int not null auto_increment,
    amount double not null,
    category_id int not null,
    importancy_id int,
    notedate date not null,
    info varchar(100),
    user_id int,
    foreign key (user_id)
    references user(id),
    foreign key (category_id)
    references category(id),
    foreign key (importancy_id)
    references importancy(id)    
);

create index date_index on table note(notedate);

insert into user(login, password) values('test1','123');
insert into category(name, user_id) values('food', 1), ('transport',1), ('health',1),('clothers',1),('entertainment',1),('other',1)
insert into category(name, user_id) values('food', 1), ('transport',1), ('health',1),('entertainment',1),('other',1)

