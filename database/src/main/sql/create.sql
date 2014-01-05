-- run a batch execute for this script
-- mysql --database=junit -u root -pexperience --host=localhost --line_numbers --force <path-to>/main/sql/create-tables.sql

-- This is just a test
SELECT version(), now();

-- Create the users table and index it.
CREATE  TABLE IF NOT EXISTS  users
(
  pk                  INTEGER       NOT NULL AUTO_INCREMENT,
  user_name           VARCHAR(80)   NOT NULL,
  first_name          VARCHAR(80)   NOT NULL,
  surname             VARCHAR(80)   NOT NULL,
  dob                 DATE          NOT NULL,
  PRIMARY KEY (pk),
  INDEX (surname,first_name)
);

-- Create a credit card table
CREATE  TABLE IF NOT EXISTS  credit_card
(
  pk                  INTEGER       NOT NULL AUTO_INCREMENT,
  user				  INTEGER		NOT NULL,
  card_number         VARCHAR(80)   NOT NULL,
  valid_from          DATE,
  valid_until         DATE			NOT NULL,
  cvv				  INTEGER 		NOT NULL,
  PRIMARY KEY (pk),
  FOREIGN KEY (user)
      REFERENCES users(pk)

);


-- Insert Test Users (un encrypted)
INSERT INTO users (user_name, first_name, surname, dob)
VALUES('jc1','Jim','Clarke','1974-01-12 00:00:00');

INSERT INTO users (user_name, first_name, surname, dob)
VALUES('samo','Sam','Smith','1990-01-12 00:00:00');
