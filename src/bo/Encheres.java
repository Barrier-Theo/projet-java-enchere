package bo;

import java.time.LocalDate;

import lombok.Data;

@Data
public class Encheres {

	private Integer noUtilisateur;
	private Integer noArticle;
	private LocalDate dateEnchere;
	private Integer prixVente;
	
	public Encheres(Integer noUtilisateur, Integer noArticle, LocalDate dateEnchere, Integer prixVente) {
		this.noUtilisateur = noUtilisateur;
		this.noArticle = noArticle;
		this.dateEnchere = dateEnchere;
		this.prixVente = prixVente;
	}
	public Encheres(Integer noUtilisateur, Integer noArticle,Integer prixVente) {
		this.noUtilisateur = noUtilisateur;
		this.noArticle = noArticle;
		this.prixVente = prixVente;
	}
	
	public Encheres() {
		
	}
	
}
