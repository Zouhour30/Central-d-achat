package pidev.elbey.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.Type;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor


public class User implements Serializable  {



    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    private String username;
    private String Adresse;
    private String PhoneNumber;

    private String email;
    private String password;
    private Boolean locked = false;
    private Boolean enabled = false;
    private String verificationCode;
    private String PasswordToken;


   // @Override
    //public Collection<? extends GrantedAuthority> getAuthorities() {
   //     return getAuthorities();
    //}
/*
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return !locked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }*/



    @ManyToMany(fetch = FetchType.EAGER) // load the user and load their roles in the db
    private Collection<Roles> roles = new ArrayList<>();

    @ManyToMany(cascade=CascadeType.ALL)
    private Set<Tender> tenderSet;
    @JsonIgnore

    @ManyToOne ()
    private Forum forum;

    @OneToMany(cascade = CascadeType.ALL,
            mappedBy="user")
    private Set<Product> Products;
    @JsonIgnore

    @OneToOne()
    private Orders orders;

    @OneToMany(cascade = CascadeType.ALL,
            mappedBy="user")
    private Set<Delivery> deliveries;

    @JsonIgnore

    @OneToMany(cascade = CascadeType.ALL,
            mappedBy="user")
    private Set<Offer> offers;

    public User(String name, String lastName, String email, String password) {
        this.firstName = name;
        this.lastName = lastName;
        this.email = email;
        this.password = password;

    }
}
