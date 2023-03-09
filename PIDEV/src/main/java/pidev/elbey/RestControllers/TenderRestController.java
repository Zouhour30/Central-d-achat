package pidev.elbey.RestControllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pidev.elbey.Entities.Tender;
import pidev.elbey.Services.TenderService;

import java.util.List;

@RestController
@RequestMapping("/Elbey")
public class TenderRestController {

    @Autowired
    TenderService tenderService;
    @PostMapping("/tender/add")
    Tender saveTender(@RequestBody Tender tender) {
        return tenderService.saveTender(tender);
    }



    @DeleteMapping("/deletetender/{id}")
    public String removeTender(@PathVariable Long id) {
        tenderService.removeTender(id);
        return "deleted";
    }





    @GetMapping("/tender")
    List<Tender> getTenders() {
        return tenderService.gettender();
    }

    @PutMapping("/tender/update")
    Tender updateTender(@RequestBody Tender tender) {
        return tenderService.updateTender(tender);
    }

    @PutMapping("/affecterProduitTender")

    public void affecterProductTender(@RequestParam Long idTender, @RequestParam Long idProduct) {
        tenderService.affecterProductTender(idTender, idProduct);
    }


}
