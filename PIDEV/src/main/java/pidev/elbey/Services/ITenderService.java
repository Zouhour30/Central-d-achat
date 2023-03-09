package pidev.elbey.Services;

import pidev.elbey.Entities.Tender;

import java.util.List;

public interface ITenderService {
    Tender saveTender(Tender tender);

    void removeTender(Long idTender);

    List<Tender> gettender();
    Tender updateTender(Tender tender);

    void affecterProductTender(Long idTender, Long idProduct) ;
}
