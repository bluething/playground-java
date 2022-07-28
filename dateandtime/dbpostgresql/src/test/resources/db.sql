CREATE TABLE person (
	id serial NOT NULL,
	name VARCHAR (50) NOT NULL,
	date_of_birth timestamp NOT NULL,
	PRIMARY KEY (id)
);
INSERT INTO person (id, name, date_of_birth) VALUES (1, 'fulan', TO_TIMESTAMP('2000-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'));
INSERT INTO person (id, name, date_of_birth) VALUES (2, 'fulan' 2, TO_TIMESTAMP('2000-01-10 00:00:00', 'YYYY-MM-DD HH24:MI:SS'));
INSERT INTO person (id, name, date_of_birth) VALUES (3, 'fulan 3', TO_TIMESTAMP('2000-01-20 00:00:00', 'YYYY-MM-DD HH24:MI:SS'));
INSERT INTO person (id, name, date_of_birth) VALUES (4, 'fulan 4', TO_TIMESTAMP('2000-01-21 00:00:00', 'YYYY-MM-DD HH24:MI:SS'));
INSERT INTO person (id, name, date_of_birth) VALUES (5, 'fulan 5', TO_TIMESTAMP('2000-01-22 00:00:00', 'YYYY-MM-DD HH24:MI:SS'));
INSERT INTO person (id, name, date_of_birth) VALUES (6, 'fulan 6', TO_TIMESTAMP('2000-01-22 01:00:00', 'YYYY-MM-DD HH24:MI:SS'));
INSERT INTO person (id, name, date_of_birth) VALUES (7, 'fulan 7', TO_TIMESTAMP('2000-01-22 01:10:00', 'YYYY-MM-DD HH24:MI:SS'));
INSERT INTO person (id, name, date_of_birth) VALUES (8, 'fulan 8', TO_TIMESTAMP('2000-01-31 23:00:00', 'YYYY-MM-DD HH24:MI:SS'));