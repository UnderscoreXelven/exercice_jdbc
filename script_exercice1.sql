-- Fichier pour la BDD de l'exercice 1
CREATE DATABASE exercice1_jdbc;

USE exercice1_jdbc;

CREATE TABLE IF NOT EXISTS etudiant(
    id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
	first_name VARCHAR(150),
    last_name VARCHAR(150),
    class_number INT,
    graduation_date VARCHAR(150)
);