package com.morrisco.net.store.onlineStoreSystem.repositories;

import com.morrisco.net.store.onlineStoreSystem.entities.Cart;
import com.morrisco.net.store.onlineStoreSystem.entities.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface CartItemRepository extends JpaRepository<CartItem,Long> {
    Optional<Cart> findByCartId (UUID id);
}
