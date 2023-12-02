USE skillswap;

-- Inserting sample users
INSERT INTO users (username, password, name) VALUES ('apmurthy', 'password', 'Abi Murthy');
INSERT INTO users (username, password, name) VALUES ('johndoe', 'johndoe123', 'John Doe');
INSERT INTO users (username, password, name) VALUES ('janedoe', 'janedoe123', 'Jane Doe');

-- Inserting sample services
INSERT INTO services (name, description, user_id) VALUES ('Web Development', 'Offering full stack web development services.', 1);
INSERT INTO services (name, description, user_id) VALUES ('Graphic Design', 'Creative graphic design services for your business.', 2);
INSERT INTO services (name, description, user_id) VALUES ('Tutoring', 'Math and Science tutoring for high school students.', 3);

-- Inserting sample requests
-- User 1 (Abi Murthy) requests a service from User 2 (John Doe)
INSERT INTO requests (user_id, offered_service_id, requested_service_id, request_text, status) VALUES (1, 1, 2, 'Interested in your graphic design services for my web project.', 'pending');

-- User 2 (John Doe) requests a service from User 3 (Jane Doe)
INSERT INTO requests (user_id, offered_service_id, requested_service_id, request_text, status) VALUES (2, 2, 3, 'Looking for tutoring services for my niece in high school.', 'pending');

-- User 3 (Jane Doe) requests a service from User 1 (Abi Murthy)
INSERT INTO requests (user_id, offered_service_id, requested_service_id, request_text, status) VALUES (3, 3, 1, 'Need help with developing a website for online tutoring.', 'pending');


-- Inserting more sample users
INSERT INTO users (username, password, name) VALUES ('sarahsmith', 'sarahpass', 'Sarah Smith');
INSERT INTO users (username, password, name) VALUES ('mikeross', 'mikeross123', 'Mike Ross');
INSERT INTO users (username, password, name) VALUES ('emilyclark', 'emilyclark123', 'Emily Clark');

-- Inserting more sample services
INSERT INTO services (name, description, user_id) VALUES ('Photography', 'Professional event and portrait photography.', 4);
INSERT INTO services (name, description, user_id) VALUES ('Content Writing', 'High-quality content writing and blogging services.', 5);
INSERT INTO services (name, description, user_id) VALUES ('Music Lessons', 'Guitar and piano lessons for beginners and advanced students.', 6);

-- Inserting more sample requests to User 1 (Abi Murthy)
-- User 4 (Sarah Smith) requests a service from User 1
INSERT INTO requests (user_id, offered_service_id, requested_service_id, request_text, status) 
VALUES (4, 4, 1, 'Looking for a web developer to build an online portfolio for my photography business.', 'pending');

-- User 5 (Mike Ross) requests a service from User 1
INSERT INTO requests (user_id, offered_service_id, requested_service_id, request_text, status) 
VALUES (5, 5, 1, 'Need a website for my content writing services, focusing on SEO and user engagement.', 'pending');

-- User 6 (Emily Clark) requests a service from User 1
INSERT INTO requests (user_id, offered_service_id, requested_service_id, request_text, status) 
VALUES (6, 6, 1, 'Interested in creating a website to offer online music lessons.', 'pending');

-- Additional requests from existing users to User 1
-- User 2 (John Doe) makes another request to User 1
INSERT INTO requests (user_id, offered_service_id, requested_service_id, request_text, status) 
VALUES (2, 2, 1, 'Seeking a web development collaboration for a new design project.', 'pending');

-- User 3 (Jane Doe) makes another request to User 1
INSERT INTO requests (user_id, offered_service_id, requested_service_id, request_text, status) 
VALUES (3, 3, 1, 'Require technical assistance in setting up an educational platform.', 'pending');

USE skillswap;

INSERT INTO chatroom_messages (sender_id, message_text, timestamp) VALUES
(1, 'Hello everyone!', '2023-03-01 10:00:00'),
(2, 'Hi! How are you doing today?', '2023-03-01 10:05:00'),
(3, 'I am doing great, thanks for asking!', '2023-03-01 10:10:00'),
(1, 'What are you guys working on?', '2023-03-01 10:15:00'),
(2, 'Just going through some tutorials.', '2023-03-01 10:20:00'),
(3, 'I am planning to start a new project soon.', '2023-03-01 10:25:00');
