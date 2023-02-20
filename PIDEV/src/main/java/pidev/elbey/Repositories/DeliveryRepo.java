package pidev.elbey.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pidev.elbey.Entities.Delivery;
import pidev.elbey.Entities.Roles;

@Repository

public interface DeliveryRepo  extends JpaRepository<Delivery, Long> {
    Delivery findByIdDelivery(long id);
}
