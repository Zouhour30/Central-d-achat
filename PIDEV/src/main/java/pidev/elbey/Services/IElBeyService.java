package pidev.elbey.Services;

import org.springframework.web.bind.annotation.RequestBody;
import pidev.elbey.Entities.Delivery;
import pidev.elbey.Entities.Roles;
import pidev.elbey.Entities.User;

import java.util.List;

public interface IElBeyService {
    Delivery addDelivery(Delivery delivery);
    List<Delivery>getdeliveries();
    Delivery updateDelivery(Delivery delivery);
    void deleteDelivery(Long id);
    User saveUser(User user);
    Roles saveRole(Roles role);
    void AddRoleToUser(String username,String roleName);
    User getUser(String username);
    List<User>getUsers();

}
