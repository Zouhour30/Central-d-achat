package com.example.testspring.Entities;

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
@Table(name="product")

public class Product implements Serializable {
    @Id
    @Column(name="Id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idProduct;
    @Column(name="Price")
    private float priceProduct;
    @Column(name="Stock")
    private int quantityProduct;
    @Column(name="Description")
    private String descriptionProduct;
    @Column(name="Image")
    private  String ImageProduct;
    @ManyToOne
    private User user ;

    @OneToMany(cascade = CascadeType.ALL,
            mappedBy="product")
    private Set<Tender> tenders;

    @ManyToOne(cascade = CascadeType.ALL)
    private Basket basket;

    //LAZY indique à Hibernate de ne récupérer que les entités associées de la base de données lorsque vous utilisez la relation
    @ManyToOne(fetch =  FetchType.LAZY)
    @JoinColumn(name="category-FK", nullable = false)
    private Category category;

}
