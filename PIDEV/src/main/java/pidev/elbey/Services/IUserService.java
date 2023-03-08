package pidev.elbey.Services;

import pidev.elbey.Entities.Orders;
import pidev.elbey.Entities.Roles;
import pidev.elbey.Entities.User;

import java.util.List;

public interface IUserService {
     User saveUser(User user);
     User UpdateUser(User user , Long userId);
     void delete(User u);
     Roles saveRole(Roles role);


     void AddRoleToUser(String username,String roleName);
     User getUser(String username);
     List<User>getUsers();
     List<Orders> getOrderHistory(Long userId);
     void changePassword(Long userId, String newPassword);
}
