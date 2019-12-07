-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `mydb` DEFAULT CHARACTER SET utf8 ;
USE `mydb` ;

-- -----------------------------------------------------
-- Table `mydb`.`Album`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`Album` (
  `id` INT NOT NULL,
  `nome` VARCHAR(150) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Playlist`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`Playlist` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(200) NULL,
  `descricao` VARCHAR(500) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Categoria`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`Categoria` (
  `nome` VARCHAR(50) NOT NULL,
  PRIMARY KEY (`nome`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Conteudo`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`Conteudo` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(100) NOT NULL,
  `tamanho` INT NOT NULL,
  `duracao` DECIMAL(6,2) NOT NULL,
  `autor` VARCHAR(100) NOT NULL,
  `album` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_Conteudo_Album1_idx` (`album` ASC) VISIBLE,
  CONSTRAINT `fk_Conteudo_Album1`
    FOREIGN KEY (`album`)
    REFERENCES `mydb`.`Album` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Cadastrado`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`Cadastrado` (
  `username` VARCHAR(100) NOT NULL,
  `email` VARCHAR(200) NULL,
  `password` VARCHAR(200) NULL,
  PRIMARY KEY (`username`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Conteudo_in_Playlist`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`Conteudo_in_Playlist` (
  `Conteudo_id` INT NOT NULL,
  `Playlist_id` INT NOT NULL,
  PRIMARY KEY (`Conteudo_id`, `Playlist_id`),
  INDEX `fk_Conteudo_has_Playlist_Playlist1_idx` (`Playlist_id` ASC) VISIBLE,
  INDEX `fk_Conteudo_has_Playlist_Conteudo1_idx` (`Conteudo_id` ASC) VISIBLE,
  CONSTRAINT `fk_Conteudo_has_Playlist_Conteudo1`
    FOREIGN KEY (`Conteudo_id`)
    REFERENCES `mydb`.`Conteudo` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Conteudo_has_Playlist_Playlist1`
    FOREIGN KEY (`Playlist_id`)
    REFERENCES `mydb`.`Playlist` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Conteudo_has_Categoria`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`Conteudo_has_Categoria` (
  `Conteudo_id` INT NOT NULL,
  `Categoria_nome` VARCHAR(50) NOT NULL,
  PRIMARY KEY (`Conteudo_id`, `Categoria_nome`),
  INDEX `fk_Conteudo_has_Categoria_Categoria1_idx` (`Categoria_nome` ASC) VISIBLE,
  INDEX `fk_Conteudo_has_Categoria_Conteudo1_idx` (`Conteudo_id` ASC) VISIBLE,
  CONSTRAINT `fk_Conteudo_has_Categoria_Conteudo1`
    FOREIGN KEY (`Conteudo_id`)
    REFERENCES `mydb`.`Conteudo` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Conteudo_has_Categoria_Categoria1`
    FOREIGN KEY (`Categoria_nome`)
    REFERENCES `mydb`.`Categoria` (`nome`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Administrador`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`Administrador` (
  `username` VARCHAR(100) NOT NULL,
  INDEX `fk_Administrador_Cadastrado1_idx` (`username` ASC) VISIBLE,
  PRIMARY KEY (`username`),
  CONSTRAINT `fk_Administrador_Cadastrado1`
    FOREIGN KEY (`username`)
    REFERENCES `mydb`.`Cadastrado` (`username`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Utilizador`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`Utilizador` (
  `username` VARCHAR(100) NOT NULL,
  INDEX `fk_Utilizador_Cadastrado1_idx` (`username` ASC) VISIBLE,
  PRIMARY KEY (`username`),
  CONSTRAINT `fk_Utilizador_Cadastrado1`
    FOREIGN KEY (`username`)
    REFERENCES `mydb`.`Cadastrado` (`username`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Pedidos`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`Pedidos` (
  `Utilizador_username` VARCHAR(100) NOT NULL,
  `Utilizador_username1` VARCHAR(100) NOT NULL,
  PRIMARY KEY (`Utilizador_username`, `Utilizador_username1`),
  INDEX `fk_Utilizador_has_Utilizador_Utilizador2_idx` (`Utilizador_username1` ASC) VISIBLE,
  INDEX `fk_Utilizador_has_Utilizador_Utilizador1_idx` (`Utilizador_username` ASC) VISIBLE,
  CONSTRAINT `fk_Utilizador_has_Utilizador_Utilizador1`
    FOREIGN KEY (`Utilizador_username`)
    REFERENCES `mydb`.`Utilizador` (`username`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Utilizador_has_Utilizador_Utilizador2`
    FOREIGN KEY (`Utilizador_username1`)
    REFERENCES `mydb`.`Utilizador` (`username`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Utilizador_has_Conteudo`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`Utilizador_has_Conteudo` (
  `Utilizador_username` VARCHAR(100) NOT NULL,
  `Conteudo_id` INT NOT NULL,
  PRIMARY KEY (`Utilizador_username`, `Conteudo_id`),
  INDEX `fk_Utilizador_has_Conteudo_Conteudo1_idx` (`Conteudo_id` ASC) VISIBLE,
  INDEX `fk_Utilizador_has_Conteudo_Utilizador1_idx` (`Utilizador_username` ASC) VISIBLE,
  CONSTRAINT `fk_Utilizador_has_Conteudo_Utilizador1`
    FOREIGN KEY (`Utilizador_username`)
    REFERENCES `mydb`.`Utilizador` (`username`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Utilizador_has_Conteudo_Conteudo1`
    FOREIGN KEY (`Conteudo_id`)
    REFERENCES `mydb`.`Conteudo` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Amigos`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`Amigos` (
  `Utilizador_username` VARCHAR(100) NOT NULL,
  `Utilizador_username1` VARCHAR(100) NOT NULL,
  PRIMARY KEY (`Utilizador_username`, `Utilizador_username1`),
  INDEX `fk_Utilizador_has_Utilizador_Utilizador4_idx` (`Utilizador_username1` ASC) VISIBLE,
  INDEX `fk_Utilizador_has_Utilizador_Utilizador3_idx` (`Utilizador_username` ASC) VISIBLE,
  CONSTRAINT `fk_Utilizador_has_Utilizador_Utilizador3`
    FOREIGN KEY (`Utilizador_username`)
    REFERENCES `mydb`.`Utilizador` (`username`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Utilizador_has_Utilizador_Utilizador4`
    FOREIGN KEY (`Utilizador_username1`)
    REFERENCES `mydb`.`Utilizador` (`username`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Utilizador_has_Playlist`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`Utilizador_has_Playlist` (
  `Utilizador_username` VARCHAR(100) NOT NULL,
  `Playlist_id` INT NOT NULL,
  PRIMARY KEY (`Utilizador_username`, `Playlist_id`),
  INDEX `fk_Utilizador_has_Playlist_Playlist1_idx` (`Playlist_id` ASC) VISIBLE,
  INDEX `fk_Utilizador_has_Playlist_Utilizador1_idx` (`Utilizador_username` ASC) VISIBLE,
  CONSTRAINT `fk_Utilizador_has_Playlist_Utilizador1`
    FOREIGN KEY (`Utilizador_username`)
    REFERENCES `mydb`.`Utilizador` (`username`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Utilizador_has_Playlist_Playlist1`
    FOREIGN KEY (`Playlist_id`)
    REFERENCES `mydb`.`Playlist` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
