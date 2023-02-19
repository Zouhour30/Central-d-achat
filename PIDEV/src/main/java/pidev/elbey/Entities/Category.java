package com.example.testspring.Entities;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@Entity
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int idCategory;
    String nameCategory;

    @OneToMany (cascade = CascadeType.ALL, mappedBy="category")
    private Set<Product> Products;
}
