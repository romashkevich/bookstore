CREATE TABLE books(
	id BIGSERIAL PRIMARY KEY,
	isbn VARCHAR(50) UNIQUE NOT NULL,
	title VARCHAR(100) NOT NULL,
	author VARCHAR(100) NOT NULL,
	pages SMALLINT NOT NULL,
	cover VARCHAR NOT NULL,
	price DECIMAL (5 , 2) NOT NULL,
	deleted BOOLEAN DEFAULT false NOT NULL
);

INSERT INTO books (isbn, title, author, pages, cover, price)
VALUES ('1234-1234', 'Harry Poter and stone finger', 'Joanne Rowling', 350, 'SOFT', 35.10 ),
		('1234-5678', 'James Bond agent 007', 'Jan Fleming', 286, 'HARD', 25.00),
		('1234-9012', 'Eugeny Onegin', 'Alex Pushkin', 125, 'HARD', 12.15),
		('1234-3456', 'Freddie and black hole', 'Freddie Mercury', 170, 'SOFT', 10.00),
		('1234-7890', 'Palata № 6', 'Anton Chehov', 25, 'SOFT', 6.20),
		('1234-0987', 'Dogs heart', 'Mihail Bulgakov', 500, 'HARD', 45.00),
		('1234-6543', 'Revizor', 'Nick Hohol', 235, 'HARD', 23.15),
		('1234-2109', 'Rudin', 'Ivan Turgenev', 127, 'SOFT', 17.00),
		('1234-8765', 'Idiot', 'Fedor Dostoevski', 456, 'HARD', 37.00),
		('1234-4321', 'Gore ot Uma', 'FAlex Griboedov', 457, 'HARD', 34.80),
		('1234-1357', 'Voskresenie', 'Lev Tolstoy', 457, 'HARD', 34.80),
		('1234-9246', 'Otzzi i deti', 'Ivan Turgenev', 270, 'HARD', 35.00),
		('1234-7689', 'Master i Margarita', 'Mihail Bulgakov', 600, 'HARD', 80.00),
		('1234-3421', 'Tri Mushketera', 'Alex Duma', 450, 'HARD', 33.00),
		('1234-5783', 'Sobaka Baskervie', 'IIlya Ilf', 210, 'SOFT', 43.00),
		('1234-5665', 'Povest Belkina', 'Alex Pushkin', 234, 'SOFT', 17.40),
		('1234-5557', 'Voina i mir', 'Fedor Dostoevski', 1200, 'HARD', 100.00),
		('1234-5455', 'Anna Karenina', 'Lev Tolstoy', 456, 'SOFT', 97.00);