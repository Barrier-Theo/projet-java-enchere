package bo;

import lombok.Data;

@Data
public class Retraits {

	private Integer noArticle;
	private String rue;
	private String codePostal;
	private String ville;
	
	public Retraits() {
		
	}
	
	public Retraits(Integer noArticle, String rue, String codePostal, String ville) {
		this.noArticle = noArticle;
		this.rue = rue;
		this.codePostal = codePostal;
		this.ville = ville;
	}
	
	
}
