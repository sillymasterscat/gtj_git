create database book;

use book;
-- 用户
create table t_user(
	`id` int primary key auto_increment,
	`username` varchar(20) not null unique,
	`password` varchar(32) not null,
	`email` varchar(200)
);

-- 书籍
create table t_book(
	`id` int primary key auto_increment,
	`name` varchar(100),
	`price` decimal(11,2),
	`author` varchar(100),
	`sales` int,
	`stock` int,
	`img_path` varchar(200)

);

-- 购物车
create table t_cart(
	`id` int primary key auto_increment,
	`totalPrice` decimal(11,2),
	`totalCount` int,
	`user_id` int not null,
	foreign key(`user_id`) references t_user(`id`)
);

create table t_cartItem(
	`id` int primary key auto_increment,
	`name` varchar(100),
	`count` int,
	`price` decimal(11,2),
	`totalPrice` decimal(11,2),
	`cart_id` int not null,
	foreign key(`cart_id`) references t_cart(`cart_id`)
);


-- 订单
create table t_order(
	`order_id` varchar(50) primary key,
	`create_time` datetime,
	`price` decimal(11,2),
	`status` int,
	`user_id` int not null,
	foreign key(`user_id`) references t_user(`id`)
);
create table t_order_item(
	`id` int primary key auto_increment,
	`name` varchar(100),
	`count` int,
	`price` decimal(11,2),
	`total_price` decimal(11,2),
	`order_id` varchar(50) not null,
	foreign key(`order_id`) references t_order(`order_id`)
);




