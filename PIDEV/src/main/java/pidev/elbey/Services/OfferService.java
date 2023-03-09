package pidev.elbey.Services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import pidev.elbey.Entities.Offer;
import pidev.elbey.Entities.Tender;
import pidev.elbey.Entities.User;
import pidev.elbey.Entities.statut;
import pidev.elbey.Repositories.OfferRepo;
import pidev.elbey.Repositories.TenderRepo;
import pidev.elbey.Repositories.UserRepo;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@Slf4j

public class OfferService implements IOfferService{



    @Autowired
    OfferRepo offerRepo;
    @Autowired
    UserRepo userRepo;
    @Autowired
    TenderRepo tenderRepo;

    @Autowired
    TenderService tenderService;
    @Autowired
    JavaMailSender javaMailSender;
    @Autowired
    JavaEmailService javaEmailService;




    @Override
    public Offer saveOffer(Offer offer) {
        return offerRepo.save(offer);
    }

    @Override
    public void removeOffer(Long id) {
        offerRepo.deleteById(id);
    }
    @Override
    public List<Offer> getOffer() {
        return offerRepo.findAll();
    }
    @Override
    public List<Offer> getBestOffer() {
        List<Tender> tenders = tenderService.gettender();
        List<Offer> bestOffers = new ArrayList<>();
        for (Tender t: tenders) {

            if(t.getProduct() == null)continue;
            Offer bestOffer = null;
            for (Offer offer:t.getOffers()) {
                if(bestOffer == null)
                {
                    bestOffer = offer;
                }
                else {
                    if(t.getProduct().getQuantityProduct()/bestOffer.getPrice() >
                            t.getProduct().getQuantityProduct()/offer.getPrice())
                    {
                        bestOffer = offer;
                    }
                }
            }
            if(bestOffer != null){
                bestOffers.add(bestOffer);
                bestOffer.setStatut(statut.accepter);
                javaEmailService.ConfirmationEmail3(bestOffer);
                offerRepo.save(bestOffer);
            }
        }

        return bestOffers;
    }
    @Override
    public Offer updateOffer(Offer offer) {
        return offerRepo.save(offer);
    }


    @Override
    public void assignOffreToUser(Long idOffer, Long id) {

        User user = userRepo.getById(id);
        Offer offer = offerRepo.findById(idOffer).orElse(null);

        offer.setUser(user);

    }

    @Override
    public Tender ajouterOeuvreArtEtAffecterAZone(Offer o, long idtender) {


        Tender tender = tenderRepo.findById(idtender).get();
        // OeuvreArt child
        // Zone master
        // on affecte child au master ==> Ajoute cascade
        tender.getOffers().add(o);
        return tenderRepo.save(tender);
    }




/*
    @Override
    public List<Offer> getBestOfferr() {
        List<Tender> tenders = tenderRepo.getTender();
        List<Offer> bestOffers = new ArrayList<>();

        for (Tender tender : tenders) {
            if (tender.getProduct() == null) {
                continue;
            }

            Offer bestOffer = null;
            for (Offer offer : tender.getOffers()) {
                if (bestOffer == null) {
                    bestOffer = offer;
                } else {
                    double bestOfferPrice = tender.getProduct().getQuantityProduct() / bestOffer.getPrice();
                    double currentOfferPrice = tender.getProduct().getQuantityProduct() / offer.getPrice();
                    if (currentOfferPrice > bestOfferPrice) {
                        bestOffer = offer;
                    }
                }
            }

            if (bestOffer != null) {
                bestOffer.setTender(tender);
                bestOffer.setStatut(statut.accepter);
                javaEmailService.ConfirmationEmail3(bestOffer);
                offerRepo.save(bestOffer);
                bestOffers.add(bestOffer);
            }
        }

        return bestOffers;
    }
*/

}
