package pidev.elbey.Services;

import java.util.List;

import pidev.elbey.Entities.ServiceR;

public interface IServiceMetier {

	public ServiceR saveService(ServiceR s);
	public List<ServiceR> listServices();
	public ServiceR getServiceByCode(long idService);
	void deleteServiceR(Long idService);
}
