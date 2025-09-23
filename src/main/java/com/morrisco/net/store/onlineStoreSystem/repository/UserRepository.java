package com.morrisco.net.store.onlineStoreSystem.repository;

import com.morrisco.net.store.onlineStoreSystem.dtos.UserSummary;
import com.morrisco.net.store.onlineStoreSystem.entities.User;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {

    //@Query("select u.email from User u where u.email = ?1")
    @EntityGraph(attributePaths = {"tags","addresses"}) //changing the relationship attributes btwn tags and user to be ager loading
    Optional<User>findByEmail(String email);

    @EntityGraph(attributePaths = "addresses")
    @Query("select u from User u")
    List<User>findAllWithAddresses();

    @Query("select u.id as id,u.email as email from User u where u.profile.loyaltyPoints > :loyaltyPoints order by u.email")
    List<UserSummary> findLoyalUsers(@Param("loyaltyPoints") int loyaltyPoints);
}