package bo;

import lombok.Data;

@Data
public class Categories {

	private Integer noCategorie;
	private String libelle;
	
	public Categories() {
		
	}
	
	public Categories(Integer noCategorie, String libelle) {
		this.noCategorie = noCategorie;
		this.libelle = libelle;
	}
}
