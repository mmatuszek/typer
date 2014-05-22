delimiter $$

CREATE DATABASE `typer` /*!40100 DEFAULT CHARACTER SET latin1 */$$

USE typer$$

CREATE  TABLE `typer`.`users` (
  `user_id` INT NOT NULL AUTO_INCREMENT ,
  `username` VARCHAR(45) NOT NULL ,
  `password` VARCHAR(45) NOT NULL ,
  `first_name` VARCHAR(45) NOT NULL ,
  `last_name` VARCHAR(45) NOT NULL ,
  PRIMARY KEY (`user_id`) ,
  UNIQUE INDEX `user_id_UNIQUE` (`user_id` ASC) );
  
CREATE TABLE 'typer'.'matches' (
  `match_id` int(11) NOT NULL AUTO_INCREMENT,
  `score_home` int(11) DEFAULT NULL,
  `score_away` int(11) DEFAULT NULL,
  `datetime` datetime NOT NULL,
  `home_team` varchar(45) NOT NULL,
  `away_team` varchar(45) NOT NULL,
  PRIMARY KEY (`match_id`),
  UNIQUE KEY `match_id_UNIQUE` (`match_id`));
  
CREATE  TABLE `typer`.`bets` (
  `bet_id` INT NOT NULL AUTO_INCREMENT ,
  `user_id` INT NOT NULL ,
  `score_home` INT NOT NULL ,
  `score_away` INT NOT NULL ,
  `match_id` INT NOT NULL ,
  PRIMARY KEY (`bet_id`) ,
  UNIQUE INDEX `bet_id_UNIQUE` (`bet_id` ASC) ,
  INDEX `user_id_fk_idx` (`user_id` ASC) ,
  INDEX `match_id_fk_idx` (`match_id` ASC) ,
  CONSTRAINT `user_id_fk`
    FOREIGN KEY (`user_id` )
    REFERENCES `typer`.`users` (`user_id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `match_id_fk`
    FOREIGN KEY (`match_id` )
    REFERENCES `typer`.`matches` (`match_id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);