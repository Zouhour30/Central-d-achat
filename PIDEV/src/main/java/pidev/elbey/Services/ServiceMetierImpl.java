package pidev.elbey.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pidev.elbey.Entities.ServiceR;
import pidev.elbey.Repositories.ServiceRepo;

@Service
public class ServiceMetierImpl implements IServiceMetier  {

	@Autowired
	private ServiceRepo serviceRepository;
	@Override
	public ServiceR saveService(ServiceR s) {
		return serviceRepository.save(s);
	}

	@Override
	public List<ServiceR> listServices() {
	
		return serviceRepository.findAll();
	}

	@Override
	public ServiceR getServiceByCode(long idService) {
		return serviceRepository.getOne(idService);
	}

	@Override
	public void deleteServiceR(Long idService) {
		serviceRepository.deleteById(idService);
		
	}

}
