package com.example.pawsome.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "payment")
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 16, nullable = false, name = "card_number")
    private String creditCardNumber;
    @Column(length = 3, nullable = false, name = "month_exp")
    private String expirationMonth;
    @Column(length = 5, nullable = false, name = "year_exp")
    private String expirationYear;
    @Column(length = 4, nullable = false, name = "cvc")
    private String cvc;
    @Column(nullable = false, name = "amount")
    private double amount;
}
