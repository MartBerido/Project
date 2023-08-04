package com.example.pawsome.entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "dogs")
public class Dog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 30, nullable = false, name = "dog_name")
    private String dogName;

    @Column(length = 30, nullable = false, name = "dog_breed")
    private String dogBreed;

    @Column(length = 2, nullable = false, name = "dog_age")
    private Integer dogAge;

    @Column(nullable = false)
    private boolean available;

    @Column(nullable = false)
    private boolean payed;

}
