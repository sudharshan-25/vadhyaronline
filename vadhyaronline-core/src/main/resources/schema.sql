CREATE TABLE user_role (
	role_id INT,
  role_name VARCHAR(25),
  CONSTRAINT pk_user_role PRIMARY KEY (role_id),
  CONSTRAINT uk_user_role_name UNIQUE (role_name)
);
CREATE TABLE event_category (
	event_category_id INT AUTO_INCREMENT,
  event_category_name VARCHAR(50),
  approved bit default 0,
  requested_by int,
  approved_by int,
  CONSTRAINT pk_event_category PRIMARY KEY (event_category_id),
  CONSTRAINT uk_event_category_name UNIQUE (event_category_name)
);
CREATE TABLE event_type (
  event_type_id INT AUTO_INCREMENT,
  event_category_id INT,
  event_type_name VARCHAR(50),
  event_type_desc VARCHAR(150),
  approved bit default 0,
  requested_by int,
  approved_by int,
  CONSTRAINT pk_event_type_id PRIMARY KEY (event_type_id),
  CONSTRAINT fk_event_type_category FOREIGN KEY  (event_category_id) REFERENCES event_category(event_category_id),
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
  approved bit default 0,
  requested_by int,
  approved_by int,
  CONSTRAINT pk_soothram_master_id PRIMARY KEY (soothram_id),
  CONSTRAINT uk_soothram_master_name UNIQUE (soothram_name)
);
CREATE TABLE gothram_master (
	gothram_id INT AUTO_INCREMENT,
  gothram_name VARCHAR(50),
  approved bit default 0,
  requested_by int,
  approved_by int,
  CONSTRAINT pk_gothram_master_id PRIMARY KEY (gothram_id),
  CONSTRAINT uk_gothram_master_name UNIQUE (gothram_name)
);
CREATE TABLE status_master (
  status_master_id INT,
  status_master_name VARCHAR (50),
  CONSTRAINT pk_status_master_id primary key (status_master_id),
  CONSTRAINT uk_status_master_name unique (status_master_name)
);
CREATE TABLE user_master (
  user_id INT AUTO_INCREMENT,
  user_first_name VARCHAR(50),
  user_last_name VARCHAR (50),
  user_name VARCHAR (15) not null,
  user_email VARCHAR(75),
  user_mobile VARCHAR(13) not null,
  user_password VARCHAR(50) not null,
  user_role_id int not null,
  user_veda_id INT,
  user_soothram_id INT,
  user_gothram_id INT,
  CONSTRAINT pk_user_master_id PRIMARY KEY (user_id),
  CONSTRAINT uk_user_master_name UNIQUE (user_name),
  CONSTRAINT uk_user_mobile UNIQUE (user_mobile),
  CONSTRAINT fk_user_role_id FOREIGN KEY (user_role_id) REFERENCES user_role(role_id),
  CONSTRAINT fk_user_veda_id FOREIGN KEY  (user_veda_id) REFERENCES veda_master(veda_id),
  CONSTRAINT fk_user_soothram_id FOREIGN KEY  (user_soothram_id) REFERENCES soothram_master(soothram_id),
  CONSTRAINT fk_user_gothram_id FOREIGN KEY  (user_gothram_id) REFERENCES gothram_master(gothram_id),
);
CREATE TABLE user_login_status (
  user_id INT,
  login_status_id INT,
  latest_login_token varchar(300),
  last_successful_login DATETIME,
  login_failed_attempt SMALLINT,
  CONSTRAINT fk_user_login_id FOREIGN KEY (user_id) REFERENCES user_master(user_id),
  CONSTRAINT fk_user_login_status_id FOREIGN KEY (login_status_id) REFERENCES status_master (status_master_id)
);
CREATE TABLE vadhyar_event_preferences (
  preference_id INT AUTO_INCREMENT,
  vadhyar_id INT,
  event_type_id INT,
  CONSTRAINT pk_vadhyar_event_preferences PRIMARY KEY (preference_id),
  CONSTRAINT fk_vadhyar_event_preferences_user FOREIGN KEY (vadhyar_id) REFERENCES user_master(user_id),
  CONSTRAINT fk_vadhyar_event_preferences_type FOREIGN KEY (event_type_id) REFERENCES event_type(event_type_id),
  CONSTRAINT uk_vadhyar_event_preferences UNIQUE (vadhyar_id, event_type_id)
);
CREATE TABLE event_status(
  event_status_id int,
  event_status_name VARCHAR(50),
  CONSTRAINT pk_event_status PRIMARY KEY (event_status_id),
  CONSTRAINT uk_event_status UNIQUE (event_status_name)
);
CREATE TABLE event_master (
  event_id INT AUTO_INCREMENT,
  event_type INT,
  requested_by INT,
  requested_on DATETIME,
  event_date_time DATETIME,
  event_status_id int,
  vadhyar_by INT,
  CONSTRAINT pk_event_master_id PRIMARY KEY (event_id),
  CONSTRAINT fk_event_master_type FOREIGN KEY (event_type) REFERENCES event_type(event_type_id),
  CONSTRAINT fk_event_master_request FOREIGN KEY (requested_by) REFERENCES user_master(user_id),
  CONSTRAINT fk_event_master_status FOREIGN KEY (event_status_id) REFERENCES event_status(event_status_id),
  CONSTRAINT fk_event_master_vadhyar FOREIGN KEY (vadhyar_by) REFERENCES user_master(user_id)
);

