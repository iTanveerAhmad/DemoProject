package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Data
@Entity
@Table(name = "Orders")
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int orderId;

    @Column(nullable = false)
    private Date orderDate;

    @Column
    private String status;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private AppUser appUser;
}
