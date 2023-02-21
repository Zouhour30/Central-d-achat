package pidev.elbey.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pidev.elbey.Entities.Tender;

public interface TenderRepo extends JpaRepository<Tender, Long> {
}
