package pidev.elbey.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
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

public class Offer implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idOffer;
    private String nameOffer;
    private String detail;
    private float price;
    @Column(name = "statut", columnDefinition = "varchar(255) default 'attender'")
    @Enumerated(EnumType.STRING)
    private statut statut;


    @Column(name = "dateOffer", nullable = false)
    private Date dateOffer;

    @JsonIgnore

    @ManyToOne
    private User user;

}
