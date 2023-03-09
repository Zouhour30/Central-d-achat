package  pidev.elbey.Repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pidev.elbey.Entities.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category,Long> {

}
