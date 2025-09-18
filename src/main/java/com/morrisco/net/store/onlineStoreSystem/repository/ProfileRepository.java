package com.morrisco.net.store.onlineStoreSystem.repository;

import com.morrisco.net.store.onlineStoreSystem.entities.Profile;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.Repository;

public interface ProfileRepository extends CrudRepository<Profile, Integer> {
}