package pidev.elbey.RestControllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pidev.elbey.Entities.Roles;
import pidev.elbey.Entities.User;
import pidev.elbey.Services.UserService;

import java.util.List;

@RestController
@RequestMapping("/api")
public class UtilisateurRestController {
    @Autowired
    UserService userService ;



    @GetMapping("/users")
    List<User> getUsers(){
        return userService.getUsers();
    }

    @PostMapping("/user/add")
    User saveUser(@RequestBody User user){
        return userService.saveUser(user);
    }
    @PostMapping("/role/add")
    Roles saveRole(@RequestBody Roles role){
        return userService.saveRole(role);
    }
}
