package pidev.elbey.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pidev.elbey.Entities.Offer;
@Repository

public interface OfferRepo extends JpaRepository<Offer, Long> {


}
