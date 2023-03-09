package pidev.elbey.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pidev.elbey.Entities.ServiceR;

@Repository
public interface ServiceRepo extends JpaRepository<ServiceR, Long> {

}
