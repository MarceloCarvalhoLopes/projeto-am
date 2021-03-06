CREATE TABLE FINANCIAL_RELEASE(
	ID BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
	DESCRIPTION VARCHAR(50) NOT NULL,
	DUE_DATE DATE NOT NULL,
	PAYMENT_DATE DATE,
	VALUE DECIMAL(10,2) NOT NULL,
	OBSERVATION VARCHAR(50),
	TYPE VARCHAR(20) NOT NULL,
	CATEGORY_ID BIGINT(20) NOT NULL,
	PEOPLE_ID BIGINT(20) NOT NULL,
	FOREIGN KEY (CATEGORY_ID) REFERENCES CATEGORY(ID),
	FOREIGN KEY (PEOPLE_ID) REFERENCES PEOPLE(ID)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


INSERT INTO FINANCIAL_RELEASE (DESCRIPTION, DUE_DATE, PAYMENT_DATE, VALUE, OBSERVATION, TYPE, CATEGORY_ID, PEOPLE_ID) values ('Monthly salary', '2017-06-10', null, 6500.00, 'Distribuição de lucros', 'RECEIPT', 1, 1);
INSERT INTO FINANCIAL_RELEASE (DESCRIPTION, DUE_DATE, PAYMENT_DATE, VALUE, OBSERVATION, TYPE, CATEGORY_ID, PEOPLE_ID) values ('Bahamas', '2017-02-10', '2017-02-10', 100.32, null, 'EXPENSE', 2, 2);
INSERT INTO FINANCIAL_RELEASE (DESCRIPTION, DUE_DATE, PAYMENT_DATE, VALUE, OBSERVATION, TYPE, CATEGORY_ID, PEOPLE_ID) values ('Top Club', '2017-06-10', null, 120, null, 'RECEIPT', 3, 3);
INSERT INTO FINANCIAL_RELEASE (DESCRIPTION, DUE_DATE, PAYMENT_DATE, VALUE, OBSERVATION, TYPE, CATEGORY_ID, PEOPLE_ID) values ('CEMIG', '2017-02-10', '2017-02-10', 110.44, 'Geração', 'RECEIPT', 3, 4);
INSERT INTO FINANCIAL_RELEASE (DESCRIPTION, DUE_DATE, PAYMENT_DATE, VALUE, OBSERVATION, TYPE, CATEGORY_ID, PEOPLE_ID) values ('DMAE', '2017-06-10', null, 200.30, null, 'EXPENSE', 3, 5);
INSERT INTO FINANCIAL_RELEASE (DESCRIPTION, DUE_DATE, PAYMENT_DATE, VALUE, OBSERVATION, TYPE, CATEGORY_ID, PEOPLE_ID) values ('Extra', '2017-03-10', '2017-03-10', 1010.32, null, 'RECEIPT', 4, 6);
INSERT INTO FINANCIAL_RELEASE (DESCRIPTION, DUE_DATE, PAYMENT_DATE, VALUE, OBSERVATION, TYPE, CATEGORY_ID, PEOPLE_ID) values ('Bahamas', '2017-06-10', null, 500, null, 'RECEIPT', 1, 7);
INSERT INTO FINANCIAL_RELEASE (DESCRIPTION, DUE_DATE, PAYMENT_DATE, VALUE, OBSERVATION, TYPE, CATEGORY_ID, PEOPLE_ID) values ('Top Club', '2017-03-10', '2017-03-10', 400.32, null, 'EXPENSE', 4, 8);
INSERT INTO FINANCIAL_RELEASE (DESCRIPTION, DUE_DATE, PAYMENT_DATE, VALUE, OBSERVATION, TYPE, CATEGORY_ID, PEOPLE_ID) values ('Despachante', '2017-06-10', null, 123.64, 'Multas', 'EXPENSE', 3, 9);
INSERT INTO FINANCIAL_RELEASE (DESCRIPTION, DUE_DATE, PAYMENT_DATE, VALUE, OBSERVATION, TYPE, CATEGORY_ID, PEOPLE_ID) values ('Wheel', '2017-04-10', '2017-04-10', 665.33, null, 'RECEIPT', 5, 10);
INSERT INTO FINANCIAL_RELEASE (DESCRIPTION, DUE_DATE, PAYMENT_DATE, VALUE, OBSERVATION, TYPE, CATEGORY_ID, PEOPLE_ID) values ('Coffee', '2017-06-10', null, 8.32, null, 'EXPENSE', 1, 5);
INSERT INTO FINANCIAL_RELEASE (DESCRIPTION, DUE_DATE, PAYMENT_DATE, VALUE, OBSERVATION, TYPE, CATEGORY_ID, PEOPLE_ID) values ('Eletrônicos', '2017-04-10', '2017-04-10', 2100.32, null, 'EXPENSE', 5, 4);
INSERT INTO FINANCIAL_RELEASE (DESCRIPTION, DUE_DATE, PAYMENT_DATE, VALUE, OBSERVATION, TYPE, CATEGORY_ID, PEOPLE_ID) values ('Instrumentos', '2017-06-10', null, 1040.32, null, 'EXPENSE', 4, 3);
INSERT INTO FINANCIAL_RELEASE (DESCRIPTION, DUE_DATE, PAYMENT_DATE, VALUE, OBSERVATION, TYPE, CATEGORY_ID, PEOPLE_ID) values ('Coffee', '2017-04-10', '2017-04-10', 4.32, null, 'EXPENSE', 4, 2);
INSERT INTO FINANCIAL_RELEASE (DESCRIPTION, DUE_DATE, PAYMENT_DATE, VALUE, OBSERVATION, TYPE, CATEGORY_ID, PEOPLE_ID) values ('Lunch', '2017-06-10', null, 10.20, null, 'EXPENSE', 4, 1);