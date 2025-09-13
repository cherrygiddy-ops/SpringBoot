package com.morrisco.net.store.userRegistrationService;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
public class User {
    private String name ;
    private long id ;
    private String email;
    private String password;


    public User(String name, long id, String email, String password) {
        this.name = name;
        this.id = id;
        this.email = email;
        this.password = password;
    }


    public String getEmail() {
        return email;
    }

}
