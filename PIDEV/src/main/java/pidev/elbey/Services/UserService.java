package pidev.elbey.Services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pidev.elbey.Entities.Roles;
import pidev.elbey.Entities.User;
import pidev.elbey.Repositories.RolesRepo;
import pidev.elbey.Repositories.UserRepo;

import javax.transaction.Transactional;
import java.util.List;
@Service
@Transactional
@Slf4j

public class UserService implements IUserService{
    @Autowired
    UserRepo userRepo;
    @Autowired
    RolesRepo rolesRepo;


    @Override
    public User saveUser(User user) {
         return userRepo.save(user);
    }

    @Override
    public Roles saveRole(Roles role) {
        return rolesRepo.save(role);
    }

    @Override
    public void AddRoleToUser(String username, String roleName) {
        User user = userRepo.findByUsername(username);
        Roles role= rolesRepo.findByName(roleName);
        user.getRoles().add(role);

    }

    @Override
    public User getUser(String username) {
        return userRepo.findByUsername(username);
    }

    @Override
    public List<User> getUsers() {
        return userRepo.findAll();
    }
}
