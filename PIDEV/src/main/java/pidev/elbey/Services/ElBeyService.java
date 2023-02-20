package pidev.elbey.Services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pidev.elbey.Entities.BillToSeen;
import pidev.elbey.Entities.Delivery;
import pidev.elbey.Entities.Roles;
import pidev.elbey.Entities.User;
import pidev.elbey.Repositories.BillToSeenRepo;
import pidev.elbey.Repositories.DeliveryRepo;
import pidev.elbey.Repositories.RolesRepo;
import pidev.elbey.Repositories.UserRepo;

import javax.transaction.Transactional;
import java.util.List;
@Service
@Transactional
@Slf4j

public class ElBeyService implements IElBeyService {
    @Autowired
    UserRepo userRepo;
    @Autowired
    RolesRepo rolesRepo;
    @Autowired
    DeliveryRepo   deliveryRepo;
    @Autowired
 BillToSeenRepo billToSeenRepo;

    @Override
    public Delivery addDelivery(Delivery delivery) {
       return deliveryRepo.save(delivery);
    }

    @Override
    public List<Delivery> getdeliveries() {
        return deliveryRepo.findAll();
    }

    @Override
    public Delivery updateDelivery(Delivery delivery) {
        return deliveryRepo.save(delivery);}

    @Override
    public void deleteDelivery(Long id) {
        deliveryRepo.deleteById(id);
    }

    @Override
    public BillToSeen addBillToSeen(BillToSeen b) {
        return billToSeenRepo.save(b);
    }

    @Override
    public List<BillToSeen> getBills() {
        return billToSeenRepo.findAll();
    }

    @Override
    public BillToSeen updateBillToSeen(BillToSeen billToSeen) {
        return billToSeenRepo.save(billToSeen);
    }

    @Override
    public void deleteBillToSeen(Long id) {
        billToSeenRepo.deleteById(id);
    }


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
