ALTER TABLE `store`.`users`
ADD COLUMN `state` VARCHAR(45) NOT NULL AFTER `password`;