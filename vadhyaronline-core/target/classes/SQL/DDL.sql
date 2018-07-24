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

CREATE TABLE event_type (
	event_type_id INT AUTO_INCREMENT,
    event_type_key VARCHAR(50),
    CONSTRAINT pk_event_type PRIMARY KEY (event_type_id),
    CONSTRAINT uk_event_type_key UNIQUE (event_type_key)
);

CREATE TABLE event_type_name (
	event_name_id INT AUTO_INCREMENT,
    event_type_id INT,
    lang_id INT,
    event_name VARCHAR(100),
    CONSTRAINT pk_event_type_name PRIMARY KEY (event_name_id),
    CONSTRAINT fk_event_type_name FOREIGN KEY (event_type_id) REFERENCES event_type (event_type_id),
    CONSTRAINT fk_event_lang_id FOREIGN KEY (lang_id) REFERENCES language_supported (lang_id)
);
