drop table if exists article_version_tag;
drop table if exists article_version;
drop table if exists article;
drop table if exists tag;

create table tag(
	id int not null auto_increment primary key,
	name varchar(32) not null, 
	parent_tag_id int null, 
	path varchar(198) not null,
	foreign key(parent_tag_id) references tag(id)
);

create table article(
	id int not null auto_increment primary key, 
	name varchar(32) not null
);

create table article_version(
	id int not null auto_increment primary key, 
	article_id int not null, 
	content longtext not null, 
	foreign key(article_id) references article(id)
);

create table article_version_tag(
	id int not null auto_increment primary key, 
	article_version_id int not null, 
	tag_id int not null, 
	foreign key(article_version_id) references article_version(id), 
	foreign key(tag_id) references tag(id)
);