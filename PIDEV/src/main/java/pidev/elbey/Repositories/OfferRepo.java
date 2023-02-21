package pidev.elbey.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pidev.elbey.Entities.Offer;

public interface OfferRepo extends JpaRepository<Offer, Long> {
}
