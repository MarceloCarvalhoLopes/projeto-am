CREATE TABLE USER (
	ID BIGINT(20) PRIMARY KEY,
	NAME VARCHAR(50) NOT NULL,
	EMAIL VARCHAR(50) NOT NULL,
	PASSWORD VARCHAR(150) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE PERMISSION (
	ID BIGINT(20) PRIMARY KEY,
	DESCRIPTION VARCHAR(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE USER_PERMISSION (
	USER_ID BIGINT(20) NOT NULL,
	PERMISSION_ID BIGINT(20) NOT NULL,
	PRIMARY KEY (USER_ID, PERMISSION_ID),
	FOREIGN KEY (USER_ID) REFERENCES USER(ID),
	FOREIGN KEY (PERMISSION_ID) REFERENCES PERMISSION(ID)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO USER (ID, NAME, EMAIL, PASSWORD) values (1, 'Administrador', 'admin@algamoney.com', '$2a$10$X607ZPhQ4EgGNaYKt3n4SONjIv9zc.VMWdEuhCuba7oLAL5IvcL5.');
INSERT INTO USER (ID, NAME, EMAIL, PASSWORD) values (2, 'Maria Silva', 'maria@algamoney.com', '$2a$10$Zc3w6HyuPOPXamaMhh.PQOXvDnEsadztbfi6/RyZWJDzimE8WQjaq');

INSERT INTO PERMISSION (ID, DESCRIPTION) values (1, 'ROLE_REGISTER_CATEGORY');
INSERT INTO PERMISSION (ID, DESCRIPTION) values (2, 'ROLE_SEARCH_CATEGORY');

INSERT INTO PERMISSION (ID, DESCRIPTION) values (3, 'ROLE_REGISTER_PEOPLE');
INSERT INTO PERMISSION (ID, DESCRIPTION) values (4, 'ROLE_REMOVE_PEOPLE');
INSERT INTO PERMISSION (ID, DESCRIPTION) values (5, 'ROLE_SEARCH_PEOPLE');

INSERT INTO PERMISSION (ID, DESCRIPTION) values (6, 'ROLE_REGISTER_FINANCIAL_RELEASE');
INSERT INTO PERMISSION (ID, DESCRIPTION) values (7, 'ROLE_REMOVE_FINANCIAL_RELEASE');
INSERT INTO PERMISSION (ID, DESCRIPTION) values (8, 'ROLE_SEARCH_FINANCIAL_RELEASE');

-- admin
INSERT INTO USER_PERMISSION (USER_ID, PERMISSION_ID) values (1, 1);
INSERT INTO USER_PERMISSION (USER_ID, PERMISSION_ID) values (1, 2);
INSERT INTO USER_PERMISSION (USER_ID, PERMISSION_ID) values (1, 3);
INSERT INTO USER_PERMISSION (USER_ID, PERMISSION_ID) values (1, 4);
INSERT INTO USER_PERMISSION (USER_ID, PERMISSION_ID) values (1, 5);
INSERT INTO USER_PERMISSION (USER_ID, PERMISSION_ID) values (1, 6);
INSERT INTO USER_PERMISSION (USER_ID, PERMISSION_ID) values (1, 7);
INSERT INTO USER_PERMISSION (USER_ID, PERMISSION_ID) values (1, 8);

-- maria
INSERT INTO USER_PERMISSION (USER_ID, PERMISSION_ID) values (2, 2);
INSERT INTO USER_PERMISSION (USER_ID, PERMISSION_ID) values (2, 5);
INSERT INTO USER_PERMISSION (USER_ID, PERMISSION_ID) values (2, 8);