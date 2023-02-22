package com.example.testspring.Repository;

import com.example.testspring.Entities.Basket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BasketReprository extends JpaRepository<Basket,Long> {
}
