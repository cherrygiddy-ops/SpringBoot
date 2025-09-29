package com.morrisco.net.store.onlineStoreSystem.repositories;

import com.morrisco.net.store.onlineStoreSystem.entities.Addresses;
import org.springframework.data.repository.CrudRepository;

public interface AddressesRepository extends CrudRepository<Addresses, Integer> {
}