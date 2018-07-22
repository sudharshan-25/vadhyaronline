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

