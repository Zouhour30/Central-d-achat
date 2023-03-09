
package pidev.elbey.Entities;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;


@Entity
@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
public class Reclamation implements Serializable {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idReclamation;
    private String DescriptionReclamation;
    private String imageReclamation;
    private String numCommande;
    @Temporal(TemporalType.DATE)
    @Column(name = "dateReclamation", nullable = false)
    private Date dateReclamation;
    private String adresse;
    
	@ManyToOne
	@JoinColumn(name="reclamant_Code")
	private Orders order;
	
	@ManyToOne
	@JoinTable(name="Recalamation_Services")
	private ServiceR service;
    
    
	public Long getIdReclamation() {
		return idReclamation;
	}
	public void setIdReclamation(Long idReclamation) {
		this.idReclamation = idReclamation;
	}
	public String getDescriptionReclamation() {
		return DescriptionReclamation;
	}
	public void setDescriptionReclamation(String descriptionReclamation) {
		DescriptionReclamation = descriptionReclamation;
	}
	public String getImageReclamation() {
		return imageReclamation;
	}
	public void setImageReclamation(String imageReclamation) {
		this.imageReclamation = imageReclamation;
	}
	public String getNumCommande() {
		return numCommande;
	}
	public void setNumCommande(String numCommande) {
		this.numCommande = numCommande;
	}
	public Date getDateReclamation() {
		return dateReclamation;
	}
	public void setDateReclamation(Date dateReclamation) {
		this.dateReclamation = dateReclamation;
	}
	public String getAdresse() {
		return adresse;
	}
	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}
	public Orders getOrder() {
		return order;
	}
	public void setOrder(Orders order) {
		this.order = order;
	}
	
	
    
    
    

}
