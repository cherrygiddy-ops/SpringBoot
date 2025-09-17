CREATE TABLE IF NOT EXISTS `store`.`user_tags` (
  `tags_id` INT NOT NULL,
  `users_id` INT NOT NULL,
  PRIMARY KEY (`tags_id`, `users_id`),
  INDEX `fk_user_tags_users1_idx` (`users_id`,`tags_id` ASC) VISIBLE,
  CONSTRAINT `fk_user_tags_tags`
    FOREIGN KEY (`tags_id`)
    REFERENCES `store`.`tags` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_user_tags_users1`
    FOREIGN KEY (`users_id`)
    REFERENCES `store`.`users` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;