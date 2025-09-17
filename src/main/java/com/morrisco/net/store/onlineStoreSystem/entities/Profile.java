package com.morrisco.net.store.onlineStoreSystem.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.Date;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "profiles")
@Entity
@ToString
public class Profile {
    @Id
    @Column(name = "users_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "bio")
    private String bio;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "date_of_birth")
    private LocalDate dateOfBirth;

    @Column(name = "loyalty_points")
    private String loyaltyPoints;

    @OneToOne
    @JoinColumn(name = "users_id") //this is the foreignKey Column
    @MapsId //TELLING Hibernate to use the same column as foreign key and primary key of the entity
    @ToString.Exclude
    private User user;
}
