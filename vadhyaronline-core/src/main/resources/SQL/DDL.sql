CREATE DATABASE vadhyaronline;
USE vadhyaronline;
GRANT SELECT, INSERT, UPDATE ON vadhyaronline.* TO dev;

-- DROP TABLE user_role;

CREATE TABLE user_role (
	role_id INT AUTO_INCREMENT,
  role_name VARCHAR(25),
  CONSTRAINT pk_user_role PRIMARY KEY (role_id),
  CONSTRAINT uk_user_role_name UNIQUE (role_name)
);
INSERT INTO user_role(role_name) VALUES ('Admin');
INSERT INTO user_role(role_name) VALUES ('Vadhyar');
INSERT INTO user_role(role_name) VALUES ('User');

CREATE TABLE language_supported (
	lang_id INT auto_increment,
    lang_key VARCHAR(5),
    lang_value VARCHAR(50),
    CONSTRAINT pk_language_supported PRIMARY KEY (lang_id),
    CONSTRAINT uk_lang_key UNIQUE (lang_key)
);
INSERT INTO language_supported(lang_key, lang_value) VALUES ('en', 'English');
INSERT INTO language_supported(lang_key, lang_value) VALUES ('tam', 'Tamil');
INSERT INTO language_supported(lang_key, lang_value) VALUES ('sans', 'Sanskrit');

CREATE TABLE event_category (
	event_category_id int auto_increment,
    event_category_name varchar(50),
    constraint pk_event_category primary key (event_category_id),
    constraint uk_event_category_name unique (event_category_name)
);
INSERT INTO event_category(event_category_name) VALUES ('Homa');
INSERT INTO event_category(event_category_name) VALUES ('Tharpanam');
INSERT INTO event_category(event_category_name) VALUES ('Devasam');
INSERT INTO event_category(event_category_name) VALUES ('Manthra');
INSERT INTO event_category(event_category_name) VALUES ('Shloka');

CREATE TABLE event_type (
  event_type_id INT AUTO_INCREMENT,
  event_category_id INT,
  event_type_name VARCHAR(50),
  event_type_desc VARCHAR(150),
  CONSTRAINT pk_event_type_id PRIMARY KEY (event_type_id),
  CONSTRAINT fk_event_type_category FOREIGN KEY (event_category_id) REFERENCES event_category(event_category_id),
  CONSTRAINT uk_event_type_name UNIQUE (event_type_name)
);
INSERT INTO event_type(event_category_id, event_type_name, event_type_desc) VALUES (1 , 'Sudharshana Homam', '');
INSERT INTO event_type(event_category_id, event_type_name, event_type_desc) VALUES (2 , 'Amavasya Tharpanam', '');
INSERT INTO event_type(event_category_id, event_type_name, event_type_desc) VALUES (3 , 'Pithru Tharpanam', '');
INSERT INTO event_type(event_category_id, event_type_name, event_type_desc) VALUES (3 , 'Mathru Tharpanam', '');
INSERT INTO event_type(event_category_id, event_type_name, event_type_desc) VALUES (4 , 'Sandiyavandanam', '');
INSERT INTO event_type(event_category_id, event_type_name, event_type_desc) VALUES (5 , 'Bhagavad Gita', '');

CREATE TABLE veda_master (
	veda_id int auto_increment,
    veda_name varchar(20),
    constraint pk_veda_master_id primary key (veda_id),
    constraint uk_veda_master_name unique (veda_name)
);
INSERT INTO veda_master (veda_name ) VALUES ('Rig Veda');
INSERT INTO veda_master (veda_name ) VALUES ('Yajur Veda');
INSERT INTO veda_master (veda_name ) VALUES ('Sama Veda');
INSERT INTO veda_master (veda_name ) VALUES ('Atharvana Veda');

CREATE TABLE soothram_master (
	soothram_id int auto_increment,
    soothram_name varchar(20),
    constraint pk_soothram_master_id primary key (soothram_id),
    constraint uk_soothram_master_name unique (soothram_name)
);
INSERT INTO soothram_master (soothram_name) VALUES ('Thrahyayana Soothram');
INSERT INTO soothram_master (soothram_name) VALUES ('Bhodhayana Soothram');
INSERT INTO soothram_master (soothram_name) VALUES ('Apasthamba Soothram');

CREATE TABLE gothram_master (
	gothram_id int auto_increment,
    gothram_name varchar(50),
    constraint pk_gothram_master_id primary key (gothram_id),
    constraint uk_gothram_master_name unique (gothram_name)
);
INSERT INTO gothram_master (gothram_name) VALUES ('Bharadwaja Gothram');
INSERT INTO gothram_master (gothram_name) VALUES ('Athreya Gothram');
INSERT INTO gothram_master (gothram_name) VALUES ('Vathula Gothram');