package pidev.elbey.Entities;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor

@RequiredArgsConstructor


public class Delivery implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idDelivery;
    private String destination;
    @Temporal(TemporalType.DATE)
    @Column(name = "dateDilevery", nullable = false)
    private Date dateDilevery;

    @JsonIgnore
    @OneToMany(cascade =CascadeType.ALL)
    private Set<Orders> orders;

    @OneToOne
    private BillToSeen billtoseen;
    @ManyToOne
    private User user ;
}
