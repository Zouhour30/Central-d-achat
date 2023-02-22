package com.example.testspring.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor

@RequiredArgsConstructor
public class Basket implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idBasket;
    private float totalPrice;
    @JsonIgnore
    @OneToMany(cascade =CascadeType.ALL)
    private Set<Orders> orders;

    @OneToMany(cascade = CascadeType.ALL, mappedBy="basket")
    private Set<Product> products;

}
