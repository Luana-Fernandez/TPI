-- DROP DATABASE db_competencia;
CREATE DATABASE db_competencia;
USE db_competencia;

CREATE TABLE competidores(
idCompetidor INT AUTO_INCREMENT PRIMARY KEY, 
nombre VARCHAR(40) NOT NULL, 
apellido VARCHAR(40) NOT NULL,
email VARCHAR(50),
telefono VARCHAR(50));
CREATE TABLE jueces(
idJuez INT AUTO_INCREMENT PRIMARY KEY,
nombre VARCHAR(40) NOT NULL,
apellido VARCHAR(40) NOT NULL,
email VARCHAR(50),
telefono VARCHAR(50));
CREATE TABLE carreras(
idCarrera INT AUTO_INCREMENT PRIMARY KEY,
categoria VARCHAR(40) NOT NULL,
horaInicio VARCHAR(20) NOT NULL,
horaFin VARCHAR(20),
ubicacion VARCHAR(50) NOT NULL,
detalleRecorrido VARCHAR(200),
idJuez INT,
FOREIGN KEY (idJuez) REFERENCES jueces(idJuez));
CREATE TABLE resultados(
idResultado INT AUTO_INCREMENT PRIMARY KEY,
idCompetidor INT NOT NULL,
idCarrera INT NOT NULL,
tiempoCompetidor VARCHAR(20),
estado VARCHAR(30),
numeroCorredor INT,
faltas VARCHAR(100),
FOREIGN KEY (idCompetidor) REFERENCES competidores(idCompetidor),
FOREIGN KEY (idCarrera) REFERENCES carreras(idCarrera));
SHOW TABLES;
SELECT * FROM competidores;
SELECT * FROM jueces;
SELECT * FROM carreras;