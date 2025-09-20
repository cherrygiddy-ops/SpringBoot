package com.morrisco.net.store.onlineStoreSystem.repository;

import com.morrisco.net.store.onlineStoreSystem.entities.Category;
import com.morrisco.net.store.onlineStoreSystem.entities.Product;

import java.math.BigDecimal;
import java.util.List;

public interface ProductCriteriaRepository {
    List<Product> findProductsByCriteria(String name , BigDecimal minPrice, BigDecimal maxPrice, Category category);
}
