-- MySQL Script generated by MySQL Workbench
<<<<<<< HEAD
-- di 17 nov 2015 11:56:56 CET
=======
-- ma 23 nov 2015 12:39:40 CET
>>>>>>> release/0.2
-- Model: New Model    Version: 1.0
-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
<<<<<<< HEAD
-- Schema LuggageControlData
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema LuggageControlData
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `LuggageControlData` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci ;
USE `LuggageControlData` ;

-- -----------------------------------------------------
-- Table `LuggageControlData`.`users`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `LuggageControlData`.`users` (
=======
-- Schema luggagecontroldata
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema luggagecontroldata
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `luggagecontroldata` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci ;
USE `luggagecontroldata` ;

-- -----------------------------------------------------
-- Table `luggagecontroldata`.`users`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `luggagecontroldata`.`users` (
>>>>>>> release/0.2
  `user_id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `username` VARCHAR(45) NULL,
  `password` VARCHAR(45) NULL,
  `salt` VARCHAR(16) NULL,
<<<<<<< HEAD
  `permissions` INT NULL,
=======
>>>>>>> release/0.2
  `firstname` VARCHAR(45) NULL,
  `surname` VARCHAR(45) NULL,
  `cellphone` VARCHAR(45) NULL,
  `birthday` DATE NULL,
  `gender` VARCHAR(11) NULL,
  `nationality` VARCHAR(45) NULL,
  `adress` VARCHAR(45) NULL,
  `city` VARCHAR(45) NULL,
  `postcode` VARCHAR(45) NULL,
  `image` BLOB NULL,
<<<<<<< HEAD
=======
  `permissions` INT(1) NULL,
>>>>>>> release/0.2
  PRIMARY KEY (`user_id`),
  UNIQUE INDEX `id_UNIQUE` (`user_id` ASC),
  UNIQUE INDEX `username_UNIQUE` (`username` ASC))
ENGINE = InnoDB;


-- -----------------------------------------------------
<<<<<<< HEAD
-- Table `LuggageControlData`.`flights`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `LuggageControlData`.`flights` (
=======
-- Table `luggagecontroldata`.`flights`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `luggagecontroldata`.`flights` (
>>>>>>> release/0.2
  `flight_id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `origin` VARCHAR(45) NULL,
  `destination` VARCHAR(45) NULL,
  `departure` DATETIME NULL,
  `arrival` DATETIME NULL,
  PRIMARY KEY (`flight_id`),
  UNIQUE INDEX `id_UNIQUE` (`flight_id` ASC))
ENGINE = InnoDB;


-- -----------------------------------------------------
<<<<<<< HEAD
-- Table `LuggageControlData`.`luggage`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `LuggageControlData`.`luggage` (
=======
-- Table `luggagecontroldata`.`luggage`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `luggagecontroldata`.`luggage` (
>>>>>>> release/0.2
  `luggage_id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `location` VARCHAR(45) NULL,
  `color` VARCHAR(45) NULL,
  `weight` VARCHAR(45) NULL,
  `size` VARCHAR(45) NULL,
  `contents` VARCHAR(256) NULL,
  `status` VARCHAR(45) NULL,
  `image` BLOB NULL,
  PRIMARY KEY (`luggage_id`),
  UNIQUE INDEX `id_UNIQUE` (`luggage_id` ASC))
ENGINE = InnoDB;


-- -----------------------------------------------------
<<<<<<< HEAD
-- Table `LuggageControlData`.`clients`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `LuggageControlData`.`clients` (
=======
-- Table `luggagecontroldata`.`clients`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `luggagecontroldata`.`clients` (
>>>>>>> release/0.2
  `client_id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `firstname` VARCHAR(45) NULL,
  `surname` VARCHAR(45) NULL,
  `cellphone` VARCHAR(45) NULL,
  `birthday` DATE NULL,
  `gender` VARCHAR(11) NULL,
  `nationality` VARCHAR(45) NULL,
  `adress` VARCHAR(45) NULL,
  `city` VARCHAR(45) NULL,
  `postcode` VARCHAR(45) NULL,
  `image` BLOB NULL,
  PRIMARY KEY (`client_id`),
  UNIQUE INDEX `id_UNIQUE` (`client_id` ASC))
ENGINE = InnoDB;


-- -----------------------------------------------------
<<<<<<< HEAD
-- Table `LuggageControlData`.`client_flights`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `LuggageControlData`.`client_flights` (
=======
-- Table `luggagecontroldata`.`client_flights`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `luggagecontroldata`.`client_flights` (
>>>>>>> release/0.2
  `flights_id` INT UNSIGNED NOT NULL,
  `clients_id` INT UNSIGNED NOT NULL,
  PRIMARY KEY (`flights_id`, `clients_id`),
  INDEX `fk_client_flights_clients1_idx` (`clients_id` ASC),
  CONSTRAINT `fk_client_flights_flights`
    FOREIGN KEY (`flights_id`)
<<<<<<< HEAD
    REFERENCES `LuggageControlData`.`flights` (`flight_id`)
=======
    REFERENCES `luggagecontroldata`.`flights` (`flight_id`)
>>>>>>> release/0.2
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_client_flights_clients1`
    FOREIGN KEY (`clients_id`)
<<<<<<< HEAD
    REFERENCES `LuggageControlData`.`clients` (`client_id`)
=======
    REFERENCES `luggagecontroldata`.`clients` (`client_id`)
>>>>>>> release/0.2
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
<<<<<<< HEAD
-- Table `LuggageControlData`.`luggage_flights`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `LuggageControlData`.`luggage_flights` (
=======
-- Table `luggagecontroldata`.`luggage_flights`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `luggagecontroldata`.`luggage_flights` (
>>>>>>> release/0.2
  `flights_id` INT UNSIGNED NOT NULL,
  `luggage_id` INT UNSIGNED NOT NULL,
  INDEX `fk_luggage_flights_flights1_idx` (`flights_id` ASC),
  INDEX `fk_luggage_flights_luggage1_idx` (`luggage_id` ASC),
  CONSTRAINT `fk_luggage_flights_flights1`
    FOREIGN KEY (`flights_id`)
<<<<<<< HEAD
    REFERENCES `LuggageControlData`.`flights` (`flight_id`)
=======
    REFERENCES `luggagecontroldata`.`flights` (`flight_id`)
>>>>>>> release/0.2
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_luggage_flights_luggage1`
    FOREIGN KEY (`luggage_id`)
<<<<<<< HEAD
    REFERENCES `LuggageControlData`.`luggage` (`luggage_id`)
=======
    REFERENCES `luggagecontroldata`.`luggage` (`luggage_id`)
>>>>>>> release/0.2
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
<<<<<<< HEAD
-- Table `LuggageControlData`.`client_luggage`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `LuggageControlData`.`client_luggage` (
=======
-- Table `luggagecontroldata`.`client_luggage`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `luggagecontroldata`.`client_luggage` (
>>>>>>> release/0.2
  `clients_id` INT UNSIGNED NOT NULL,
  `luggage_id` INT UNSIGNED NOT NULL,
  INDEX `fk_client_luggage_clients1_idx` (`clients_id` ASC),
  INDEX `fk_client_luggage_luggage1_idx` (`luggage_id` ASC),
  CONSTRAINT `fk_client_luggage_clients1`
    FOREIGN KEY (`clients_id`)
<<<<<<< HEAD
    REFERENCES `LuggageControlData`.`clients` (`client_id`)
=======
    REFERENCES `luggagecontroldata`.`clients` (`client_id`)
>>>>>>> release/0.2
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_client_luggage_luggage1`
    FOREIGN KEY (`luggage_id`)
<<<<<<< HEAD
    REFERENCES `LuggageControlData`.`luggage` (`luggage_id`)
=======
    REFERENCES `luggagecontroldata`.`luggage` (`luggage_id`)
>>>>>>> release/0.2
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
<<<<<<< HEAD
-- Table `LuggageControlData`.`luggage_lost_found`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `LuggageControlData`.`luggage_lost_found` (
=======
-- Table `luggagecontroldata`.`luggage_lost_found`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `luggagecontroldata`.`luggage_lost_found` (
>>>>>>> release/0.2
  `luggage_lost_id` INT UNSIGNED NOT NULL,
  `luggage_found_id` INT UNSIGNED NOT NULL,
  `status` VARCHAR(45) NULL,
  INDEX `fk_table1_luggage1_idx` (`luggage_lost_id` ASC),
  INDEX `fk_table1_luggage2_idx` (`luggage_found_id` ASC),
  CONSTRAINT `fk_table1_luggage1`
    FOREIGN KEY (`luggage_lost_id`)
<<<<<<< HEAD
    REFERENCES `LuggageControlData`.`luggage` (`luggage_id`)
=======
    REFERENCES `luggagecontroldata`.`luggage` (`luggage_id`)
>>>>>>> release/0.2
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_table1_luggage2`
    FOREIGN KEY (`luggage_found_id`)
<<<<<<< HEAD
    REFERENCES `LuggageControlData`.`luggage` (`luggage_id`)
=======
    REFERENCES `luggagecontroldata`.`luggage` (`luggage_id`)
>>>>>>> release/0.2
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

<<<<<<< HEAD
CREATE USER 'luggagecontroluser' IDENTIFIED BY 'verysecurepassword';

GRANT ALL ON `LuggageControlData`.* TO 'luggagecontroluser';
GRANT ALL ON TABLE `LuggageControlData`.`users` TO 'luggagecontroluser';
GRANT ALL ON TABLE `LuggageControlData`.`luggage` TO 'luggagecontroluser';
GRANT ALL ON TABLE `LuggageControlData`.`client_luggage` TO 'luggagecontroluser';
GRANT ALL ON TABLE `LuggageControlData`.`clients` TO 'luggagecontroluser';
GRANT ALL ON TABLE `LuggageControlData`.`client_flights` TO 'luggagecontroluser';
GRANT ALL ON TABLE `LuggageControlData`.`flights` TO 'luggagecontroluser';
GRANT ALL ON TABLE `LuggageControlData`.`luggage_flights` TO 'luggagecontroluser';
=======
CREATE USER 'lugcontroluser'@'localhost' IDENTIFIED BY 'verysecurepassword';

GRANT ALL ON `luggagecontroldata`.* TO 'lugcontroluser'@'localhost';
GRANT ALL ON TABLE `luggagecontroldata`.`users` TO 'lugcontroluser'@'localhost';
GRANT ALL ON TABLE `luggagecontroldata`.`luggage` TO 'lugcontroluser'@'localhost';
GRANT ALL ON TABLE `luggagecontroldata`.`client_luggage` TO 'lugcontroluser'@'localhost';
GRANT ALL ON TABLE `luggagecontroldata`.`clients` TO 'lugcontroluser'@'localhost';
GRANT ALL ON TABLE `luggagecontroldata`.`client_flights` TO 'lugcontroluser'@'localhost';
GRANT ALL ON TABLE `luggagecontroldata`.`flights` TO 'lugcontroluser'@'localhost';
GRANT ALL ON TABLE `luggagecontroldata`.`luggage_flights` TO 'lugcontroluser'@'localhost';
>>>>>>> release/0.2

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
