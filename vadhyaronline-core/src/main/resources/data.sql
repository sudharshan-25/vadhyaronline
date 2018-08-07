INSERT INTO user_role(role_id, role_name) VALUES (1, 'Admin');
INSERT INTO user_role(role_id, role_name) VALUES (2, 'Vadhyar');
INSERT INTO user_role(role_id, role_name) VALUES (3, 'User');

INSERT INTO event_category(event_category_name, approved) VALUES ('Homa', 1);
INSERT INTO event_category(event_category_name, approved) VALUES ('Tharpanam', 1);
INSERT INTO event_category(event_category_name, approved) VALUES ('Devasam', 1);
INSERT INTO event_category(event_category_name, approved) VALUES ('Manthra', 1);
INSERT INTO event_category(event_category_name, approved) VALUES ('Shloka', 1);

INSERT INTO event_type(event_category_id, event_type_name, event_type_desc, approved) VALUES (1 , 'Sudharshana Homam', '', 1);
INSERT INTO event_type(event_category_id, event_type_name, event_type_desc, approved) VALUES (2 , 'Amavasya Tharpanam', '', 1);
INSERT INTO event_type(event_category_id, event_type_name, event_type_desc, approved) VALUES (3 , 'Pithru Tharpanam', '', 1);
INSERT INTO event_type(event_category_id, event_type_name, event_type_desc, approved) VALUES (3 , 'Mathru Tharpanam', '', 0);
INSERT INTO event_type(event_category_id, event_type_name, event_type_desc, approved) VALUES (4 , 'Sandiyavandanam', '', 0);
INSERT INTO event_type(event_category_id, event_type_name, event_type_desc, approved) VALUES (5 , 'Bhagavad Gita', '', 1);

INSERT INTO veda_master (veda_name ) VALUES ('Rig Veda');
INSERT INTO veda_master (veda_name ) VALUES ('Yajur Veda');
INSERT INTO veda_master (veda_name ) VALUES ('Sama Veda');
INSERT INTO veda_master (veda_name ) VALUES ('Atharvana Veda');

INSERT INTO soothram_master (soothram_name) VALUES ('Thrahyayana Soothram');
INSERT INTO soothram_master (soothram_name) VALUES ('Bhodhayana Soothram');
INSERT INTO soothram_master (soothram_name) VALUES ('Apasthamba Soothram');

INSERT INTO gothram_master (gothram_name) VALUES ('Bharadwaja Gothram');
INSERT INTO gothram_master (gothram_name) VALUES ('Athreya Gothram');
INSERT INTO gothram_master (gothram_name) VALUES ('Vathula- Gothram');

INSERT INTO status_master (status_master_id, status_master_name) VALUES (1, 'Active');
INSERT INTO status_master (status_master_id, status_master_name) VALUES (2, 'Inactive');
INSERT INTO status_master (status_master_id, status_master_name) VALUES (3, 'Logged In');
INSERT INTO status_master (status_master_id, status_master_name) VALUES (4, 'Locked');

INSERT INTO user_master (user_first_name, user_last_name, user_name, user_email, user_mobile, user_password,
   user_role_id, user_veda_id, user_soothram_id, user_gothram_id)
  VALUES ('Admin', '', 'admin', 'sudharshan.srinivasan@outlook.in', '8695696274', 'password-123', 1, 3, 1, 1);

INSERT INTO user_login_status (user_id, login_status_id, last_successful_login, login_failed_attempt)
  VALUES (1, 1, null , 0);

UPDATE user_login_status SET latest_login_token = NULL WHERE 1 != 1;