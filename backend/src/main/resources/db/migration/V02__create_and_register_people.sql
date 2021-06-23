CREATE TABLE PEOPLE(
	ID BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
	NAME VARCHAR(100) NOT NULL,
	ADDRESS VARCHAR(100),
	NUMBER VARCHAR(10),
	ADDITIONAL VARCHAR (75),
	NEIGHBORHOOD VARCHAR (75),
	ZIPCODE VARCHAR(10), 
	CITY VARCHAR (75),
	STATE VARCHAR (2),
	ACTIVE BOOLEAN
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO PEOPLE(NAME,ADDRESS,NUMBER,ADDITIONAL,NEIGHBORHOOD,ZIPCODE,CITY,STATE,ACTIVE) VALUES ('Logan','Main Street', '1', 'N','Williamsburg','11.115-290','New York','NY',true);
INSERT INTO PEOPLE(NAME,ADDRESS,NUMBER,ADDITIONAL,NEIGHBORHOOD,ZIPCODE,CITY,STATE,ACTIVE) VALUES ('Anakin','Main Street', '2', 'N','West Village','11.115-291','New York','NY',true);
INSERT INTO PEOPLE(NAME,ADDRESS,NUMBER,ADDITIONAL,NEIGHBORHOOD,ZIPCODE,CITY,STATE,ACTIVE) VALUES ('BarryAllen','Main Street', '3', 'N','Soho','11.115-292','New York','NY',true);
INSERT INTO PEOPLE(NAME,ADDRESS,NUMBER,ADDITIONAL,NEIGHBORHOOD,ZIPCODE,CITY,STATE,ACTIVE) VALUES ('Superman','Main Street', '4', 'N','Massachusetts','11.115-293','Worcester','MA',true);
INSERT INTO PEOPLE(NAME,ADDRESS,NUMBER,ADDITIONAL,NEIGHBORHOOD,ZIPCODE,CITY,STATE,ACTIVE) VALUES ('Diana Prince','Paradise Island', '5', 'N','The Magnificent Mile','11.115-294','Chicago','IL',true);
INSERT INTO PEOPLE(NAME,ADDRESS,NUMBER,ADDITIONAL,NEIGHBORHOOD,ZIPCODE,CITY,STATE,ACTIVE) values ('João Silva', 'Rua do Abacaxi', '10', null, 'Brasil', '38.400-12', 'Uberlândia', 'MG', true);
INSERT INTO PEOPLE(NAME,ADDRESS,NUMBER,ADDITIONAL,NEIGHBORHOOD,ZIPCODE,CITY,STATE,ACTIVE) values ('Maria Rita', 'Rua do Sabiá', '110', 'Apto 101', 'Colina', '11.400-12', 'Ribeirão Preto', 'SP', true);
INSERT INTO PEOPLE(NAME,ADDRESS,NUMBER,ADDITIONAL,NEIGHBORHOOD,ZIPCODE,CITY,STATE,ACTIVE) values ('Pedro Santos', 'Rua da Bateria', '23', null, 'Morumbi', '54.212-12', 'Goiânia', 'GO', true);
INSERT INTO PEOPLE(NAME,ADDRESS,NUMBER,ADDITIONAL,NEIGHBORHOOD,ZIPCODE,CITY,STATE,ACTIVE) values ('Ricardo Pereira', 'Rua do Motorista', '123', 'Apto 302', 'Aparecida', '38.400-12', 'Salvador', 'BA', true);
INSERT INTO PEOPLE(NAME,ADDRESS,NUMBER,ADDITIONAL,NEIGHBORHOOD,ZIPCODE,CITY,STATE,ACTIVE) values ('Josué Mariano', 'Av Rio Branco', '321', null, 'Jardins', '56.400-12', 'Natal', 'RN', true);
INSERT INTO PEOPLE(NAME,ADDRESS,NUMBER,ADDITIONAL,NEIGHBORHOOD,ZIPCODE,CITY,STATE,ACTIVE) values ('Pedro Barbosa', 'Av Brasil', '100', null, 'Tubalina', '77.400-12', 'Porto Alegre', 'RS', true);
INSERT INTO PEOPLE(NAME,ADDRESS,NUMBER,ADDITIONAL,NEIGHBORHOOD,ZIPCODE,CITY,STATE,ACTIVE) values ('Henrique Medeiros', 'Rua do Sapo', '1120', 'Apto 201', 'Centro', '12.400-12', 'Rio de Janeiro', 'RJ', true);
INSERT INTO PEOPLE(NAME,ADDRESS,NUMBER,ADDITIONAL,NEIGHBORHOOD,ZIPCODE,CITY,STATE,ACTIVE) values ('Carlos Santana', 'Rua da Manga', '433', null, 'Centro', '31.400-12', 'Belo Horizonte', 'MG', true);
INSERT INTO PEOPLE(NAME,ADDRESS,NUMBER,ADDITIONAL,NEIGHBORHOOD,ZIPCODE,CITY,STATE,ACTIVE) values ('Leonardo Oliveira', 'Rua do Músico', '566', null, 'Segismundo Pereira', '38.400-00', 'Uberlândia', 'MG', true);
INSERT INTO PEOPLE(NAME,ADDRESS,NUMBER,ADDITIONAL,NEIGHBORHOOD,ZIPCODE,CITY,STATE,ACTIVE) values ('Isabela Martins', 'Rua da Terra', '1233', 'Apto 10', 'Vigilato', '99.400-12', 'Manaus', 'AM', true);
INSERT INTO PEOPLE(NAME,ADDRESS,NUMBER,ADDITIONAL,NEIGHBORHOOD,ZIPCODE,CITY,STATE,ACTIVE) values ('Isabela', 'Rua da Terra', '1233', 'Apto 10', 'Vigilato', '99.400-12', 'Manaus', 'AM', true);
