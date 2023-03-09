package  pidev.elbey.Repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pidev.elbey.Entities.Basket;

@Repository
public interface BasketReprository extends JpaRepository<Basket,Long> {
}
