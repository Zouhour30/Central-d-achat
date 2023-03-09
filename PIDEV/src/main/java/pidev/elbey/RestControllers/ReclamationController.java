package pidev.elbey.RestControllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


import pidev.elbey.Entities.Reclamation;
import pidev.elbey.Services.ReclamationService;

@RestController
@RequestMapping("/Elbey")
public class ReclamationController {

	  @Autowired
	  ReclamationService reclamationService;
	  
	  @GetMapping("/reclamation")
	    List< Reclamation> getReclamation(){
	        return reclamationService.getReclamation();
	    }
	  
	  @PostMapping("/reclamation/add")
	  Reclamation saveReclamation(@RequestBody Reclamation r){
	        return reclamationService.saveReclamation(r);
	    }
	  
	 	@DeleteMapping("/delete-reclamation/{id}")
	 	@ResponseBody
	 	public void deleteReclamation(@PathVariable("id") Long idReclamation) {
	 		reclamationService.deleteReclamation(idReclamation);
	 	}
	 	
	 	@PutMapping("/update-reclamation")
	 	@ResponseBody
	 	public Reclamation updateReclamation(@RequestBody Reclamation r) {
	 		Reclamation ba = reclamationService.updateReclamation(r);
	 		return  ba;
	 	}
	  
		@PutMapping("/AddReclamationToCommande/{idOrders}/{numCommande}")
	 	@ResponseBody
	 	public ResponseEntity<String> AddReclamationToCommande(@PathVariable("nameOrders") Long idOrders,@PathVariable("numCommande") Long numCommande) {
	 		reclamationService.AddReclamationToCommande(idOrders, numCommande);
	 		return  new ResponseEntity<String>("Assyn succes", HttpStatus.INTERNAL_SERVER_ERROR);
	 	
	 	}
		
		@GetMapping("/Search-reclamation")
		@ResponseBody
		public List<Reclamation> listSearchReclamation(){
			return reclamationService.SearchReclamation();
		}
		
		
}
