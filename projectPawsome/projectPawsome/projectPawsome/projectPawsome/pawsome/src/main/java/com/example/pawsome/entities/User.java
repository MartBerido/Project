package com.example.pawsome.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, length = 45)
    private String email;

    @Column(length = 15, nullable = false)
    private String password;

    @Column(length = 45, nullable = false, name = "first_name")
    private String firstName;

    @Column(length = 45, nullable = false, name = "last_name")
    private String lastName;

    @Column(length = 10, nullable = false, name = "phone")
    private String phone;

    @Column(length = 45, nullable = false, name = "street")
    private String street;

    @Column(length = 45, nullable = false, name = "city")
    private String city;

    @Column(length = 6, nullable = false, name = "postal_code")
    private String postalCode;

    @Column(length = 20, nullable = false, name = "account")
    private String account;

    @Column(nullable = false)
    private boolean enabled;

}
