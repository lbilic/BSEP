insert into authority (id, name) values (1, 'ADMIN');

INSERT INTO accounts (ID, USERNAME, PASSWORD, EMAIL, LAST_NAME, FIRST_NAME) VALUES (789, 'aa', '$2a$10$9AG/inYS7fWOHPhxQCtd0Og2gVYnbhwFBUsHaAOek5QAKSQtWItRC', 'kwhshocker@gmail.com', 'aa','aa');
INSERT INTO account_authority(ID, ACCOUNT_ID, AUTHORITY_ID) VALUES (54, 789, 1);

INSERT INTO certificate_node (id, alias, is_software) values (500, 'ROOT' , 0);
INSERT INTO certificate_node (id, alias, is_software) values (501, 'Boston' , 0);
INSERT INTO certificate_node (id, alias, is_software) values (502, 'London' , 0);
INSERT INTO certificate_node (id, alias, is_software) values (503, 'Hong Kong' , 0);
INSERT INTO certificate_node (id, alias, is_software) values (504, 'Siem center' , 1);
INSERT INTO certificate_node (id, alias, is_software) values (505, 'Siem agent1' , 1);
INSERT INTO certificate_node (id, alias, is_software) values (506, 'Siem agent2' , 1);

INSERT INTO certificate_node_children (certificate_node_id, children_id) values (500,501);
INSERT INTO certificate_node_children (certificate_node_id, children_id) values (500,502);
INSERT INTO certificate_node_children (certificate_node_id, children_id) values (500,503);
INSERT INTO certificate_node_children (certificate_node_id, children_id) values (503,504);
INSERT INTO certificate_node_children (certificate_node_id, children_id) values (503,505);
INSERT INTO certificate_node_children (certificate_node_id, children_id) values (503,506);

INSERT INTO certificate_node_connected_softwares (certificate_node_id, connected_softwares_id) values (504,505);
INSERT INTO certificate_node_connected_softwares (certificate_node_id, connected_softwares_id) values (505,504);
INSERT INTO certificate_node_connected_softwares (certificate_node_id, connected_softwares_id) values (505,506);
INSERT INTO certificate_node_connected_softwares (certificate_node_id, connected_softwares_id) values (506,505);


INSERT INTO permission (id,name) values (500,'/api/cert|POST');
INSERT INTO permission (id,name) values (501,'/api/cert|GET');
INSERT INTO permission (id,name) values (502,'/api/cert|DELETE');
INSERT INTO permission (id,name) values (503,'/api/cert/all-data|GET');
INSERT INTO permission (id,name) values (504,'/api/systemUser/user|PUT');
INSERT INTO permission (id,name) values (505,'/api/systemUser/user|DELETE');
INSERT INTO permission (id,name) values (506,'/api/systemUser/user|POST');
INSERT INTO permission (id,name) values (507,'/api/systemUser/user|GET');
INSERT INTO permission (id,name) values (508,'/api/systemUser/user/|GET');
INSERT INTO permission (id,name) values (509,'/api/systemUser/role|PUT');
INSERT INTO permission (id,name) values (510,'/api/systemUser/role|POST');
INSERT INTO permission (id,name) values (511,'/api/systemUser/role|DELETE');
INSERT INTO permission (id,name) values (512,'/api/systemUser/role|GET');
INSERT INTO permission (id,name) values (513,'/api/systemUser/role/|GET');
INSERT INTO permission (id,name) values (514,'/api/systemUser/permission|PUT');
INSERT INTO permission (id,name) values (515,'/api/systemUser/remove_permission|POST');
INSERT INTO permission (id,name) values (516,'/api/systemUser/permission|GET');
INSERT INTO permission (id,name) values (517,'/api/software/|GET');
INSERT INTO permission (id,name) values (518,'/api/software/|POST');

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
INSERT INTO role_permissions (role_id,permissions_id) values (500,511);
INSERT INTO role_permissions (role_id,permissions_id) values (500,512);
INSERT INTO role_permissions (role_id,permissions_id) values (500,513);
INSERT INTO role_permissions (role_id,permissions_id) values (500,514);
INSERT INTO role_permissions (role_id,permissions_id) values (500,515);
INSERT INTO role_permissions (role_id,permissions_id) values (500,516);
INSERT INTO role_permissions (role_id,permissions_id) values (500,517);
INSERT INTO role_permissions (role_id,permissions_id) values (500,518);

INSERT INTO system_user (id,username) values (500,'aa');

INSERT INTO system_user_roles (system_user_id,roles_id) values (500,500);

