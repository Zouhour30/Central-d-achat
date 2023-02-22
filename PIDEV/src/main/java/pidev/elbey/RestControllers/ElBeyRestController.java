package pidev.elbey.RestControllers;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pidev.elbey.Entities.Roles;
import pidev.elbey.Entities.User;
import pidev.elbey.Services.ElBeyService;

import java.util.List;

@RestController
@RequestMapping("/Elbey")
public class ElBeyRestController {
    @Autowired
    ElBeyService elBeyService ;



    @GetMapping("/users")
    List<User> getUsers(){
        return elBeyService.getUsers();
    }

    @PostMapping("/user/add")
    User saveUser(@RequestBody User user){
        return elBeyService.saveUser(user);
    }
    @PostMapping("/role/add")
    Roles saveRole(@RequestBody Roles role){
        return elBeyService.saveRole(role);

    }
    @PostMapping("/role/addtouser")
    void AddRoleToUser(@RequestBody RoleToUserForm form){
        elBeyService.AddRoleToUser(form.getUsername(), form.getRoleName());

    }
}
@Data
class RoleToUserForm{
    private String username;
    private String roleName;
}
