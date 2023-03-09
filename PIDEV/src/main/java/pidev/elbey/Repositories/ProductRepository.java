package  pidev.elbey.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pidev.elbey.Entities.Product;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {
//Requet
    //@Query("select u from  Universite  u where u.nomUniv=?1")
    //    List<Universite> rechercheParNom(String nom);
   /* @Query("select p from Product p where p.category=?1")//1 awel parametre fel methode
    List<Product> getProductsByCategory(String categoryName);
*/

    @Query("SELECT p FROM Product p ORDER BY p.priceProduct ASC")
    List<Product> findProductsSortedByPriceAsc();
    List<Product> findProductsByOrderByPriceProduct();


    ////// stat

    @Query("SELECT SUM(p.quantityProduct) FROM Product p")
    Long sumQuantity();

    @Query("SELECT AVG(p.quantityProduct) FROM Product p")
    Double averageQuantity();
    @Query("SELECT MIN(p.quantityProduct) FROM Product p")
    Integer minQuantity();
    @Query("SELECT MAX(p.quantityProduct) FROM Product p")
    Integer maxQuantity();

    @Query("SELECT p FROM Product p WHERE p.quantityProduct < 20")
    List<Product> findProductsExceededQuantity();

}
