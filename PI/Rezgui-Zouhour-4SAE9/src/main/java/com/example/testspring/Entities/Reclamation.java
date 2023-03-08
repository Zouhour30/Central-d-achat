package com.example.testspring.Entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;


@Entity
@Getter
@Setter
@AllArgsConstructor

@RequiredArgsConstructor

public class Reclamation implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idReclamation;
    private String DescriptionReclamation;
    private String imageReclamation;
    @Temporal(TemporalType.DATE)
    @Column(name = "dateReclamation", nullable = false)
    private Date dateReclamation;
    @ManyToOne()
    private Orders orders;
}
