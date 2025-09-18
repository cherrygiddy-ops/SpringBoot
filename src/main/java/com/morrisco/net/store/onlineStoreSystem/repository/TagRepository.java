package com.morrisco.net.store.onlineStoreSystem.repository;

import com.morrisco.net.store.onlineStoreSystem.entities.Tag;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.Repository;

public interface TagRepository extends CrudRepository<Tag, Integer> {
}