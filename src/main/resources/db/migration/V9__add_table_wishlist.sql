CREATE TABLE IF NOT EXISTS `store`.`wishlist` (
  `users_id` INT NOT NULL,
  `products_id` BIGINT NOT NULL,
  PRIMARY KEY (`users_id`, `products_id`),
  INDEX `fk_user_products_users1_idx` (`users_id`,`products_id` ASC) VISIBLE,
  CONSTRAINT `fk_user_products_products`
    FOREIGN KEY (`products_id`)
    REFERENCES `store`.`products` (`id`)
    ON DELETE RESTRICT
    ON UPDATE CASCADE,
  CONSTRAINT `fk_user_products_users1`
    FOREIGN KEY (`users_id`)
    REFERENCES `store`.`users` (`id`)
    ON DELETE RESTRICT
    ON UPDATE CASCADE)
ENGINE = InnoDB;