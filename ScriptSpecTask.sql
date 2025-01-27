/*В подключенном MySQL репозитории создать базу данных “Друзья человека”*/
CREATE DATABASE HumanFriends;

USE HumanFriends;

/* Создать таблицы с иерархией из диаграммы в БД и
 заполнить низкоуровневые таблицы именами(животных), командами которые они выполняют и датами рождения*/
CREATE TABLE animals (
	typeID INT PRIMARY KEY AUTO_INCREMENT,
	typeAnimal VARCHAR(20)
);

INSERT INTO
	animals (typeAnimal)
VALUES
	('Pack'),
	('Pet');

CREATE TABLE pack_animals (
	packID INT PRIMARY KEY AUTO_INCREMENT,
	name VARCHAR(20),
	typeID INT,
	FOREIGN KEY (typeID) REFERENCES animals(typeID) ON DELETE CASCADE ON UPDATE CASCADE
);

INSERT INTO
	pack_animals (name, typeID)
VALUES
	('Horses', 1),
	('Donkeys', 1),
	('Camels', 1);

CREATE TABLE pets (
	petID INT PRIMARY KEY AUTO_INCREMENT,
	name VARCHAR(20),
	typeID INT,
	FOREIGN KEY (typeID) REFERENCES animals(typeID) ON DELETE CASCADE ON UPDATE CASCADE
);

INSERT INTO
	pets (name, typeID)
VALUES
	('cat', 2),
	('dog', 2),
	('hamester', 2);

CREATE TABLE cats (
	catID INT PRIMARY KEY AUTO_INCREMENT,
	name VARCHAR(20),
	date_birth DATE,
	commands VARCHAR(50),
	petID INT,
	FOREIGN KEY (petID) REFERENCES pets (petID) ON DELETE CASCADE ON UPDATE CASCADE
);

INSERT INTO
	cats (name, date_birth, commands, petID)
VALUES
	('John', '2020-03-15', 'to lie', 1),
	('Miliana', '2024-05-07', "mrr", 1),
	('Pinki', '2015-06-19', "none", 1);

CREATE TABLE dogs (
	dogID INT PRIMARY KEY AUTO_INCREMENT,
	name VARCHAR(20),
	date_birth DATE,
	commands VARCHAR(50),
	petID INT,
	FOREIGN KEY (petID) REFERENCES pets (petID) ON DELETE CASCADE ON UPDATE CASCADE
);

INSERT INTO
	dogs (name, date_birth, commands, petID)
VALUES
	('Jack', '2020-03-15', 'Gav-gav', 2),
	('Miliana', '2024-05-07', "jump", 2),
	('Black Pirat', '2015-06-19', "none", 2);

CREATE TABLE hamsters (
	hamstersID INT PRIMARY KEY AUTO_INCREMENT,
	name VARCHAR(20),
	date_birth DATE,
	commands VARCHAR(50),
	petID int,
	FOREIGN KEY (petID) REFERENCES pets (petID) ON DELETE CASCADE ON UPDATE CASCADE
);

INSERT INTO
	hamsters (name, date_birth, commands, petID)
VALUES
	('Jack', '2020-03-15', 'to lie', 3),
	('Miliana', '2024-05-07', "jump", 3),
	('Pumba', '2015-06-19', "puk-puk", 3);

CREATE TABLE horses (
	horsesID INT PRIMARY KEY AUTO_INCREMENT,
	name VARCHAR(20),
	date_birth DATE,
	commands VARCHAR(50),
	packID int,
	FOREIGN KEY (packID) REFERENCES pack_animals (packID) ON DELETE CASCADE ON UPDATE CASCADE
);

INSERT INTO
	horses (name, date_birth, commands, packID)
VALUES
	('Jack', '2020-03-15', 'Go-go', 1),
	('Jira', '2024-05-07', "jump", 1),
	('Pickachu', '2019-03-19', "to lie", 1);

