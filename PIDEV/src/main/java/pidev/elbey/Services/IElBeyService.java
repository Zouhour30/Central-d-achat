package pidev.elbey.Services;

import pidev.elbey.Entities.Roles;
import pidev.elbey.Entities.User;

import java.util.List;

public interface IElBeyService {
    User saveUser(User user);
    Roles saveRole(Roles role);
    void AddRoleToUser(String username,String roleName);
    User getUser(String username);
    List<User>getUsers();
}
