package com.morrisco.net.store.onlineStoreSystem.services;

import com.morrisco.net.store.onlineStoreSystem.entities.Addresses;
import com.morrisco.net.store.onlineStoreSystem.entities.Profile;
import com.morrisco.net.store.onlineStoreSystem.entities.User;
import com.morrisco.net.store.onlineStoreSystem.repository.AddressesRepository;
import com.morrisco.net.store.onlineStoreSystem.repository.ProfileRepository;
import com.morrisco.net.store.onlineStoreSystem.repository.UserRepository;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

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

//    @Transactional
//    public void fetchByEmail(){
//        //In Here we have N+1 PROBLEM Since for every user Record Hibernate sents another request to db to fetch users address
//        //hence Eager loading can solve this problem by Loading address and users at ounce
//       var users= userRepository.findAllWithAddresses();
//       for (var user:users){
//        System.out.println(user);
//        user.getAddresses().forEach(System.out::println
//                );
//       }
//    }

    @Transactional
    public void persistUserAndProfile(){
//        var user = User.builder()
//                .name("a")
//                .email("morris")
//                .password("c")
//                .build();
        var user2=userRepository.findById(10).orElseThrow().getId();
        //System.out.println(user2.getId());
        var profile = Profile.builder()
                .id(user2)
                .bio("s")
                .loyaltyPoints(5)
                .phoneNumber("0706781524")
                .dateOfBirth(LocalDate.parse("1996-10-10"))
                .build();
        //userRepository.save(user);
        profileRepository.save(profile);

    }

//    @Transactional
//    public void printEmails(int points){
//        userRepository.findLoyalUsers(points).forEach(profile -> {
//            System.out.println(profile.getId());
//            System.out.println(profile.getEmail());
//        });
//    }

}
