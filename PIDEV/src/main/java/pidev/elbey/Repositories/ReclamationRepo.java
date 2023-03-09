package pidev.elbey.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


import pidev.elbey.Entities.Reclamation;

@Repository
public interface ReclamationRepo extends JpaRepository<Reclamation, Long> {
	Reclamation findByNumCommande(String numCommande);
	@Query(value ="SELECT * FROM reclamation pf WHERE pf.numCommande LIKE 'P%' ",nativeQuery = true)
	List<Reclamation> SearchReclamation();
}
