package com.morrisco.net.store.onlineStoreSystem.services;

import com.morrisco.net.store.onlineStoreSystem.entities.User;
import com.morrisco.net.store.onlineStoreSystem.repository.UserRepository;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class UnderstandingState {
    private UserRepository userRepository;
    private EntityManager entityManager;

    //@Transactional
    public void show(){
        var user = User.builder()
                .name("a")
                .email("b")
                .password("c")
                .build();
     if (!entityManager.contains(user))
         System.out.println("Transient/Detached");
     else
         System.out.println("Persistence");

        userRepository.save(user);

        if (!entityManager.contains(user))
            System.out.println("Transient/Detached");
        else
            System.out.println("Persistence");
    }
}
