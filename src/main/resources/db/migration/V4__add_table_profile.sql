CREATE TABLE IF NOT EXISTS `store`.`profiles` (
  `users_id` INT NOT NULL AUTO_INCREMENT,
  `bio` VARCHAR(45) NULL,
  `phone_number` VARCHAR(45) NULL,
  `date_of_birth` VARCHAR(45) NULL,
  `loyalty_points` INT NULL DEFAULT 0,
  INDEX `fk_profiles_users1_idx` (`users_id` ASC) VISIBLE,
  PRIMARY KEY (`users_id`),
  CONSTRAINT `fk_profiles_users1`
    FOREIGN KEY (`users_id`)
    REFERENCES `store`.`users` (`id`)
    ON DELETE NO ACTION
    ON UPDATE CASCADE)
    ENGINE = InnoDB;