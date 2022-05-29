CREATE TABLE users(
	id BIGSERIAL PRIMARY KEY,
	login VARCHAR(50) UNIQUE NOT NULL,
	email VARCHAR(100) UNIQUE NOT NULL,
	pass VARCHAR(30) NOT NULL,
	firstname VARCHAR(50) NOT NULL,
	lastname VARCHAR(100) NOT NULL,
	adress_id BIGINT REFERENCES adress(adress_id),
	telnumber VARCHAR(18) UNIQUE NOT NULL,
	sex_id BIGINT REFERENCES sex(sex_id),
	deleted BOOLEAN DEFAULT false NOT NULL
);
INSERT INTO users(login, email, pass, firstname, lastname, adress_id, telnumber, sex_id) VALUES
('nik', 'glavvrach@14crp.by', 'glav1234', 'Irina', 'Petrova', '1', '+375(29)5678764', '2'),
('nik1', 'info@14crp.by', 'glav12345', 'Marya', 'Sidorova', '2', '+375(29)5238764', '2'),
('nik12', 'terapevt@14crp.by', 'glav12346', 'Arina', 'Ivanova', '3', '+375(29)5248764', '2'),
('nik13', 'hirurg@14crp.by', 'glav12347', 'Polina', 'Luxemburg', '4', '+375(29)5258764', '2'),
('nik14', 'lor@14crp.by', 'glav12348', 'Kate', 'Dovlatova', '5', '+375(29)5348764', '2'),
('nik15', 'narkolog@14crp.by', 'glav12349', 'Petr', 'Petrov', '6', '+375(29)5358764', '1'),
('nik16', 'stomatolog@14crp.by', 'glav123410', 'Mike', 'Sidorov', '7', '+375(29)5368764', '1'),
('nik17', 'glavbuh14crp.by', 'glav123411', 'Tom', 'Ivanov', '8', '+375(29)56458764', '1'),
('nik18', 'laboratory@14crp.by', 'glav123412', 'Tanya', 'Romanova', '9', '+375(29)5438764', '2'),
('nik19', 'regestratura@14crp.by', 'glav123414', 'Irina', 'D_Ark', '10', '+375(29)5548764', '2');