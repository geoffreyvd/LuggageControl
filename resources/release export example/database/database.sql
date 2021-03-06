SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

CREATE SCHEMA IF NOT EXISTS `luggagecontroldata` DEFAULT CHARACTER SET utf8 ;
USE `luggagecontroldata` ;

-- -----------------------------------------------------
-- Table `luggagecontroldata`.`customer`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `luggagecontroldata`.`customer` (
  `customer_id` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `firstname` VARCHAR(45) NULL DEFAULT NULL,
  `surname` VARCHAR(45) NULL DEFAULT NULL,
  `email` VARCHAR(128) NULL,
  `cellphone` VARCHAR(45) NULL DEFAULT NULL,
  `birthday` DATE NULL DEFAULT NULL,
  `gender` VARCHAR(11) NULL DEFAULT NULL,
  `adress` VARCHAR(45) NULL DEFAULT NULL,
  `postcode` VARCHAR(45) NULL DEFAULT NULL,
  PRIMARY KEY (`customer_id`),
  UNIQUE INDEX `id_UNIQUE` (`customer_id` ASC))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `luggagecontroldata`.`flight`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `luggagecontroldata`.`flight` (
  `flight_id` INT(10) UNSIGNED NOT NULL,
  `origin` VARCHAR(45) NULL DEFAULT NULL,
  `destination` VARCHAR(45) NULL DEFAULT NULL,
  `departure` DATETIME NULL DEFAULT NULL,
  `arrival` DATETIME NULL DEFAULT NULL,
  UNIQUE INDEX `id_UNIQUE` (`flight_id` ASC),
  PRIMARY KEY (`flight_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `luggagecontroldata`.`customer_flight`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `luggagecontroldata`.`customer_flight` (
  `flight_id` INT(10) UNSIGNED NOT NULL,
  `customer_id` INT(10) UNSIGNED NOT NULL,
  PRIMARY KEY (`flight_id`, `customer_id`),
  INDEX `fk_client_flights_clients1_idx` (`customer_id` ASC),
  CONSTRAINT `fk_client_flights_clients1`
    FOREIGN KEY (`customer_id`)
    REFERENCES `luggagecontroldata`.`customer` (`customer_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_client_flights_flights`
    FOREIGN KEY (`flight_id`)
    REFERENCES `luggagecontroldata`.`flight` (`flight_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `luggagecontroldata`.`luggage`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `luggagecontroldata`.`luggage` (
  `luggage_id` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `location` VARCHAR(45) NULL DEFAULT NULL,
  `color` VARCHAR(45) NULL DEFAULT NULL,
  `weight` INT NULL DEFAULT NULL,
  `size` VARCHAR(6) NULL DEFAULT NULL,
  `description` VARCHAR(256) NULL DEFAULT NULL,
  `status` VARCHAR(11) NULL DEFAULT NULL,
  `image` LONGTEXT NULL DEFAULT NULL,
  PRIMARY KEY (`luggage_id`),
  UNIQUE INDEX `id_UNIQUE` (`luggage_id` ASC))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `luggagecontroldata`.`customer_luggage`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `luggagecontroldata`.`customer_luggage` (
  `customer_id` INT(10) UNSIGNED NOT NULL,
  `luggage_id` INT(10) UNSIGNED NOT NULL,
  INDEX `fk_client_luggage_clients1_idx` (`customer_id` ASC),
  INDEX `fk_client_luggage_luggage1_idx` (`luggage_id` ASC),
  PRIMARY KEY (`customer_id`, `luggage_id`),
  CONSTRAINT `fk_client_luggage_clients1`
    FOREIGN KEY (`customer_id`)
    REFERENCES `luggagecontroldata`.`customer` (`customer_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_client_luggage_luggage1`
    FOREIGN KEY (`luggage_id`)
    REFERENCES `luggagecontroldata`.`luggage` (`luggage_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `luggagecontroldata`.`luggage_flight`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `luggagecontroldata`.`luggage_flight` (
  `flight_id` INT(10) UNSIGNED NOT NULL,
  `luggage_id` INT(10) UNSIGNED NOT NULL,
  INDEX `fk_luggage_flights_flights1_idx` (`flight_id` ASC),
  INDEX `fk_luggage_flights_luggage1_idx` (`luggage_id` ASC),
  PRIMARY KEY (`flight_id`, `luggage_id`),
  CONSTRAINT `fk_luggage_flights_flights1`
    FOREIGN KEY (`flight_id`)
    REFERENCES `luggagecontroldata`.`flight` (`flight_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_luggage_flights_luggage1`
    FOREIGN KEY (`luggage_id`)
    REFERENCES `luggagecontroldata`.`luggage` (`luggage_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `luggagecontroldata`.`luggage_lost_found`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `luggagecontroldata`.`luggage_lost_found` (
  `luggage_lost_id` INT(10) UNSIGNED NOT NULL,
  `luggage_found_id` INT(10) UNSIGNED NOT NULL,
  `status` VARCHAR(25) NULL DEFAULT NULL,
  INDEX `fk_table1_luggage1_idx` (`luggage_lost_id` ASC),
  INDEX `fk_table1_luggage2_idx` (`luggage_found_id` ASC),
  CONSTRAINT `fk_table1_luggage1`
    FOREIGN KEY (`luggage_lost_id`)
    REFERENCES `luggagecontroldata`.`luggage` (`luggage_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_table1_luggage2`
    FOREIGN KEY (`luggage_found_id`)
    REFERENCES `luggagecontroldata`.`luggage` (`luggage_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `luggagecontroldata`.`user`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `luggagecontroldata`.`user` (
  `user_id` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `username` VARCHAR(45) NULL DEFAULT NULL,
  `password` VARCHAR(1024) NULL DEFAULT NULL,
  `salt` VARCHAR(16) NULL DEFAULT NULL,
  `email` VARCHAR(128) NULL,
  `firstname` VARCHAR(45) NULL DEFAULT NULL,
  `surname` VARCHAR(45) NULL DEFAULT NULL,
  `cellphone` VARCHAR(45) NULL DEFAULT NULL,
  `birthday` DATE NULL DEFAULT NULL,
  `gender` VARCHAR(11) NULL DEFAULT NULL,
  `nationality` VARCHAR(45) NULL DEFAULT NULL,
  `adress` VARCHAR(45) NULL DEFAULT NULL,
  `city` VARCHAR(45) NULL DEFAULT NULL,
  `postcode` VARCHAR(45) NULL DEFAULT NULL,
  `image` LONGTEXT NULL DEFAULT NULL,
  `permission` INT(1) NULL DEFAULT NULL,
  PRIMARY KEY (`user_id`),
  UNIQUE INDEX `id_UNIQUE` (`user_id` ASC),
  UNIQUE INDEX `username_UNIQUE` (`username` ASC),
  UNIQUE INDEX `email_UNIQUE` (`email` ASC))
ENGINE = InnoDB
AUTO_INCREMENT = 7
DEFAULT CHARACTER SET = utf8;

CREATE USER 'lugcontroluser'@'localhost' IDENTIFIED BY 'verysecurepassword';

GRANT ALL ON `luggagecontroldata`.* TO 'lugcontroluser'@'localhost';

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
