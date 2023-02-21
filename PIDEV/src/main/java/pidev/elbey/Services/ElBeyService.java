package pidev.elbey.Services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pidev.elbey.Entities.Offer;
import pidev.elbey.Entities.Roles;
import pidev.elbey.Entities.Tender;
import pidev.elbey.Entities.User;
import pidev.elbey.Repositories.OfferRepo;
import pidev.elbey.Repositories.RolesRepo;
import pidev.elbey.Repositories.TenderRepo;
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
    TenderRepo tenderRepo;

    @Autowired
    OfferRepo offerRepo;


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




    @Override
    public Tender saveTender(Tender tender) {
        return tenderRepo.save(tender);
    }

    @Override
    public Offer saveOffer(Offer offer) {
        return offerRepo.save(offer);
    }

    @Override
    public void removeTender(Long id) {
        tenderRepo.deleteById(id);
    }

    @Override
    public void removeOffer(Long id) {
        offerRepo.deleteById(id);
    }

    @Override
    public List<Tender> gettender() {
        return tenderRepo.findAll();
    }

    @Override
    public Tender updateTender(Tender tender) {
        return tenderRepo.save(tender);
    }

    @Override
    public List<Offer> getOffer() {
        return offerRepo.findAll();
    }

    @Override
    public Offer updateOffer(Offer offer) {
        return offerRepo.save(offer);
    }


}



