package pidev.elbey.Services;

import pidev.elbey.Entities.Offer;
import pidev.elbey.Entities.Tender;

import java.util.List;

public interface IOfferService {

    Offer saveOffer(Offer offer);

    void removeOffer(Long idOffer);
    List<Offer> getOffer();

    List<Offer> getBestOffer();

    Offer updateOffer(Offer offer);

    Tender ajouterOeuvreArtEtAffecterAZone(Offer o, long idtender);
    void assignOffreToUser (Long idOffer, Long id);

}
