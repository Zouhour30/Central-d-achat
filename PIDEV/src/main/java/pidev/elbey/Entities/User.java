package pidev.elbey.Entities;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor

@RequiredArgsConstructor
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String username;
    private String password;

    @ManyToMany(fetch = FetchType.EAGER) // load the user and load their roles in the db
    private Collection<Roles> roles = new ArrayList<>();

    @ManyToMany(cascade=CascadeType.ALL)
    private Set<Tender> tenderSet;
    @ManyToOne ()
    private Forum forum;

    @OneToMany(cascade = CascadeType.ALL,
            mappedBy="user")
    private Set<Product> Products;

    @OneToOne()
    private Orders orders;

    @OneToMany(cascade = CascadeType.ALL,
            mappedBy="user")
    private Set<Delivery> deliveries;


    @OneToMany(cascade = CascadeType.ALL,
            mappedBy="user")
    private Set<Offer> offers;



}
