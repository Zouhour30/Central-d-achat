package pidev.elbey.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pidev.elbey.Entities.Orders;
import pidev.elbey.Entities.User;

import java.util.List;

@Repository
public interface OrdersRepo extends JpaRepository<Orders,Long> {
    List<Orders> findByUser(User user);
}
