package pidev.elbey.Services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pidev.elbey.Entities.Orders;
import pidev.elbey.Entities.Reclamation;
import pidev.elbey.Repositories.OrderRepo;
import pidev.elbey.Repositories.ReclamationRepo;


@Service
public class ReclamationService implements IReclamationService {

	@Autowired
	private ReclamationRepo reclamationRepository;
	@Autowired
	OrderRepo orderRepo;
	
	
	@Override
	public Reclamation saveReclamation(Reclamation r) {
		// TODO Auto-generated method stub
		return reclamationRepository.save(r);
	}

	@Override
	public List<Reclamation> listReclamations() {
		// TODO Auto-generated method stub
		return reclamationRepository.findAll();
	}

	@Override
	public void AddReclamationToCommande(Long idOrder, Long idCommande) {
		Optional<Orders> orderOptional = orderRepo.findById(idOrder); 
		if(orderOptional.isPresent()) {
			Optional<Reclamation> reclamation= reclamationRepository.findById(idCommande);
		       if(reclamation.isPresent()) {
		    	   Orders order = orderOptional.get();
		    	   order.getReclamations().add(reclamation.get());
					  orderRepo.save(order);
		       }else {
					System.out.println(" reclamation null");
				}
		}else {
			System.out.println(" order null");
		}
		  
	}

	@Override
	public Orders getOrders(String nameOrders) {
		// TODO Auto-generated method stub
		return orderRepo.findBynameOrder(nameOrders);
	}

	@Override
	public List<Reclamation> getReclamation() {
		// TODO Auto-generated method stub
		return reclamationRepository.findAll();
	}

	@Override
	public void deleteReclamation(Long idReclamation) {
		reclamationRepository.deleteById(idReclamation);
		
	}

	@Override
	public Reclamation updateReclamation(Reclamation r) {
		// TODO Auto-generated method stub
		return reclamationRepository.save(r);
	}

	@Override
	public List<Reclamation> SearchReclamation() {
		// TODO Auto-generated method stub
		return (List<Reclamation>)reclamationRepository.SearchReclamation();
	}

	

}
