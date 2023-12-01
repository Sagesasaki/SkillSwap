DROP DATABASE IF EXISTS skillswap;
CREATE DATABASE skillswap;
USE skillswap;

CREATE TABLE users (
  user_id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
  username VARCHAR(128) NOT NULL UNIQUE,
  password VARCHAR(128) NOT NULL,
  name VARCHAR(255) NOT NULL
);

CREATE TABLE services (
  service_id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
  name VARCHAR(256) NOT NULL,
  description VARCHAR(512) NOT NULL,
  user_id INT NOT NULL,
  FOREIGN KEY (user_id) REFERENCES users (user_id)
);

CREATE TABLE barters (
  is_complete BIT NOT NULL,
  service_id_1 INT NOT NULL,
  service_id_2 INT NOT NULL,
  FOREIGN KEY (service_id_1) REFERENCES services (service_id),
  FOREIGN KEY (service_id_2) REFERENCES services (service_id)
);

CREATE TABLE reviews (
  reviewer_id INT NOT NULL,
  reviewed_id INT NOT NULL,
  review_text VARCHAR(512) NOT NULL,
  FOREIGN KEY (reviewer_id) REFERENCES users (user_id),
  FOREIGN KEY (reviewed_id) REFERENCES users (user_id)
);

CREATE TABLE requests (
  request_id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
  user_id INT NOT NULL,
  offered_service_id INT NOT NULL,
  requested_service_id INT NOT NULL,
  request_text VARCHAR(512) NOT NULL,
  status VARCHAR(20) NOT NULL DEFAULT 'pending',
  FOREIGN KEY (user_id) REFERENCES users (user_id),
  FOREIGN KEY (offered_service_id) REFERENCES services (service_id),
  FOREIGN KEY (requested_service_id) REFERENCES services (service_id)
);

ALTER TABLE requests MODIFY COLUMN status ENUM('pending', 'accepted', 'declined') NOT NULL;
