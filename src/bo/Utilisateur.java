package bo;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class Utilisateur  {
    private Integer id;
    private String pseudo;
    private String nom;
    private String prenom;
    private String email;
    private String telephone;
    private String rue;
    private String codePostal;
    private String ville;
    private String motDePasse;
    private Integer credit;
    private Boolean isAdmin;
    private Boolean isDelete;
    

    public Utilisateur() {
    
    }
    
    public Utilisateur(Integer id, String nom) {
    	this.id = id;
    	this.nom = nom;
    }
    
    public Utilisateur(String pseudo, String nom, String prenom, String email, String telephone, String rue, 
    		String codePostal,String ville, String motDePasse, Integer credit, Boolean isAdmin, Boolean isDelete) {
    	this.pseudo = pseudo;
    	this.nom= nom;
    	this.prenom = prenom;
    	this.email = email;
    	this.telephone = telephone;
    	this.rue = rue;
    	this.codePostal = codePostal;
    	this.ville= ville;
    	this.motDePasse = motDePasse;
    	this.credit = credit;
    	this.isAdmin = isAdmin;
    	this.isDelete = isDelete; 
    }
    
    public Utilisateur(Integer id, String pseudo, String nom, String prenom, String email, String telephone, String rue, 
    		String codePostal,String ville, String motDePasse, Integer credit, Boolean isAdmin) {
    	this.id = id;
    	this.pseudo = pseudo;
    	this.nom= nom;
    	this.prenom = prenom;
    	this.email = email;
    	this.telephone = telephone;
    	this.rue = rue;
    	this.codePostal = codePostal;
    	this.ville= ville;
    	this.motDePasse = motDePasse;
    	this.credit = credit;
    	this.isAdmin = isAdmin;
    }

	public Utilisateur(Integer id, String pseudo, String nom, String prenom, String email, String telephone,
			String rue, String codePostal, String ville, Integer credit) {
		this.id = id;
    	this.pseudo = pseudo;
    	this.nom= nom;
    	this.prenom = prenom;
    	this.email = email;
    	this.telephone = telephone;
    	this.rue = rue;
    	this.codePostal = codePostal;
    	this.ville= ville;
    	this.credit = credit;
	}
    				
    
  


}