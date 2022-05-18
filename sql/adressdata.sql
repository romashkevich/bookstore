CREATE TABLE adress(
	adress_id BIGSERIAL PRIMARY KEY,
	country VARCHAR(100) NOT NULL,
    city VARCHAR(100) NOT NULL,
   	street VARCHAR(100) NOT NULL,
    strNum SMALLINT NOT NULL,
    apart SMALLINT NOT NULL
);
--10
INSERT INTO adress (country, city, street, strNum, apart) VALUES
('Belarus', 'Minsk', 'Brikela', '23', '12'),
('Belarus', 'Minsk', 'Velosipedny per.', '1', '13'),
('Belarus', 'Minsk', 'Nikiforova', '12', '3'),
('Belarus', 'Minsk', 'pr. Zhukova', '13', '55'),
('Belarus', 'Minsk', 'Lubimova', '45', '89'),
('Belarus', 'Minsk', 'Esenina', '6', '96'),
('Belarus', 'Minsk', 'Lopatina', '27', '22'),
('Belarus', 'Minsk', 'Kolcova', '2', '14'),
('Belarus', 'Minsk', 'Nekrasova', '13', '45'),
('Belarus', 'Minsk', 'Shugaeva', '2', '32');