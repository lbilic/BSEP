insert into authority (id, name) values (1, 'ADMIN');

INSERT INTO accounts (ID, USERNAME, PASSWORD, EMAIL, LAST_NAME, FIRST_NAME) VALUES (789, 'aa', '$2a$10$9AG/inYS7fWOHPhxQCtd0Og2gVYnbhwFBUsHaAOek5QAKSQtWItRC', 'kwhshocker@gmail.com', 'aa','aa');
INSERT INTO account_authority(ID, ACCOUNT_ID, AUTHORITY_ID) VALUES (54, 789, 1);

INSERT INTO city (id,has_cert,name_id) values (1,false,'Boston');
INSERT INTO city (id,has_cert,name_id) values (2,false,'London');
INSERT INTO city (id,has_cert,name_id) values (3,false,'Hong Kong');

INSERT INTO office (id,has_cert,name_id,city_id) values (1,false,'Office1',1);
INSERT INTO office (id,has_cert,name_id,city_id) values (2,false,'Office2',1);
INSERT INTO office (id,has_cert,name_id,city_id) values (3,false,'Office3',1);
INSERT INTO city_offices (city_id,offices_id) values (1,1);
INSERT INTO city_offices (city_id,offices_id) values (1,2);
INSERT INTO city_offices (city_id,offices_id) values (1,3);

INSERT INTO office (id,has_cert,name_id,city_id) values (4,false,'Office4',2);
INSERT INTO office (id,has_cert,name_id,city_id) values (5,false,'Office5',2);
INSERT INTO city_offices (city_id,offices_id) values (2,4);
INSERT INTO city_offices (city_id,offices_id) values (2,5);

INSERT INTO office (id,has_cert,name_id,city_id) values (6,false,'Office6',3);
INSERT INTO city_offices (city_id,offices_id) values (3,6);

INSERT INTO software (id,has_cert,name_id,office_id) values (1,false,'Software1',1);
INSERT INTO software (id,has_cert,name_id,office_id) values (2,false,'Software2',1);
INSERT INTO software (id,has_cert,name_id,office_id) values (3,false,'Software3',2);
INSERT INTO software (id,has_cert,name_id,office_id) values (4,false,'Software4',2);
INSERT INTO software (id,has_cert,name_id,office_id) values (5,false,'Software5',3);
INSERT INTO software (id,has_cert,name_id,office_id) values (6,false,'Software6',3);
INSERT INTO software (id,has_cert,name_id,office_id) values (7,false,'Software7',4);
INSERT INTO software (id,has_cert,name_id,office_id) values (8,false,'Software8',4);
INSERT INTO software (id,has_cert,name_id,office_id) values (9,false,'Software9',5);
INSERT INTO software (id,has_cert,name_id,office_id) values (10,false,'Software10',5);
INSERT INTO software (id,has_cert,name_id,office_id) values (11,false,'Software11',6);
INSERT INTO software (id,has_cert,name_id,office_id) values (12,false,'Software12',6);
INSERT INTO office_softwares (office_id,softwares_id) values (1,1);
INSERT INTO office_softwares (office_id,softwares_id) values (1,2);
INSERT INTO office_softwares (office_id,softwares_id) values (2,3);
INSERT INTO office_softwares (office_id,softwares_id) values (2,4);
INSERT INTO office_softwares (office_id,softwares_id) values (3,5);
INSERT INTO office_softwares (office_id,softwares_id) values (3,6);
INSERT INTO office_softwares (office_id,softwares_id) values (4,7);
INSERT INTO office_softwares (office_id,softwares_id) values (4,8);
INSERT INTO office_softwares (office_id,softwares_id) values (5,9);
INSERT INTO office_softwares (office_id,softwares_id) values (5,10);
INSERT INTO office_softwares (office_id,softwares_id) values (6,11);
INSERT INTO office_softwares (office_id,softwares_id) values (6,12);

INSERT INTO permission (id,name) values (500,'generateCertificate');
INSERT INTO permission (id,name) values (501,'getCertificate');
INSERT INTO permission (id,name) values (502,'revoke');
INSERT INTO permission (id,name) values (503,'addUser');
INSERT INTO permission (id,name) values (504,'removeUser');
INSERT INTO permission (id,name) values (505,'editUser');
INSERT INTO permission (id,name) values (506,'addRole');
INSERT INTO permission (id,name) values (507,'removeRole');
INSERT INTO permission (id,name) values (508,'removeRole');
INSERT INTO permission (id,name) values (509,'addPermission');
INSERT INTO permission (id,name) values (510,'removePermission');

INSERT INTO role (id,name) values (500,'system_admin');

INSERT INTO role_permissions (role_id,permissions_id) values (500,500);
INSERT INTO role_permissions (role_id,permissions_id) values (500,501);
INSERT INTO role_permissions (role_id,permissions_id) values (500,502);
INSERT INTO role_permissions (role_id,permissions_id) values (500,503);
INSERT INTO role_permissions (role_id,permissions_id) values (500,504);
INSERT INTO role_permissions (role_id,permissions_id) values (500,505);
INSERT INTO role_permissions (role_id,permissions_id) values (500,506);
INSERT INTO role_permissions (role_id,permissions_id) values (500,507);
INSERT INTO role_permissions (role_id,permissions_id) values (500,508);
INSERT INTO role_permissions (role_id,permissions_id) values (500,509);
INSERT INTO role_permissions (role_id,permissions_id) values (500,510);

INSERT INTO system_user (id,username) values (500,'aa');

INSERT INTO system_user_roles (system_user_id,roles_id) values (500,500);

