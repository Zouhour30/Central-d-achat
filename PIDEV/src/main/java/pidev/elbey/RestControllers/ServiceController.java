package pidev.elbey.RestControllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import pidev.elbey.Entities.ServiceR;
import pidev.elbey.Services.IServiceMetier;

@RestController
@RequestMapping("/Elbey")
public class ServiceController {

	@Autowired
	private IServiceMetier serviceMetier;

	
	  @PostMapping("/serviceR/add")
	 ServiceR saveService(@RequestBody ServiceR s) {
		return serviceMetier.saveService(s);
	}

	@RequestMapping(value="/services", method=RequestMethod.GET)
	public List<ServiceR> listServices() {
		return serviceMetier.listServices();
	}

	@RequestMapping(value="/services/{idService}", method=RequestMethod.GET)
	public ServiceR getServiceByCode(@PathVariable long idService) {
		return serviceMetier.getServiceByCode(idService);
	}
	
 	@DeleteMapping("/delete-serviceR/{id}")
 	@ResponseBody
 	public void deleteReclamation(@PathVariable("id") Long idService) {
 		serviceMetier.deleteServiceR(idService);
 	}
}
