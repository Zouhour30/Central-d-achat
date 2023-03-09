package pidev.elbey.Services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pidev.elbey.Entities.Product;
import pidev.elbey.Entities.Tender;
import pidev.elbey.Repositories.ProductRepo;
import pidev.elbey.Repositories.TenderRepo;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
@Slf4j

public class TenderService implements ITenderService {
    @Autowired
    TenderRepo tenderRepo;
    @Autowired
    ProductRepo productRepo;


    @Override
    public Tender saveTender(Tender tender) {
        return tenderRepo.save(tender);
    }



    @Override
    public void removeTender(Long id) {
        tenderRepo.deleteById(id);
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
    public void affecterProductTender(Long idTender, Long idProduct) {

        Product product = productRepo.findById(idProduct).orElse(null);
        Tender tender = tenderRepo.findById(idTender).orElse(null);

        tender.setProduct(product);
    }
}
