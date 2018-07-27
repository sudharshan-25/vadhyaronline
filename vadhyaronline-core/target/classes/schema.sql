CREATE TABLE user_role (
	role_id INT AUTO_INCREMENT,
  role_name VARCHAR(25),
  CONSTRAINT pk_user_role PRIMARY KEY (role_id),
  CONSTRAINT uk_user_role_name UNIQUE (role_name)
);
CREATE TABLE language_supported (
	lang_id INT auto_increment,
    lang_key VARCHAR(5),
    lang_value VARCHAR(50),
    CONSTRAINT pk_language_supported PRIMARY KEY (lang_id),
    CONSTRAINT uk_lang_key UNIQUE (lang_key)
);
CREATE TABLE event_category (
	event_category_id int auto_increment,
    event_category_name varchar(50),
    constraint pk_event_category primary key (event_category_id),
    constraint uk_event_category_name unique (event_category_name)
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
	veda_id int auto_increment,
    veda_name varchar(20),
    constraint pk_veda_master_id primary key (veda_id),
    constraint uk_veda_master_name unique (veda_name)
);
CREATE TABLE soothram_master (
	soothram_id int auto_increment,
    soothram_name varchar(20),
    constraint pk_soothram_master_id primary key (soothram_id),
    constraint uk_soothram_master_name unique (soothram_name)
);
CREATE TABLE gothram_master (
	gothram_id int auto_increment,
    gothram_name varchar(50),
    constraint pk_gothram_master_id primary key (gothram_id),
    constraint uk_gothram_master_name unique (gothram_name)
);