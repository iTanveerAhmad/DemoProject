package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "Profile")
public class Profile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int profileId;

    @Column
    private String bio;

    @Column
    private String profilePictureUrl;

    @OneToOne
    @JoinColumn(name = "user_id", unique = true)
    private AppUser appUser;
}