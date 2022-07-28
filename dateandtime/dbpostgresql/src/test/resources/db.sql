CREATE TABLE person (
	id serial NOT NULL,
	name VARCHAR (50) NOT NULL,
	date_of_birth timestamp NOT NULL,
	PRIMARY KEY (id)
);
INSERT INTO person (id, name, date_of_birth) VALUES (1, 'fulan', TO_TIMESTAMP('2000-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'));