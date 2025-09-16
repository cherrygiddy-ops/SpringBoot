package com.morrisco.net.store.onlineStoreSystem.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor //jpa and hibernate expect default constructor  but here java initial this fields  so we need to apply another annotaion call
                    //@NoArgsConstructor to return back to default constructor
@NoArgsConstructor
@Builder //used for building object step by step
@Entity
@Table(name = "users")
@ToString
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)//used to specify how ids are generated i.e primary keys
    @Column(name = "id")
    private Integer id;

    @Column(nullable = false,name = "name")
    private String name;

    @Column(nullable = false,name = "email")
    private String email;

    @Column(nullable = false,name = "password")
    private String password;

    @OneToMany(mappedBy = "user")//telling hibernate who is the owner of the relationship
    @Builder.Default //telling builder annotation to include new ArrayList<>(); when building an object
    private List<Addresses> addresses = new ArrayList<>();

    public void addAddress(Addresses address){
        addresses.add(address);
        address.setUser(this);
    }

    public void removeAddress(Addresses address){
        addresses.remove(address);
        address.setUser(null);
    }
}
