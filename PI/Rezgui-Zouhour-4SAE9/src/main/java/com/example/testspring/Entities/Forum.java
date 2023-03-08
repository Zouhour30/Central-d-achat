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
public class Forum implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idForum;


    @OneToMany(cascade = CascadeType.ALL,
            mappedBy="forum")
    private Set<User> users;

}
