package pidev.elbey.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pidev.elbey.Entities.EmailDetails;

@Repository
public interface EmailServiceRepo extends JpaRepository<EmailDetails, Long > {

}
