package com.morrisco.net.store.onlineStoreSystem.repository;

import com.morrisco.net.store.onlineStoreSystem.entities.Product;
import org.springframework.data.repository.CrudRepository;

import java.math.BigDecimal;
import java.util.List;

public interface ProductRepository extends CrudRepository<Product, Long> {
   //String
    //select * from products where name like ?
    List<Product>findByName(String name);
    List<Product>findByNameLike(String name);
    List<Product>findByNameNotLike(String name);
    List<Product>findByNameContaining(String name);
    List<Product>findByNameStartingWith(String name);
    List<Product>findByNameEndingWith(String name);
    List<Product>findByNameEndingWithIgnoreCase(String name);

    //Numbers

    List<Product> findByPrice (BigDecimal price);
    List<Product> findByPriceGreaterThan (BigDecimal price);
    List<Product> findByPriceGreaterThanEqual (BigDecimal price);
    List<Product> findByPriceLessThanEqual (BigDecimal price);
    List<Product> findByPriceBetween (BigDecimal min,BigDecimal max);

    //Null
    List<Product> findByNameNull ();
    List<Product> findByNameNotNull ();

    //MultipleConditions
    //List<Product> findByNameNullDescriptionNull ();

    //sorting(Order By)
    List<Product> findTop5ByNameOrderByPriceDesc(String name);

    //Limit
    List<Product> findFirst5ByNameOrderByPriceDesc(String name);
}