CREATE TABLE contact (
    id BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
	people_id BIGINT(20) NOT NULL,
	name VARCHAR(50) NOT NULL,
	email VARCHAR(100) NOT NULL,
	phone VARCHAR(20) NOT NULL,
  FOREIGN KEY (people_id) REFERENCES people(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

insert into contact (id, people_id, name, email, phone) values (1, 4, 'Pri Tiemi', 'pritiemias@yahoo.com.br', '00 0000-0000');