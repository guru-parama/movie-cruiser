
SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema movieCruiser
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema movieCruiser
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `movieCruiser` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci ;
USE `movieCruiser` ;

-- -----------------------------------------------------
-- Table `movieCruiser`.`user`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `movieCruiser`.`user` (
  `us_id` INT NOT NULL AUTO_INCREMENT,
  `us_user_name` VARCHAR(60) NULL,
  `us_first_name` VARCHAR(60) NULL,
  `us_last_name` VARCHAR(60) NULL,
  `us_password` VARCHAR(200) NULL,
  PRIMARY KEY (`us_id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `movieCruiser`.`movie`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `movieCruiser`.`movie` (
  `mv_id` INT NOT NULL AUTO_INCREMENT,
  `mv_name` VARCHAR(100) NULL,
  `mv_box_office` BIGINT NULL,
  `mv_active` BOOLEAN,
  `mv_date_of_launch` DATE NULL,
  `mv_genre` VARCHAR(45) NULL,
  `mv_has_teaser` BOOLEAN,
  `mv_image` VARCHAR(500) NULL,
  PRIMARY KEY (`mv_id`))
ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `movieCruiser`.`genre`(
  `ge_id` INT NOT NULL AUTO_INCREMENT,
  `ge_name` VARCHAR(100) NULL,
  PRIMARY KEY (`ge_id`))
ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `movieCruiser`.`favorite`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `movieCruiser`.`favorite` (
  `fv_id` INT NOT NULL AUTO_INCREMENT,
  `fv_us_id` INT NULL,
  `fv_mv_id` INT NULL,
  PRIMARY KEY (`fv_id`),
  INDEX `fv_us_fk_idx` (`fv_us_id` ASC),
  INDEX `fv_mv_fk_idx` (`fv_mv_id` ASC),
  CONSTRAINT `fv_us_fk`
    FOREIGN KEY (`fv_us_id`)
    REFERENCES `movieCruiser`.`user` (`us_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fv_mv_fk`
    FOREIGN KEY (`fv_mv_id`)
    REFERENCES `movieCruiser`.`movie` (`mv_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `movieCruiser`.`role`(
	`ro_id` INT NOT NULL AUTO_INCREMENT,
    `ro_name` VARCHAR(45) NULL,
    PRIMARY KEY (`ro_id`))
ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `movieCruiser`.`user_role`(
	`ur_id` INT NOT NULL AUTO_INCREMENT,
    `ur_us_id` INT NULL,
    `ur_ro_id` INT NULL,
     PRIMARY KEY (`ur_id`),
    INDEX `ur_us_fk_idx` (`ur_us_id` ASC),
  INDEX `ur_ro_fk_idx` (`ur_ro_id` ASC),
  CONSTRAINT `ur_us_fk`
    FOREIGN KEY (`ur_us_id`)
    REFERENCES `movieCruiser`.`user` (`us_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `ur_ro_fk`
    FOREIGN KEY (`ur_ro_id`)
    REFERENCES `movieCruiser`.`role` (`ro_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
    ENGINE = InnoDB;
SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
