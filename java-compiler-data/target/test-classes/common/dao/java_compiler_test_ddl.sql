DROP TABLE IF EXISTS users CASCADE;
DROP TABLE IF EXISTS roles CASCADE;
DROP TABLE IF EXISTS files CASCADE;
DROP TABLE IF EXISTS files_comments CASCADE;
DROP TABLE IF EXISTS file_statuses CASCADE;
DROP TABLE IF EXISTS file_visibility CASCADE;
DROP TABLE IF EXISTS comments CASCADE;

CREATE TABLE users (
	user_id bigserial NOT NULL,
	username varchar(50) NOT NULL,
	password varchar(200) NOT NULL,
	role_id bigint NOT NULL
);

CREATE TABLE roles (
	role_id bigserial NOT NULL,
	name varchar(50) NOT NULL
);

CREATE TABLE files (
	file_id bigserial NOT NULL,
	filename varchar(50) NOT NULL,
	file_status_id bigint NOT NULL,
	file_visibility_id bigint NOT NULL,
	user_id bigint NOT NULL,
	creation_time TIMESTAMP not NULL,
	description varchar(200)
);

CREATE TABLE file_statuses (
	file_status_id bigserial NOT NULL,
	name varchar(50) NOT NULL
);

CREATE TABLE file_visibility (
	file_visibility_id bigserial NOT NULL,
	name varchar(50) NOT NULL
);

CREATE TABLE comments (
	comment_id bigserial NOT NULL,
	comment_text text NOT NULL,
	user_id bigint NOT NULL,
	timePlaced timestamp NOT NULL,
	file_id bigint NOT NULL
);

ALTER TABLE users
	ADD CONSTRAINT UQ_users_username UNIQUE (username);


ALTER TABLE users
	ADD CONSTRAINT PK_users PRIMARY KEY (user_id);

ALTER TABLE roles
	ADD CONSTRAINT PK_roles PRIMARY KEY (role_id);	

ALTER TABLE files
	ADD CONSTRAINT PK_files PRIMARY KEY (file_id);

ALTER TABLE file_statuses
	ADD CONSTRAINT PK_files_statuses PRIMARY KEY (file_status_id);

ALTER TABLE file_visibility
	ADD CONSTRAINT PK_files_visibility PRIMARY KEY (file_visibility_id);

ALTER TABLE comments
	ADD CONSTRAINT PK_comments PRIMARY KEY (comment_id);

ALTER TABLE users ADD CONSTRAINT FK_users_roles
	FOREIGN KEY (role_id) REFERENCES roles (role_id);

ALTER TABLE files ADD CONSTRAINT FK_files_files_status
	FOREIGN KEY (file_status_id) REFERENCES file_statuses (file_status_id);

ALTER TABLE files ADD CONSTRAINT FK_files_files_visibility
	FOREIGN KEY (file_visibility_id) REFERENCES file_visibility (file_visibility_id);

ALTER TABLE files ADD CONSTRAINT FK_files_users
	FOREIGN KEY (user_id) REFERENCES users (user_id);

ALTER TABLE comments ADD CONSTRAINT FK_comments_users
	FOREIGN KEY (user_id) REFERENCES users (user_id);

ALTER TABLE comments ADD CONSTRAINT FK_comments_files
	FOREIGN KEY (file_id) REFERENCES files (file_id);


INSERT INTO roles (name)
	VALUES('administrator');

INSERT INTO roles (name)
	VALUES('uzytkownik');		

INSERT INTO file_statuses (name)
	VALUES('skompilowany');

INSERT INTO file_statuses (name)
	VALUES('blad kompilacji');

INSERT INTO file_statuses (name)
	VALUES('oczekujacy na kompilacje');

INSERT INTO file_statuses (name)
	VALUES('toDelete');

INSERT INTO file_visibility (name)
	VALUES('prywatny');	

INSERT INTO file_visibility (name)
	VALUES('publiczny');

INSERT INTO file_visibility (name)
	VALUES('test');

INSERT INTO users (username, password, role_id)
	VALUES('username1', 'password1', 1);
INSERT INTO users (username, password, role_id)
	VALUES('username2', 'password2', 2);
INSERT INTO users (username, password, role_id)
	VALUES('username3', 'password3', 2);
INSERT INTO users (username, password, role_id)
	VALUES('username4', 'password4', 2);
INSERT INTO users (username, password, role_id)
	VALUES('username5', 'password5', 2);
INSERT INTO users (username, password, role_id)
	VALUES('toDelete', 'password5', 2);

INSERT INTO files (filename, file_status_id, file_visibility_id, user_id, creation_time, description)
	VALUES('source1', 1, 1, 1, now(), 'description1');

INSERT INTO files (filename, file_status_id, file_visibility_id, user_id, creation_time, description)
	VALUES('source2', 2, 2, 1, now(), 'description2');

INSERT INTO files (filename, file_status_id, file_visibility_id, user_id, creation_time, description)
	VALUES('source3', 3, 1, 1, now(), 'description3');

INSERT INTO files (filename, file_status_id, file_visibility_id, user_id, creation_time, description)
	VALUES('source4', 1, 2, 2, now(), 'description4');

INSERT INTO files (filename, file_status_id, file_visibility_id, user_id, creation_time, description)
	VALUES('source5', 2, 1, 2, now(), 'description5');

INSERT INTO files (filename, file_status_id, file_visibility_id, user_id, creation_time, description)
	VALUES('source6', 2, 1, 3, now(), 'description6');

INSERT INTO comments (comment_text, user_id, timePlaced, file_id)
	VALUES('comment1', 1, now(), 3);
INSERT INTO comments (comment_text, user_id, timePlaced, file_id)
	VALUES('comment2', 2, now(), 1);
INSERT INTO comments (comment_text, user_id, timePlaced, file_id)
	VALUES('comment3', 2, now(), 1);
INSERT INTO comments (comment_text, user_id, timePlaced, file_id)
	VALUES('comment4', 2, now(), 1);
INSERT INTO comments (comment_text, user_id, timePlaced, file_id)
	VALUES('comment5', 3, now(), 2);
INSERT INTO comments (comment_text, user_id, timePlaced, file_id)
	VALUES('comment6', 3, now(), 2);



				