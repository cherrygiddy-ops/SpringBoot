DELIMITER $$

CREATE PROCEDURE `findProductsByPrice` (
maxPrice DECIMAL(10,2),
minPrice DECIMAL(10,2)
)
BEGIN
 SELECT ID,NAME,PRICE,CATEGORY_id from Products where price between minPrice and maxPrice order by name;
END $$

DELIMITER ;