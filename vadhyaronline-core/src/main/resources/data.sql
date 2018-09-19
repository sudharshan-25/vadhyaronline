INSERT INTO role_master(role_id, role_name) VALUES (1, 'Admin');
INSERT INTO role_master(role_id, role_name) VALUES (2, 'Vadhyar');
INSERT INTO role_master(role_id, role_name) VALUES (3, 'Assistant');
INSERT INTO role_master(role_id, role_name) VALUES (4, 'User');

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

INSERT INTO veda_master (veda_id, veda_name ) VALUES (1, 'Rig Veda');
INSERT INTO veda_master (veda_id, veda_name ) VALUES (2, 'Yajur Veda');
INSERT INTO veda_master (veda_id, veda_name ) VALUES (3, 'Sama Veda');
INSERT INTO veda_master (veda_id, veda_name ) VALUES (4, 'Atharvana Veda');

INSERT INTO soothram_master (soothram_name, approved, APPROVED_BY, REQUESTED_BY) VALUES ('Thrahyayana Soothram', 1, 1 ,2);
INSERT INTO soothram_master (soothram_name, approved, APPROVED_BY, REQUESTED_BY) VALUES ('Bhodhayana Soothram', 1, 1 ,2);
INSERT INTO soothram_master (soothram_name, approved, APPROVED_BY, REQUESTED_BY) VALUES ('Apasthamba Soothram', 1, 1 ,2);

INSERT INTO gothram_master (gothram_name, approved, APPROVED_BY, REQUESTED_BY) VALUES ('Bharadwaja Gothram', 1, 1, 2);
INSERT INTO gothram_master (gothram_name, approved, APPROVED_BY, REQUESTED_BY) VALUES ('Athreya Gothram', 1, 1, 2);
INSERT INTO gothram_master (gothram_name, approved, APPROVED_BY, REQUESTED_BY) VALUES ('Vathula- Gothram', 0, 1, 2);

INSERT INTO status_master (status_master_id, status_master_name) VALUES (1, 'Active');
INSERT INTO status_master (status_master_id, status_master_name) VALUES (2, 'Inactive');
INSERT INTO status_master (status_master_id, status_master_name) VALUES (3, 'Logged In');
INSERT INTO status_master (status_master_id, status_master_name) VALUES (4, 'Locked');

INSERT INTO user_master (user_first_name, user_last_name, user_name, user_email, user_mobile, user_password, user_role_id)
  VALUES ('Admin', '', 'admin', 'admin@vadhyar.in', '1234567890', 'password-123', 1);
INSERT INTO user_master (user_first_name, user_last_name, user_name, user_email, user_mobile, user_password, user_role_id)
  VALUES ('Vadhyar', '', 'vadhyar', 'vadhyar@vadhyar.in', '0123456789', 'password-123', 2);
INSERT INTO user_master (user_first_name, user_last_name, user_name, user_email, user_mobile, user_password, user_role_id)
  VALUES ('User', '', 'user', 'user@vadhyar.in', '123456789', 'password-123', 4);


INSERT INTO event_status (event_status_id, event_status_name) VALUES (1, 'Draft');
INSERT INTO event_status (event_status_id, event_status_name) VALUES (2, 'Requested');
INSERT INTO event_status (event_status_id, event_status_name) VALUES (3, 'Accepted');
INSERT INTO event_status (event_status_id, event_status_name) VALUES (4, 'Cancelled');
INSERT INTO event_status (event_status_id, event_status_name) VALUES (5, 'Completed');
