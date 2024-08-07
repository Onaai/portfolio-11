-- Crear la base de datos
CREATE DATABASE IF NOT EXISTS `portfolio-10`;

-- Usar la base de datos
USE `portfolio-10`;

-- Crear la tabla users
CREATE TABLE IF NOT EXISTS `users` (
    `id` INT NOT NULL AUTO_INCREMENT,
    `usuario` VARCHAR(50) NOT NULL,
    `contrasena` VARCHAR(64) NOT NULL,
    PRIMARY KEY (`id`)
);
