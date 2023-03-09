package pidev.elbey.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pidev.elbey.Entities.Orders;
import pidev.elbey.Entities.User;

@Repository
public interface OrderRepo extends JpaRepository<Orders, Long> {
	Orders findBynameOrder(String nameOrder);
}
