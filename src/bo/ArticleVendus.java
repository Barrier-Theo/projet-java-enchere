package bo;

import java.sql.Date;

import lombok.Data;

@Data
public class ArticleVendus {

	private Integer noArticle;
	private String nomArticle;
	private String description;
	private Date dateDebutEncheres;
	private Date dateFinEncheres;
	private Float miseAPrix;
	private Float prixVente;
	private Integer noUtilisateur;
	private Integer noCategorie;
	
	public ArticleVendus() {
		
	}
	
	public ArticleVendus(Integer noArticle, String nomArticle, String description, Date dateDebutEncheres, Date dateFinEncheres, 
			Float miseAPrix, Float prixVente, Integer noUtilisateur, Integer noCategorie) {
		this.noArticle = noArticle;
		this.nomArticle = nomArticle;
		this.description = description;
		this.dateDebutEncheres = dateDebutEncheres;
		this.dateFinEncheres = dateFinEncheres;
		this.miseAPrix = miseAPrix;
		this.prixVente = prixVente;
		this.noUtilisateur = noUtilisateur;
		this.noCategorie = noCategorie;
	}
	
}
