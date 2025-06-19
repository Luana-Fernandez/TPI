-- DROP DATABASE db_competencia;
CREATE DATABASE db_competencia;
USE db_competencia;

CREATE TABLE competidores(
idCompetidor INT AUTO_INCREMENT PRIMARY KEY, 
nombre VARCHAR(40) NOT NULL, 
apellido VARCHAR(40) NOT NULL,
telefono VARCHAR(50),
email VARCHAR(50));
CREATE TABLE jueces(
idJuez INT AUTO_INCREMENT PRIMARY KEY,
nombre VARCHAR(40) NOT NULL,
apellido VARCHAR(40) NOT NULL,
telefono VARCHAR(50),
email VARCHAR(50));
CREATE TABLE carreras(
idCarrera INT AUTO_INCREMENT PRIMARY KEY,
categoria VARCHAR(40) NOT NULL,
idJuez INT,
horaInicio VARCHAR(20) NOT NULL,
horaFin VARCHAR(20),
ubicacion VARCHAR(50) NOT NULL,
detalleRecorrido VARCHAR(200),
FOREIGN KEY (idJuez) REFERENCES jueces(idJuez));
CREATE TABLE resultados(
idResultado INT AUTO_INCREMENT PRIMARY KEY,
idCompetidor INT NOT NULL,
idCarrera INT NOT NULL,
tiempoCompetidor VARCHAR(20),
estado VARCHAR(30),
numeroCorredor INT,
faltas INT,
FOREIGN KEY (idCompetidor) REFERENCES competidores(idCompetidor),
FOREIGN KEY (idCarrera) REFERENCES carreras(idCarrera)
);
