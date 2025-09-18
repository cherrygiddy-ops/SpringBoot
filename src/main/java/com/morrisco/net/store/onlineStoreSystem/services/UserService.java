package com.morrisco.net.store.onlineStoreSystem.services;

import com.morrisco.net.store.onlineStoreSystem.entities.Addresses;
import com.morrisco.net.store.onlineStoreSystem.entities.User;
import com.morrisco.net.store.onlineStoreSystem.repository.AddressesRepository;
import com.morrisco.net.store.onlineStoreSystem.repository.ProfileRepository;
import com.morrisco.net.store.onlineStoreSystem.repository.UserRepository;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class UserService {
    private UserRepository userRepository;
    private ProfileRepository profileRepository;
    private EntityManager entityManager;
    private AddressesRepository addressesRepository;

    //@Transactional
    public void showEntityState(){
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
    @Transactional
    public void showRelatedEntities(){
        var address = addressesRepository.findById(1).orElseThrow();
        System.out.println(address.getState());
    }

    public void persistRelatedEntities_SaveToDB(){
        var user = User.builder()
                .name("a")
                .email("b")
                .password("c")
                .build();
        var address= Addresses.builder()
                .city("a")
                .state("c")
                .zipCode("c")
                .street("s")
                .build();

        user.addAddress(address);

        userRepository.save(user);

    }

    public void deleteRelatedEntities(){
        userRepository.deleteById(3);
    }


    @Transactional
    public void deleteChildRecord(){
        var user =userRepository.findById(7).orElseThrow();
        var address=user.getAddresses().get(0);
        user.removeAddress(address);
        userRepository.save(user);
    }

}
