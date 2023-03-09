package pidev.elbey.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pidev.elbey.Entities.Tender;

public interface TenderRepo extends JpaRepository<Tender, Long> {

   //  @Query("SELECT COUNT(o) FROM Offer o WHERE o.tender.idTender = :tenderId")
  //   Long countByTenderId(@Param("tenderId") Long tenderId);
}
