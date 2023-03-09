package pidev.elbey.RestControllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.*;
import pidev.elbey.Entities.Offer;
import pidev.elbey.Entities.Tender;
import pidev.elbey.Repositories.OfferRepo;
import pidev.elbey.Services.OfferService;

import java.util.List;

@RestController
@RequestMapping("/Elbey")
public class OfferRestController {

     @Autowired
     OfferService offerService;
    @Autowired
    OfferRepo offerRepo;

    @Autowired
    JavaMailSender javaMailSender;

    @PostMapping("/offre/add")
    Offer saveTender(@RequestBody Offer offer) {
        return offerService.saveOffer(offer);
    }


    @DeleteMapping("/deleteOffer/{id}")
    public String removeOffer(@PathVariable Long id) {
        offerService.removeOffer(id);
        return "deleted";
    }


    @GetMapping("/offer")
    List<Offer> getOffers()
    {
        List<Offer> a=offerService.getOffer();
        System.out.println(a);
        return offerService.getOffer();
    }

    @GetMapping("/bestoffer")
    List<Offer> getBestOffre()
    {
        return offerService.getBestOffer();
    }

    @PutMapping("/offer/update")
    Offer updateOffer(@RequestBody Offer offer) {
        return offerService.updateOffer(offer);
    }



    @PutMapping("/{idOffer}/OFFRE/{id}")
    public void assignOffreToUser(@PathVariable Long idOffer,
                                  @PathVariable Long id) {
        offerService.assignOffreToUser(idOffer, id);
    }
    @PostMapping("ajouterOffertEtAffectertender")
    Tender ajouterOeuvreArtEtAffecterAZone(@RequestBody Offer o, @RequestParam long idtender){
        return offerService.ajouterOeuvreArtEtAffecterAZone(o,idtender);
    }

    @PostMapping("/send-mail/{idOffer}")
    public ResponseEntity<String> sendEmail(@PathVariable Long idOffer) {
        SimpleMailMessage message = new SimpleMailMessage();
        Offer o = offerRepo.findById(idOffer).orElse(null);
        message.setTo(o.getUser().getEmail());
        message.setSubject("Offer Confirmation - tender : " + o.getIdOffer());
        message.setText("Dear " + o.getUser().getEmail() + ",\n\n"
                + "Your offer has been confirmed for tender : " + o.getIdOffer() + " from "
                + o.getDateOffer().toString() + " to " + ".\n\n"
                + "Total price: TND" + o.getPrice() + "\n\n"
                + "Satatut" + o.getStatut() + "\n\n"
                + "Thank you .\n"
                + "-----------");

        javaMailSender.send(message);

        return ResponseEntity.ok("Email sent successfully!");
    }



}
