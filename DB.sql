-- Active: 1743133057434@@127.0.0.1@3306@colegio
CREATE DATABASE tiendaveh;

USE tiendaveh;

CREATE TABLE vehiculos(
	id INT AUTO_INCREMENT PRIMARY KEY,
    marca VARCHAR(30) NOT NULL,
    modelo 	VARCHAR(30) NOT NULL,
    color 	VARCHAR(30) NOT NULL,
    precio 	DECIMAL(9,2) NOT NULL,
    placa 	CHAR(7) NOT NULL,
    CONSTRAINT uk_placa_veh UNIQUE (placa)
)ENGINE = INNODB;

INSERT INTO vehiculos (marca, modelo, color, precio, placa) VALUE
('Nissan', 'Frotier','gris','145000','ABC-111'),
('Toyota', 'Hilux','blanco','98000','ABC-222');