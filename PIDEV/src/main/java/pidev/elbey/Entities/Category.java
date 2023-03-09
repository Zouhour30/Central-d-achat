package pidev.elbey.Entities;

import lombok.*;


import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Getter
@Setter
@Entity
@AllArgsConstructor
@RequiredArgsConstructor
public class Category implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idCategory;
    private String nameCategory;
    @OneToMany(cascade = CascadeType.ALL,mappedBy = "category")
    private  Set<Product> products;





}
