package com.morrisco.net.store.onlineStoreSystem.repository;

import com.morrisco.net.store.onlineStoreSystem.dtos.UserSummary;
import com.morrisco.net.store.onlineStoreSystem.entities.Profile;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProfileRepository extends CrudRepository<Profile, Integer> {



}