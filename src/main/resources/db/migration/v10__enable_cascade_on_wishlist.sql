ALTER TABLE `store`.`wishlist`
DROP FOREIGN KEY `fk_user_products_products`;
ALTER TABLE `store`.`wishlist`
ADD CONSTRAINT `fk_user_products_products`
  FOREIGN KEY (`products_id`)
  REFERENCES `store`.`products` (`id`)
  ON DELETE CASCADE
  ON UPDATE CASCADE;