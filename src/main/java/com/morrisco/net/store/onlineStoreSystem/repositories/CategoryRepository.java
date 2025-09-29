package com.morrisco.net.store.onlineStoreSystem.repositories;

import com.morrisco.net.store.onlineStoreSystem.entities.Category;
import org.springframework.data.repository.CrudRepository;

public interface CategoryRepository extends CrudRepository<Category, Byte> {
}