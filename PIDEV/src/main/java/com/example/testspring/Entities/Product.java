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

public class Product implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idProduct;
    private  String ProductName;
    private float priceProduct;
    private int quantityProduct;
    private String descriptionProduct;

    @ManyToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    @JoinTable(name="Product_images",
            joinColumns =   {
                    @JoinColumn(name="idProduct")
            },
            inverseJoinColumns = {
                    @JoinColumn(name="image_id")
            }

    )
    private Set<ImageModel> images;
    @ManyToOne
    private User user ;

    @OneToMany(cascade = CascadeType.ALL,
            mappedBy="product")
    private Set<Tender> tenders;

    @ManyToOne(cascade = CascadeType.ALL)
    private Basket basket;
      @JsonIgnore
    @ManyToOne
    private Category category;





}
