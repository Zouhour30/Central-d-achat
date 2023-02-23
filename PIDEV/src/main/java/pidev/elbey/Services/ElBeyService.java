package pidev.elbey.Services;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pidev.elbey.Entities.Roles;
import pidev.elbey.Entities.User;
import pidev.elbey.Repositories.RolesRepo;
import pidev.elbey.Repositories.UserRepo;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
@Service
@Transactional
@Slf4j
@AllArgsConstructor
public class ElBeyService implements IElBeyService , UserDetailsService {
    private final PasswordEncoder passwordEncoder;
    @Autowired
    UserRepo userRepo;
    @Autowired
    RolesRepo rolesRepo;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user= userRepo.findByUsername(username);
        if(user==null){
            log.error("user not found in the database");
        }
        else
        {
            log.info("user found in the database: {}",username);
        }
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        user.getRoles().forEach(roles -> {
            authorities.add(new SimpleGrantedAuthority(roles.getName()));
        });
        return new org.springframework.security.core.userdetails.User(user.getUsername(),user.getPassword(),authorities);
    }
    @Override
    public User saveUser(User user) {
         user.setPassword(passwordEncoder.encode(user.getPassword()));
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
