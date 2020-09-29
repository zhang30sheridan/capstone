INSERT INTO employee (name, avatar, title, services, phone, dscr) VALUES ('Katherine Rannelli', '/images/avatars/KatherineRannelli.jpeg', 'Owner', 'Hair Color, Cutting, Nails', '905-955-8040', '42yrs experience award winner 1989 in precision Hair Color and cutting, specializing in Aesthetics and nails. My mission is to help you feel the best you can. Life is fun.');
INSERT INTO employee (name, avatar, title, services, phone, dscr) VALUES ('Audrey Gebbie', '/images/avatars/AudreyGebbie.png', 'Colour Tech', 'Hair Color', '905-713-9122', '30+ experience, colour tech. Specialty with long hair.');
INSERT INTO employee (name, avatar, title, services, phone, dscr) VALUES ('Cindee', '/images/avatars/Cindee.jpg', 'Colour Tech', 'Hair Color, Cutting', '905-895-4247', '30 yrs experience. Specialist in curly hair, utilizing multiple cutting and colouring techniques to suit your hair type and skin tone. You are beautiful I am here to help you feel it!');
INSERT INTO employee (name, avatar, title, services, phone, dscr) VALUES ('Cindy', '/images/avatars/Cindy.png', 'Colour Tech', 'Hair Color', '905-868-2181', '20 yrs, specializing in colour, highlights, difficult and challenging hair texture.');
INSERT INTO employee (name, avatar, title, services, phone, dscr) VALUES ('Minoo', '/images/avatars/Minoo.png', 'Registered Nurse', 'Botox and Fillers, Medical Therapist, Body Shape RF, Skin Lifting & Rejuvenation, Body Contouring, Cellulite Improvement', '416-832-1249', '15 years nursing experience, Certified Botox and Fillers, medical therapist, experience at hospital and cosmetic clinic. Body Shape RF, Skin Lifting & Rejuvenation, Body Contouring, Cellulite Improvement');
INSERT INTO employee (name, avatar, title, services, phone, dscr) VALUES ('Maggie', '/images/avatars/Maggie.jpg', 'Colour Tech', 'Hair Color, Hair Extensions', '289-995-2346', '17 yrs experience, specialist in colour, hair extensions, and smoothing system');
INSERT INTO employee (name, avatar, title, services, phone, dscr) VALUES ('Sumaira Hussain', '/images/avatars/SumairaHussain.jpg', 'Hair Stylist', 'Balayage, Cutting', '905-955-8040, 905-598-5806', 'Specialize in Balayage & Men’s fade hair cuts.');
INSERT INTO employee (name, avatar, title, services, phone, dscr) VALUES ('Maryam', '/images/avatars/Maryam.jpg', 'Medical Skin Care Specialist', 'Medical Skin Care, Body Contouring, Skin Tightening', '647-545-7674', '14yrs specializing in medical skin care, body contouring and skin tightening with the latest technique.');
INSERT INTO employee (name, avatar, title, services, phone, dscr) VALUES ('Megean', '/images/avatars/Megean.jpg', 'Colour Tech', 'Hair Color, Highlights', '905-251-7173', '10 Years experience, specializing in colour and highlights.');
INSERT INTO employee (name, avatar, title, services, phone, dscr) VALUES ('Ellie', '/images/avatars/Ellie.jpg', 'Beauty Tech', 'Eye Lash Extensions, Microblading, Permanent Makeup and Tattoo', '647-637-7131', 'Specializing in eye lash extensions, Microblading, permanent makeup and tattoo.');

INSERT INTO Account(email, encrypted_Password, enabled) VALUES ('test@test.com', '$2a$10$1ltibqiyyBJMJQ4hqM7f0OusP6np/IHshkYc4TjedwHnwwNChQZCy', 1);

INSERT INTO Role(rolename) VALUES('ROLE_ADMIN'); 
INSERT INTO Role(rolename) VALUES('ROLE_USER');
INSERT INTO Role(rolename) VALUES('ROLE_EMP');

INSERT INTO Account_Roles(accounts_id, roles_id) VALUES(1, 1); 
INSERT INTO Account_Roles(accounts_id, roles_id) VALUES(1, 2); 

INSERT INTO Employee_Account(employee_id, account_id) VALUES(1, 1); 