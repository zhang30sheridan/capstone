INSERT INTO employee (name, avatar, title, services, phone, dscr, enabled) VALUES ('Katherine Rannelli', '/images/avatars/KatherineRannelli.jpeg', 'Owner', 'Hair Color, Cutting, Nails', '905-955-8040', '42yrs experience award winner 1989 in precision Hair Color and cutting, specializing in Aesthetics and nails. My mission is to help you feel the best you can. Life is fun.', 1);
INSERT INTO employee (name, avatar, title, services, phone, dscr, enabled) VALUES ('Audrey Gebbie', '/images/avatars/AudreyGebbie.png', 'Colour Tech', 'Hair Color', '905-713-9122', '30+ experience, colour tech. Specialty with long hair.', 1);

INSERT INTO customer (name, phone) VALUES ('Test Customer', '123-456-7890');

INSERT INTO Account(email, encrypted_Password, enabled) VALUES ('admin@test.com', '$2a$10$1ltibqiyyBJMJQ4hqM7f0OusP6np/IHshkYc4TjedwHnwwNChQZCy', 1);
INSERT INTO Account(email, encrypted_Password, enabled) VALUES ('e1@test.com', '$2a$10$1ltibqiyyBJMJQ4hqM7f0OusP6np/IHshkYc4TjedwHnwwNChQZCy', 1);
INSERT INTO Account(email, encrypted_Password, enabled) VALUES ('customer@test.com', '$2a$10$1ltibqiyyBJMJQ4hqM7f0OusP6np/IHshkYc4TjedwHnwwNChQZCy', 1);

INSERT INTO Role(rolename) VALUES('ROLE_ADMIN'); 
INSERT INTO Role(rolename) VALUES('ROLE_EMP');
INSERT INTO Role(rolename) VALUES('ROLE_USER');

INSERT INTO Account_Roles(accounts_id, roles_id) VALUES(1, 1); 
INSERT INTO Account_Roles(accounts_id, roles_id) VALUES(1, 2);  
INSERT INTO Account_Roles(accounts_id, roles_id) VALUES(1, 3);

INSERT INTO Account_Roles(accounts_id, roles_id) VALUES(2, 2); 
INSERT INTO Account_Roles(accounts_id, roles_id) VALUES(2, 3);

INSERT INTO Account_Roles(accounts_id, roles_id) VALUES(3, 3);

INSERT INTO Employee_Account(employee_id, account_id) VALUES(1, 1); 
INSERT INTO Employee_Account(employee_id, account_id) VALUES(2, 2); 

INSERT INTO Customer_Account(customer_id, account_id) VALUES(1, 3); 