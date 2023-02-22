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
public class Payment implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPayment;
    private String amount;
    private boolean methode;
    @Temporal(TemporalType.DATE)
    @Column(name = "datePayement", nullable = false)
    private Date datePayement;

    @OneToOne(mappedBy="payment")
    private Orders orders;
}
