-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema MediaCenter
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema MediaCenter
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `MediaCenter` DEFAULT CHARACTER SET utf8 ;
-- -----------------------------------------------------
-- Schema MediaCenter
-- -----------------------------------------------------
USE `MediaCenter` ;

-- -----------------------------------------------------
-- Table `MediaCenter`.`Album`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `MediaCenter`.`Album` (
  `Album_id` INT NOT NULL,
  `nome` VARCHAR(150) NOT NULL,
  PRIMARY KEY (`Album_id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `MediaCenter`.`Playlist`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `MediaCenter`.`Playlist` (
  `Playlist_id` INT NOT NULL,
  `nome` VARCHAR(200) NULL,
  `descricao` VARCHAR(500) NULL,
  PRIMARY KEY (`Playlist_id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `MediaCenter`.`Categoria`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `MediaCenter`.`Categoria` (
  `Categoria_nome` VARCHAR(50) NOT NULL,
  PRIMARY KEY (`Categoria_nome`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `MediaCenter`.`Conteudo`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `MediaCenter`.`Conteudo` (
  `Conteudo_id` INT NOT NULL,
  `nome` VARCHAR(100) NOT NULL,
  `tamanho` INT NOT NULL,
  `duracao` BIGINT NOT NULL,
  `autor` VARCHAR(100) NOT NULL,
  `path` VARCHAR(100) NOT NULL,
  `Album_id` INT NULL,
  PRIMARY KEY (`Conteudo_id`),
  INDEX `fk_Conteudo_Album1_idx` (`Album_id` ASC) VISIBLE,
  CONSTRAINT `fk_Conteudo_Album1`
    FOREIGN KEY (`Album_id`)
    REFERENCES `MediaCenter`.`Album` (`Album_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `MediaCenter`.`Cadastrado`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `MediaCenter`.`Cadastrado` (
  `username` VARCHAR(100) NOT NULL,
  `email` VARCHAR(200) NULL,
  `password` VARCHAR(200) NULL,
  PRIMARY KEY (`username`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `MediaCenter`.`Conteudo_in_Playlist`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `MediaCenter`.`Conteudo_in_Playlist` (
  `Conteudo_id` INT NOT NULL,
  `Playlist_id` INT NOT NULL,
  PRIMARY KEY (`Conteudo_id`, `Playlist_id`),
  INDEX `fk_Conteudo_has_Playlist_Playlist1_idx` (`Playlist_id` ASC) VISIBLE,
  INDEX `fk_Conteudo_has_Playlist_Conteudo1_idx` (`Conteudo_id` ASC) VISIBLE,
  CONSTRAINT `fk_Conteudo_has_Playlist_Conteudo1`
    FOREIGN KEY (`Conteudo_id`)
    REFERENCES `MediaCenter`.`Conteudo` (`Conteudo_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Conteudo_has_Playlist_Playlist1`
    FOREIGN KEY (`Playlist_id`)
    REFERENCES `MediaCenter`.`Playlist` (`Playlist_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `MediaCenter`.`Conteudo_has_Categoria`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `MediaCenter`.`Conteudo_has_Categoria` (
  `Conteudo_id` INT NOT NULL,
  `Categoria_nome` VARCHAR(50) NOT NULL,
  PRIMARY KEY (`Conteudo_id`, `Categoria_nome`),
  INDEX `fk_Conteudo_has_Categoria_Categoria1_idx` (`Categoria_nome` ASC) VISIBLE,
  INDEX `fk_Conteudo_has_Categoria_Conteudo1_idx` (`Conteudo_id` ASC) VISIBLE,
  CONSTRAINT `fk_Conteudo_has_Categoria_Conteudo1`
    FOREIGN KEY (`Conteudo_id`)
    REFERENCES `MediaCenter`.`Conteudo` (`Conteudo_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Conteudo_has_Categoria_Categoria1`
    FOREIGN KEY (`Categoria_nome`)
    REFERENCES `MediaCenter`.`Categoria` (`Categoria_nome`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `MediaCenter`.`Administrador`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `MediaCenter`.`Administrador` (
  `username` VARCHAR(100) NOT NULL,
  INDEX `fk_Administrador_Cadastrado1_idx` (`username` ASC) VISIBLE,
  PRIMARY KEY (`username`),
  CONSTRAINT `fk_Administrador_Cadastrado1`
    FOREIGN KEY (`username`)
    REFERENCES `MediaCenter`.`Cadastrado` (`username`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `MediaCenter`.`Utilizador`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `MediaCenter`.`Utilizador` (
  `username` VARCHAR(100) NOT NULL,
  INDEX `fk_Utilizador_Cadastrado1_idx` (`username` ASC) VISIBLE,
  PRIMARY KEY (`username`),
  CONSTRAINT `fk_Utilizador_Cadastrado1`
    FOREIGN KEY (`username`)
    REFERENCES `MediaCenter`.`Cadastrado` (`username`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `MediaCenter`.`Pedidos`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `MediaCenter`.`Pedidos` (
  `username` VARCHAR(100) NOT NULL,
  `username1` VARCHAR(100) NOT NULL,
  PRIMARY KEY (`username`, `username1`),
  INDEX `fk_Utilizador_has_Utilizador_Utilizador2_idx` (`username1` ASC) VISIBLE,
  INDEX `fk_Utilizador_has_Utilizador_Utilizador1_idx` (`username` ASC) VISIBLE,
  CONSTRAINT `fk_Utilizador_has_Utilizador_Utilizador1`
    FOREIGN KEY (`username`)
    REFERENCES `MediaCenter`.`Utilizador` (`username`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Utilizador_has_Utilizador_Utilizador2`
    FOREIGN KEY (`username1`)
    REFERENCES `MediaCenter`.`Utilizador` (`username`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `MediaCenter`.`Utilizador_has_Conteudo`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `MediaCenter`.`Utilizador_has_Conteudo` (
  `username` VARCHAR(100) NOT NULL,
  `Conteudo_id` INT NOT NULL,
  PRIMARY KEY (`username`, `Conteudo_id`),
  INDEX `fk_Utilizador_has_Conteudo_Conteudo1_idx` (`Conteudo_id` ASC) VISIBLE,
  INDEX `fk_Utilizador_has_Conteudo_Utilizador1_idx` (`username` ASC) VISIBLE,
  CONSTRAINT `fk_Utilizador_has_Conteudo_Utilizador1`
    FOREIGN KEY (`username`)
    REFERENCES `MediaCenter`.`Utilizador` (`username`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Utilizador_has_Conteudo_Conteudo1`
    FOREIGN KEY (`Conteudo_id`)
    REFERENCES `MediaCenter`.`Conteudo` (`Conteudo_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `MediaCenter`.`Amigos`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `MediaCenter`.`Amigos` (
  `username` VARCHAR(100) NOT NULL,
  `username1` VARCHAR(100) NOT NULL,
  PRIMARY KEY (`username`, `username1`),
  INDEX `fk_Utilizador_has_Utilizador_Utilizador4_idx` (`username1` ASC) VISIBLE,
  INDEX `fk_Utilizador_has_Utilizador_Utilizador3_idx` (`username` ASC) VISIBLE,
  CONSTRAINT `fk_Utilizador_has_Utilizador_Utilizador3`
    FOREIGN KEY (`username`)
    REFERENCES `MediaCenter`.`Utilizador` (`username`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Utilizador_has_Utilizador_Utilizador4`
    FOREIGN KEY (`username1`)
    REFERENCES `MediaCenter`.`Utilizador` (`username`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `MediaCenter`.`Utilizador_has_Playlist`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `MediaCenter`.`Utilizador_has_Playlist` (
  `username` VARCHAR(100) NOT NULL,
  `Playlist_id` INT NOT NULL,
  PRIMARY KEY (`username`, `Playlist_id`),
  INDEX `fk_Utilizador_has_Playlist_Playlist1_idx` (`Playlist_id` ASC) VISIBLE,
  INDEX `fk_Utilizador_has_Playlist_Utilizador1_idx` (`username` ASC) VISIBLE,
  CONSTRAINT `fk_Utilizador_has_Playlist_Utilizador1`
    FOREIGN KEY (`username`)
    REFERENCES `MediaCenter`.`Utilizador` (`username`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Utilizador_has_Playlist_Playlist1`
    FOREIGN KEY (`Playlist_id`)
    REFERENCES `MediaCenter`.`Playlist` (`Playlist_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
