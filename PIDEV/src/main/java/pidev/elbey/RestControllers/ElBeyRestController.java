package pidev.elbey.RestControllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pidev.elbey.Entities.Offer;
import pidev.elbey.Entities.Roles;
import pidev.elbey.Entities.Tender;
import pidev.elbey.Entities.User;
import pidev.elbey.Services.ElBeyService;

import java.util.List;

@RestController
@RequestMapping("/Elbey")
public class ElBeyRestController {
    @Autowired
    ElBeyService userService ;



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

    @PostMapping("/tender/add")
    Tender saveTender(@RequestBody Tender tender){
        return userService.saveTender(tender);
    }

    @PostMapping("/offre/add")
    Offer saveTender(@RequestBody Offer offer){
        return userService.saveOffer(offer);
    }

    @DeleteMapping("/deletetender/{id}")
    public String removeTender(@PathVariable Long id){
        userService.removeTender(id);
        return "deleted";
    }


    @DeleteMapping("/deleteOffer/{id}")
    public String removeOffer(@PathVariable Long id){
        userService.removeOffer(id);
        return "deleted";
    }


    @GetMapping("/tender")
    List<Tender> getTenders(){
        return userService.gettender();
    }
    @PutMapping("/tender/update")
    Tender updateTender(@RequestBody Tender tender){
        return userService.updateTender(tender);}


    @GetMapping("/offer")
    List<Offer> getOffers(){
        return userService.getOffer();
    }
    @PutMapping("/offer/update")
    Offer updateOffer(@RequestBody Offer offer){
        return userService.updateOffer(offer);}












}
