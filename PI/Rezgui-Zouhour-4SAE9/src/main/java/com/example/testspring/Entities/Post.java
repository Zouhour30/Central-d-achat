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

public class Post implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPost;
    private String description;
    private String image;
    @Temporal(TemporalType.DATE)
    @Column(name = "datePublication", nullable = false)
    private Date datePublication;

    @ManyToOne(cascade = CascadeType.ALL)
    private Forum forum;


}
