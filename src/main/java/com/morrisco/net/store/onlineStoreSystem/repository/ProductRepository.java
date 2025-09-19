package com.morrisco.net.store.onlineStoreSystem.repository;

import com.morrisco.net.store.onlineStoreSystem.dtos.ProductSummary;
import com.morrisco.net.store.onlineStoreSystem.dtos.ProductSummaryUsingClass;
import com.morrisco.net.store.onlineStoreSystem.entities.Category;
import com.morrisco.net.store.onlineStoreSystem.entities.Product;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

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

    //Using jpql language not sticking to sql
    //Find products whose prices btwn given ranges order by Name
    @Query("select p from Product p join p.category where p.price between ?1 and ?2 order by p.name DESC")
    List<Product> findProducts(@Param("1") BigDecimal min, @Param("2") BigDecimal max);

    //Using sql language
    //Find products whose prices btwn given ranges order by Name
    @Query(value = "select * from products p where p.price between :min and :max order by p.name DESC",nativeQuery = true)
    List<Product> findProductsByName(@Param("min") BigDecimal min, @Param("max") BigDecimal max);

    @Query(value = "select count(*) from products p where p.price between :min and :max order by p.name DESC",nativeQuery = true)
    long countItems(@Param("min") BigDecimal min, @Param("max") BigDecimal max);

    @Modifying
    @Query(value = "update products set price = :newPrice where category_id = :categoryId ",nativeQuery = true)
    void updatePriceByCategory(@Param("newPrice") BigDecimal newPrice, @Param("categoryId") Byte categoryId);

    //@Query("select p.id,p.name from Product p where p.category = :category")
    List<ProductSummaryUsingClass> findByCategory(@Param("category") Category category);
}