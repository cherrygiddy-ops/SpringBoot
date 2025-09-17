CREATE TABLE IF NOT EXISTS `store`.`products` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(255) NULL,
  `price` DECIMAL(10,2) NULL,
  `category_id` TINYINT NULL,
  INDEX `fk_products_category_idx` (`category_id` ASC) VISIBLE,
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_category_id`
    FOREIGN KEY (`category_id`)
    REFERENCES `store`.`categories` (`id`)
    ON DELETE RESTRICT
    ON UPDATE CASCADE)
    ENGINE = InnoDB;