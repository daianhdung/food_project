create database food_delivery;
use food_delivery;

create table user(
                     id int auto_increment,
                     email varchar(255),
                     password varchar(255),
                     fullname varchar(100),
                     token varchar(100),
                     type_token varchar(10),
                     phone_number varchar(12),
                     verify_code varchar(12),
                     verify_code_expired timestamp,
                     is_active boolean default false,
                     primary key(id)
);

create table user_detail(
                            id_user int,
                            address varchar(255),
                            mobile_phone varchar(12),
                            state varchar(255),
                            city varchar(255),
                            street varchar(255),

                            primary key(id_user),
                            foreign key(id_user) references user(id)
);

create table category(
                         id int auto_increment,
                         image text,
                         name varchar(50),

                         primary key(id)
);

create table restaurant(
                           id int auto_increment,
                           image text,
                           name varchar(100),

                           primary key(id)
);

create table restaurant_review(
                                  id int auto_increment,
                                  id_restaurant int,
                                  content text,
                                  rate float,

                                  primary key(id),
                                  foreign key(id_restaurant) references restaurant(id)
);

create table food(
                     id int auto_increment,
                     name varchar(255),
                     image text,
                     price int,
                     id_category int,
                     id_restaurant int,

                     primary key(id),
                     foreign key(id_category) references category(id),
                     foreign key(id_restaurant) references restaurant(id)
);

create table food_detail(
                            id_food int,
                            description text,
                            create_date timestamp,
                            rating float,

                            primary key(id_food),
                            foreign key(id_food) references food(id)
);


create table food_review(
                            id int auto_increment,
                            id_food int,
                            id_user int,
                            content text,
                            create_date timestamp default now(),
                            rate float,

                            primary key(id),
                            foreign key(id_food) references food(id),
                            foreign key(id_user) references user(id)
);

create table food_addon(
                           id int auto_increment,
                           name varchar(100),
                           image text,
                           price int,
                           id_food int,

                           primary key(id),
                           foreign key(id_food) references food(id)
);

create table material(
                         id int auto_increment,
                         name varchar(100),

                         primary key(id)
);

create table food_material(
                              id_food int,
                              id_material int,

                              primary key(id_food,id_material),
                              foreign key(id_food) references food(id),
                              foreign key(id_material) references material(id)
);

create table t_order(
                        id int auto_increment,
                        id_user int,
                        estimate_ship timestamp,
                        deliver_address text,

                        primary key(id),
                        foreign key(id_user) references user(id)
);

create table status(
                       id int auto_increment,
                       name varchar(50),

                       primary key(id)
);

create table order_status(
                             id_order int,
                             id_status int,

                             primary key(id_order,id_status),
                             foreign key(id_order) references t_order(id),
                             foreign key(id_status) references status(id)
);

create table food_order(
                           id_order int,
                           id_food int,
                           price float,
                           quality int,
                           id_promo int,

                           primary key(id_order,id_food),
                           foreign key(id_order) references t_order(id),
                           foreign key(id_food) references food(id)
);

create table user_favor(
                           id_user int,
                           id_food int,
                           is_favor varchar(10) default 'false',
                           primary key(id_user,id_food),
                           foreign key(id_user) references user(id),
                           foreign key(id_food) references food(id)
);

INSERT INTO category(image, name) VALUES ('mdi-fire', 'Popular');
INSERT INTO category(image, name) VALUES ('mdi-motorbike', 'FastDelivery');
INSERT INTO category(image, name) VALUES ('mdi-wallet-outline', 'High class');
INSERT INTO category(image, name) VALUES ('mdi-silverware-variant', 'Dine in');
INSERT INTO category(image, name) VALUES ('mdi-home-variant-outline', 'Pick up');
INSERT INTO category(image, name) VALUES ('mdi-map-outline', 'Nearest');

INSERT INTO food_delivery.`user` (email, password, fullname, phone_number) VALUES ('nguyenvana@gmail.com', '123456', 'Nguyen Van A', '09812345678');

INSERT INTO restaurant(image, name) VALUES ('burgerking.png', 'Buger King');
INSERT INTO restaurant(image, name) VALUES ('pizzahut.png', 'Pizza Hut');
INSERT INTO restaurant(image, name) VALUES ('kfc.png', 'KFC');
INSERT INTO restaurant(image, name) VALUES ('macd.png', 'Mac Donalds');
INSERT INTO restaurant(image, name) VALUES ('domino.png', 'Dominos');
INSERT INTO restaurant(image, name) VALUES ('subway.png', 'Subway');
INSERT INTO restaurant(image, name) VALUES ('test.png', 'Test');

INSERT INTO food(image, name) VALUES ('food3.jpg', 'Tandori');
INSERT INTO food(image, name) VALUES ('food4.jpg', 'Special Thaili');
INSERT INTO food(image, name) VALUES ('food5.jpg', 'Diet Food');
INSERT INTO food(image, name) VALUES ('food6.jpg', 'SandWich');
INSERT INTO food(image, name) VALUES ('food1.jpg', 'Spicy Na Thai Pizza');
INSERT INTO food(image, name) VALUES ('food2.jpg', 'Special Burger');
INSERT INTO food(image, name) VALUES ('test.png', 'Test');

INSERT INTO food_addon(name, price, id_food) VALUES ('Basmati rice', '3', 3);
INSERT INTO food_addon(name, price, id_food) VALUES ('Brown rice', '2', 3);
INSERT INTO food_addon(name, price, id_food) VALUES ('Bulgur pilaf', '4', 3);
INSERT INTO food_addon(name, price, id_food) VALUES ('Basmati rice', '3', 1);
INSERT INTO food_addon(name, price, id_food) VALUES ('Brown rice', '2', 1);
INSERT INTO food_addon(name, price, id_food) VALUES ('Bulgur pilaf', '4', 1);
INSERT INTO food_addon(name, price, id_food) VALUES ('Basmati rice', '3', 2);
INSERT INTO food_addon(name, price, id_food) VALUES ('Brown rice', '2', 2);
INSERT INTO food_addon(name, price, id_food) VALUES ('Bulgur pilaf', '4', 2);
INSERT INTO food_addon(name, price, id_food) VALUES ('Basmati rice', '3', 4);
INSERT INTO food_addon(name, price, id_food) VALUES ('Brown rice', '2', 4);
INSERT INTO food_addon(name, price, id_food) VALUES ('Bulgur pilaf', '4', 4);
INSERT INTO food_addon(name, price, id_food) VALUES ('Basmati rice', '3', 5);
INSERT INTO food_addon(name, price, id_food) VALUES ('Brown rice', '2', 5);
INSERT INTO food_addon(name, price, id_food) VALUES ('Bulgur pilaf', '4', 5);
INSERT INTO food_addon(name, price, id_food) VALUES ('Basmati rice', '3', 6);
INSERT INTO food_addon(name, price, id_food) VALUES ('Brown rice', '2', 6);
INSERT INTO food_addon(name, price, id_food) VALUES ('Bulgur pilaf', '4', 6);
