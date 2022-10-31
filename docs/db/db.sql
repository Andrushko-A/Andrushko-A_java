CREATE TABLE task_list (
	task_id INTEGER NOT NULL REFERENCES task(id),
	participant_id INTEGER NOT NULL REFERENCES participant(id),
	date datetime NOT NULL,
	date_of_correction datetime NOT NULL,
	status INTEGER NOT NULL,
	team_id INTEGER NOT NULL REFERENCES team(id)
);

CREATE TABLE task (
	id integer PRIMARY KEY AUTOINCREMENT,
	name VARCHAR NOT NULL,
	task_type_id INTEGER NOT NULL REFERENCES task_type(id)
);

CREATE TABLE participant (
	id integer PRIMARY KEY AUTOINCREMENT,
	name VARCHAR NOT NULL
);

CREATE TABLE task_type (
	id integer PRIMARY KEY AUTOINCREMENT,
	name VARCHAR NOT NULL,
	date_of_correction datetime NOTNULL
);

CREATE TABLE team (
	id integer PRIMARY KEY AUTOINCREMENT,
	name VARCHAR NOT NULL,
	number_of_part integer NOT NULL
);






