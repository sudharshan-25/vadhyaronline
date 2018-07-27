INSERT INTO user_role(role_name) VALUES ('Admin');
INSERT INTO user_role(role_name) VALUES ('Vadhyar');
INSERT INTO user_role(role_name) VALUES ('User');

INSERT INTO language_supported(lang_key, lang_value) VALUES ('en', 'English');
INSERT INTO language_supported(lang_key, lang_value) VALUES ('tam', 'Tamil');
INSERT INTO language_supported(lang_key, lang_value) VALUES ('sans', 'Sanskrit');

INSERT INTO event_category(event_category_name) VALUES ('Homa');
INSERT INTO event_category(event_category_name) VALUES ('Tharpanam');
INSERT INTO event_category(event_category_name) VALUES ('Devasam');
INSERT INTO event_category(event_category_name) VALUES ('Manthra');
INSERT INTO event_category(event_category_name) VALUES ('Shloka');

INSERT INTO event_type(event_category_id, event_type_name, event_type_desc) VALUES (1 , 'Sudharshana Homam', '');
INSERT INTO event_type(event_category_id, event_type_name, event_type_desc) VALUES (2 , 'Amavasya Tharpanam', '');
INSERT INTO event_type(event_category_id, event_type_name, event_type_desc) VALUES (3 , 'Pithru Tharpanam', '');
INSERT INTO event_type(event_category_id, event_type_name, event_type_desc) VALUES (3 , 'Mathru Tharpanam', '');
INSERT INTO event_type(event_category_id, event_type_name, event_type_desc) VALUES (4 , 'Sandiyavandanam', '');
INSERT INTO event_type(event_category_id, event_type_name, event_type_desc) VALUES (5 , 'Bhagavad Gita', '');

INSERT INTO veda_master (veda_name ) VALUES ('Rig Veda');
INSERT INTO veda_master (veda_name ) VALUES ('Yajur Veda');
INSERT INTO veda_master (veda_name ) VALUES ('Sama Veda');
INSERT INTO veda_master (veda_name ) VALUES ('Atharvana Veda');

INSERT INTO soothram_master (soothram_name) VALUES ('Thrahyayana Soothram');
INSERT INTO soothram_master (soothram_name) VALUES ('Bhodhayana Soothram');
INSERT INTO soothram_master (soothram_name) VALUES ('Apasthamba Soothram');

INSERT INTO gothram_master (gothram_name) VALUES ('Bharadwaja Gothram');
INSERT INTO gothram_master (gothram_name) VALUES ('Athreya Gothram');
INSERT INTO gothram_master (gothram_name) VALUES ('Vathula Gothram');