drop table users;

CREATE TABLE IF NOT EXISTS users (
	id integer UNIQUE,
	name varchar(50),
	email varchar(50),
    createdDate TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);


INSERT INTO users (id,name,email) 
	VALUES(
	1,'Tom', 'tom@gmail.com'
	);
	
INSERT INTO users (id,name,email) 
	VALUES(
	2,'Dick', 'dick@gmail.com'
	);

INSERT INTO users (id,name,email) 
	VALUES(
	3,'Harry', 'harry@gmail.com'
	);

	