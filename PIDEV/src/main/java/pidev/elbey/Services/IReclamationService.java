package pidev.elbey.Services;

import java.util.List;



import pidev.elbey.Entities.Orders;
import pidev.elbey.Entities.Reclamation;

public interface IReclamationService {
	public Reclamation saveReclamation(Reclamation r);
	void deleteReclamation(Long idReclamation);
	public List<Reclamation> listReclamations();
	Reclamation updateReclamation(Reclamation r);
	public List<Reclamation>SearchReclamation();
	void AddReclamationToCommande(Long idOrder,Long numCommande);
    Orders getOrders(String nameOrders);
    List<Reclamation>getReclamation();
}
