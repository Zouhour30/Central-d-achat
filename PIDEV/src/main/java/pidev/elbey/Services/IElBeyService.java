package pidev.elbey.Services;

import pidev.elbey.Entities.Offer;
import pidev.elbey.Entities.Roles;
import pidev.elbey.Entities.Tender;
import pidev.elbey.Entities.User;

import java.util.List;

public interface IElBeyService {
    User saveUser(User user);
    Roles saveRole(Roles role);
    void AddRoleToUser(String username,String roleName);
    User getUser(String username);
    List<User>getUsers();





    Tender saveTender(Tender tender);
    Offer saveOffer(Offer offer);
    void removeTender(Long idTender);
    void removeOffer(Long idOffer);

    List<Tender>gettender();
    Tender updateTender(Tender tender);

    List<Offer>getOffer();
    Offer updateOffer(Offer offer);

}
