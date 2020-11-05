INSERT INTO employee (name, avatar, title, service, phone, dscr, is_Enabled) VALUES ('Katherine Rannelli', '/images/avatars/KatherineRannelli.jpeg', 'Owner', 'Hair Color, Cutting, Nails', '905-955-8040', '42yrs experience award winner 1989 in precision Hair Color and cutting, specializing in Aesthetics and nails. My mission is to help you feel the best you can. Life is fun.', TRUE);
INSERT INTO employee (name, avatar, title, service, phone, dscr, is_Enabled) VALUES ('Audrey Gebbie', '/images/avatars/AudreyGebbie.png', 'Colour Tech', 'Hair Color', '905-713-9122', '30+ experience, colour tech. Specialty with long hair.', TRUE);
INSERT INTO employee (name, avatar, title, service, phone, dscr, is_Enabled) VALUES ('Cindee', '/images/avatars/Cindee.jpg', 'Colour Tech', 'Hair Color, Cutting', '905-895-4247', '30 yrs experience. Specialist in curly hair, utilizing multiple cutting and colouring techniques to suit your hair type and skin tone. You are beautiful I am here to help you feel it!', FALSE);

INSERT INTO customer (name, phone) VALUES ('Test Customer', '123-456-7890');

INSERT INTO Account(email, encrypted_Password, enabled, is_Customer, is_Employee, is_Admin) VALUES ('admin@test.com', '$2a$10$1ltibqiyyBJMJQ4hqM7f0OusP6np/IHshkYc4TjedwHnwwNChQZCy', 1, FALSE, TRUE, TRUE);
INSERT INTO Account(email, encrypted_Password, enabled, is_Customer, is_Employee, is_Admin) VALUES ('e1@test.com', '$2a$10$1ltibqiyyBJMJQ4hqM7f0OusP6np/IHshkYc4TjedwHnwwNChQZCy', 1, FALSE, TRUE, FALSE);
INSERT INTO Account(email, encrypted_Password, enabled, is_Customer, is_Employee, is_Admin) VALUES ('customer@test.com', '$2a$10$1ltibqiyyBJMJQ4hqM7f0OusP6np/IHshkYc4TjedwHnwwNChQZCy', 1, TRUE, FALSE, FALSE);
INSERT INTO Account(email, encrypted_Password, enabled, is_Customer, is_Employee, is_Admin) VALUES ('e2@test.com', '$2a$10$1ltibqiyyBJMJQ4hqM7f0OusP6np/IHshkYc4TjedwHnwwNChQZCy', 1, FALSE, TRUE, FALSE);

INSERT INTO Role(rolename) VALUES('ROLE_ADMIN'); 
INSERT INTO Role(rolename) VALUES('ROLE_EMP');
INSERT INTO Role(rolename) VALUES('ROLE_USER');

INSERT INTO Account_Roles(accounts_id, roles_id) VALUES(1, 1); 
INSERT INTO Account_Roles(accounts_id, roles_id) VALUES(1, 2);  
INSERT INTO Account_Roles(accounts_id, roles_id) VALUES(1, 3);

INSERT INTO Account_Roles(accounts_id, roles_id) VALUES(2, 2); 
INSERT INTO Account_Roles(accounts_id, roles_id) VALUES(2, 3);

INSERT INTO Account_Roles(accounts_id, roles_id) VALUES(3, 3);

INSERT INTO Account_Roles(accounts_id, roles_id) VALUES(4, 3);

INSERT INTO Employee_Account(employee_id, account_id) VALUES(1, 1); 
INSERT INTO Employee_Account(employee_id, account_id) VALUES(2, 2); 
INSERT INTO Employee_Account(employee_id, account_id) VALUES(3, 4);

INSERT INTO Customer_Account(customer_id, account_id) VALUES(1, 3); 

INSERT INTO Service(name, description) VALUES ('Haircut', 'Haircut');
INSERT INTO Service(name, description) VALUES ('Hair Colour', 'Hair Colour');
INSERT INTO Service(name, description) VALUES ('Nails', 'Nails');

INSERT INTO Employee_Services(employees_id, services_id) VALUES(1, 1);
INSERT INTO Employee_Services(employees_id, services_id) VALUES(1, 2);
INSERT INTO Employee_Services(employees_id, services_id) VALUES(1, 3);

INSERT INTO Schedule(work_Date, start_Work_Time, end_Work_Time) VALUES('2020-10-31', '09:00', '17:00');
INSERT INTO Schedule(work_Date, start_Work_Time, end_Work_Time) VALUES('2020-12-31', '09:00', '17:00');

INSERT INTO Employee_Schedules(employees_id, schedules_id) VALUES(1, 1);
INSERT INTO Employee_Schedules(employees_id, schedules_id) VALUES(1, 2);

