insert into authority (id, name) values (1, 'ADMIN');

INSERT INTO accounts (ID, USERNAME, PASSWORD, EMAIL, LAST_NAME, FIRST_NAME) VALUES (789, 'aa', '$2a$10$9AG/inYS7fWOHPhxQCtd0Og2gVYnbhwFBUsHaAOek5QAKSQtWItRC', 'kwhshocker@gmail.com', 'aa','aa');
INSERT INTO account_authority(ID, ACCOUNT_ID, AUTHORITY_ID) VALUES (54, 789, 1);