package com.morrisco.net.store.userRegistrationService;

import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class InMemoryUserRepository implements UserRepository{
    private final Map<String,User>map = new HashMap<>();
    @Override
    public void save(User user) {
        map.putIfAbsent(user.getEmail(),user);
        System.out.println(map.keySet());
    }
    @Override
    public User findByEmail(String mail){
        return map.getOrDefault(mail,null);
    }
}
