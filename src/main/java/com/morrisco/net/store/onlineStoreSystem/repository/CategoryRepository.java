package com.morrisco.net.store.onlineStoreSystem.repository;

import com.morrisco.net.store.onlineStoreSystem.entities.Category;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.Repository;

public interface CategoryRepository extends CrudRepository<Category, Byte> {
}