CREATE TABLE donkeys (
	donkeysID INT PRIMARY KEY AUTO_INCREMENT,
	name VARCHAR(20),
	date_birth DATE,
	commands VARCHAR(50),
	packID int,
	FOREIGN KEY (packID) REFERENCES pack_animals (packID) ON DELETE CASCADE ON UPDATE CASCADE
);

INSERT INTO
	donkeys (name, date_birth, commands, packID)
VALUES
	('Malik', '2021-03-17', 'Ik-ik', 2),
	('Panda', '2015-04-07', "jump", 2),
	('Point', '2020-08-10', "to lie", 2);

CREATE TABLE camels (
	camelsID INT PRIMARY KEY AUTO_INCREMENT,
	name VARCHAR(20),
	date_birth DATE,
	commands VARCHAR(50),
	packID int,
	FOREIGN KEY (packID) REFERENCES pack_animals (packID) ON DELETE CASCADE ON UPDATE CASCADE
);

INSERT INTO
	camels (name, date_birth, commands, packID)
VALUES
	('Mamed', '2017-03-17', 'none', 3),
	('Simba', '2022-04-07', "jump", 3),
	('Rectangle', '2014-08-10', "to lie", 3);

/* Удалив из таблицы верблюдов, т.к. верблюдов решили перевезти в другой питомник на зимовку.
 Объединить таблицы лошади, и ослы в одну таблицу.*/
DELETE FROM
	camels;

SELECT
	name,
	date_birth,
	commands
FROM
	horses
UNION
SELECT
	name,
	date_birth,
	commands
FROM
	donkeys;

/* Создать новую таблицу “молодые животные” в которую попадут все животные старше 1 года,
 но младше 3 лет и в отдельном столбце с точностью до месяца подсчитать возраст животных в новой таблице */
CREATE TEMPORARY TABLE animals AS (
	SELECT
		*,
		'horses' as type_an
	FROM
		horses
	UNION
	SELECT
		*,
		'donkeys' AS type_an
	FROM
		donkeys
	UNION
	SELECT
		*,
		'dogs' AS type_an
	FROM
		dogs
	UNION
	SELECT
		*,
		'cats' AS type_an
	FROM
		cats
	UNION
	SELECT
		*,
		'hamsters' AS type_an
	FROM
		hamsters
);

CREATE TABLE yang_animal AS (
	SELECT
		name,
		date_birth,
		commands,
		type_an,
		TIMESTAMPDIFF(MONTH, date_birth, CURDATE()) AS age_in_month
	FROM
		animals
	WHERE
		date_birth BETWEEN ADDDATE(curdate(), INTERVAL -3 YEAR)
		AND ADDDATE(CURDATE(), INTERVAL -1 YEAR)
);

SELECT
	*
FROM
	yang_animal;
/* Объединить все таблицы в одну, при этом сохраняя поля, указывающие на прошлую принадлежность к старым таблицам.*/
SELECT h.name, h.date_birth, h.commands, pa.name, ya.age_in_month 
FROM horses h
LEFT JOIN yang_animal ya ON ya.name = h.name
LEFT JOIN pack_animals pa ON pa.packID = h.packID
UNION 
SELECT d.name, d.date_birth, d.commands, pa.name, ya.age_in_month 
FROM donkeys d 
LEFT JOIN yang_animal ya ON ya.name = d.name
LEFT JOIN pack_animals pa ON pa.packID = d.packID
UNION
SELECT c.name, c.date_birth, c.commands, p.name, ya.age_in_month 
FROM cats c
LEFT JOIN yang_animal ya ON ya.name = c.name
LEFT JOIN pets p ON p.petID = c.petID
UNION
SELECT d.name, d.date_birth, d.commands, p.name, ya.age_in_month 
FROM dogs d
LEFT JOIN yang_animal ya ON ya.name = d.name
LEFT JOIN pets p ON ha.petID = d.petID
UNION
SELECT hm.name, hm.date_birth, hm.commands, p.Genus_name, ya.age_in_month 
FROM hamsters hm
LEFT JOIN yang_animal ya ON ya.name = hm.name
LEFT JOIN pets p ON p.petID = hm.petID;