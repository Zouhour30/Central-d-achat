package  pidev.elbey.Repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import pidev.elbey.Entities.ImageModel;

public interface ImageRepo extends JpaRepository<ImageModel,Long> {
}
