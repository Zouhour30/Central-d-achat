package pidev.elbey.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pidev.elbey.Entities.Roles;

@Repository
public interface RolesRepo extends JpaRepository<Roles, Integer> {
    Roles findByName(String name);
}
