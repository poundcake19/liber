drop table if exists article_version;
drop table if exists article;

create table article(
	id int not null auto_increment primary key, 
	name varchar(32) not null
);

create table article_version(
	id int not null auto_increment primary key, 
	article_id int not null, 
	version varchar(32) not null, 
	content longtext not null, 
	foreign key(article_id) references article(id)
);