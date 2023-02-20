package pidev.elbey.RestControllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pidev.elbey.Entities.Delivery;
import pidev.elbey.Entities.Roles;
import pidev.elbey.Entities.User;
import pidev.elbey.Services.ElBeyService;

import java.util.List;

@RestController
@RequestMapping("/Elbey")
public class ElBeyRestController {
    @Autowired
    ElBeyService userService ;

    @PostMapping("/delivery/add")
    Delivery addDelivery(@RequestBody Delivery delivery){
        return userService.addDelivery(delivery);}
    @GetMapping("/deliveries")
    List<Delivery> getDeliveries(){
        return userService.getdeliveries();
    }
    @PutMapping("/delivery/update")
    Delivery updateDelivery(@RequestBody Delivery delivery){
        return userService.updateDelivery(delivery);}
    @DeleteMapping("/delete/{idDelivery}")
    public void deleteDelivery(@PathVariable Long idDelivery) {
        userService.deleteDelivery(idDelivery);
    }

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
