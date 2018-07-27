CREATE TABLE user_role (
	role_id INT AUTO_INCREMENT,
  role_name VARCHAR(25),
  CONSTRAINT pk_user_role PRIMARY KEY (role_id),
  CONSTRAINT uk_user_role_name UNIQUE (role_name)
);
CREATE TABLE language_supported (
	lang_id INT AUTO_INCREMENT,
  lang_key VARCHAR(5),
  lang_value VARCHAR(50),
  CONSTRAINT pk_language_supported PRIMARY KEY (lang_id),
  CONSTRAINT uk_lang_key UNIQUE (lang_key)
);
CREATE TABLE event_category (
	event_category_id INT AUTO_INCREMENT,
  event_category_name VARCHAR(50),
  CONSTRAINT pk_event_category PRIMARY KEY (event_category_id),
  CONSTRAINT uk_event_category_name UNIQUE (event_category_name)
);
CREATE TABLE event_type (
  event_type_id INT AUTO_INCREMENT,
  event_category_id INT,
  event_type_name VARCHAR(50),
  event_type_desc VARCHAR(150),
  CONSTRAINT pk_event_type_id PRIMARY KEY (event_type_id),
  CONSTRAINT fk_event_type_category FOREIGN KEY (event_category_id) REFERENCES event_category(event_category_id),
  CONSTRAINT uk_event_type_name UNIQUE (event_type_name)
);
CREATE TABLE veda_master (
	veda_id INT AUTO_INCREMENT,
  veda_name VARCHAR(20),
  CONSTRAINT pk_veda_master_id PRIMARY KEY (veda_id),
  CONSTRAINT uk_veda_master_name UNIQUE (veda_name)
);
CREATE TABLE soothram_master (
	soothram_id INT AUTO_INCREMENT,
  soothram_name VARCHAR(20),
  CONSTRAINT pk_soothram_master_id PRIMARY KEY (soothram_id),
  CONSTRAINT uk_soothram_master_name UNIQUE (soothram_name)
);
CREATE TABLE gothram_master (
	gothram_id INT AUTO_INCREMENT,
  gothram_name VARCHAR(50),
  CONSTRAINT pk_gothram_master_id PRIMARY KEY (gothram_id),
  CONSTRAINT uk_gothram_master_name UNIQUE (gothram_name)
);
CREATE TABLE user_master (
  user_id int AUTO_INCREMENT,
  user_first_name varchar(50),
  user_last_name varchar (50),
  user_name varchar (15) not null,
  user_email varchar(75),
  user_mobile varchar(13) not null,
  user_password varchar(50) not null,
  user_veda_id int,
  user_soothram_id int,
  user_gothram_id int,
  CONSTRAINT pk_user_master_id primary key (user_id),
  constraint uk_user_master_name unique (user_name),
  constraint uk_user_mobile unique (user_mobile),
  constraint uk_user_password unique (user_password),
  constraint fk_user_veda_id foreign key (user_veda_id) references veda_master(veda_id),
  constraint fk_user_soothram_id foreign key (user_soothram_id) references soothram_master(soothram_id),
  constraint fk_user_gothram_id foreign key (user_gothram_id) references gothram_master(gothram_id),
);

