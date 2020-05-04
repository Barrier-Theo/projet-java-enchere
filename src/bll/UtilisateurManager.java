package bll;

import java.util.List;

import bo.Utilisateur;
import dal.CodesResultatDAL;
import dal.DAOFactory;
import dal.UtilisateurDAO;
import bll.CodesResultatBLL;
import servlet.BusinessException;


public class UtilisateurManager {
	private BusinessException businessException = new BusinessException();

	private UtilisateurDAO utilisateurDAO;
	
	/**
	 * Le constructeur permet d'initialiser la variable membre avisDAO pour 
	 * permettre une communication avec la base de données. 
	 * @return 
	 */
	public UtilisateurManager() {
		this.utilisateurDAO=DAOFactory.getUtilisateurDAO();
	}
	
	
	public List<Utilisateur> selectAll() throws BusinessException{
		return this.utilisateurDAO.selectAll();
	}
	
	public Integer findIdByPseudoPassword(String pseudo, String password) throws BusinessException {
		Integer idUtilisateur = null;
		
		this.verifNullTrimLength(pseudo, 30, CodesResultatBLL.REGLE_PSEUDO);
		this.verifNullTrimLength(password, 30, CodesResultatBLL.REGLE_MOTDEPASSE);
		if(!this.businessException.hasErreurs())
		{
			idUtilisateur = this.utilisateurDAO.findIdByPseudoPassword(pseudo, password);
		}
		
		if(idUtilisateur == null) {
			this.businessException.ajouterErreur(CodesResultatBLL.IDENTIFIANTS_INCORRECTES);
		}
		
		if(this.businessException.hasErreurs())
		{
			throw this.businessException;
		}
		
		return idUtilisateur;
	}
	
	public void ajouterUtilisateur(Utilisateur utilisateur) throws BusinessException {
		this.verifUtilisateur(utilisateur);
		boolean erreur = this.utilisateurDAO.verifUnicitePseudoEmail(utilisateur); 
		
		if(erreur) {
			this.businessException.ajouterErreur(CodesResultatBLL.SPEUDO_EMAIL_NON_UNIQUE);
		}
		
		
		if(!this.businessException.hasErreurs())
		{
			this.utilisateurDAO.ajouterUtilisateur(utilisateur);
		}
		
		if(this.businessException.hasErreurs())
		{
			throw this.businessException;
		}
	}
	
	public void verifUtilisateur(Utilisateur utilisateur) {
		String pseudo = utilisateur.getPseudo();
		String motDePasse = utilisateur.getMotDePasse();
		String nom = utilisateur.getNom();
		String prenom = utilisateur.getPrenom();
		String email = utilisateur.getEmail();
		String telephone = utilisateur.getTelephone();
		String rue = utilisateur.getRue();
		String codePostale = utilisateur.getCodePostal();
		String ville = utilisateur.getVille();
		
		this.verifNullTrimLength(pseudo, 30, CodesResultatBLL.REGLE_PSEUDO);
		this.verifNullTrimLength(motDePasse, 30, CodesResultatBLL.REGLE_MOTDEPASSE);
		this.verifNullTrimLength(nom, 30, CodesResultatBLL.REGLE_NOM);
		this.verifNullTrimLength(prenom, 30, CodesResultatBLL.REGLE_PRENOM);
		this.verifNullTrimLength(email, 50, CodesResultatBLL.REGLE_EMAIL);
		this.verifNullTrimLength(telephone, 15, CodesResultatBLL.REGLE_TELEPHONE);
		this.verifNullTrimLength(rue, 30, CodesResultatBLL.REGLE_VILLE);
		this.verifNullTrimLength(codePostale, 10, CodesResultatBLL.REGLE_CODEPOSTALE);
		this.verifNullTrimLength(ville, 10, CodesResultatBLL.REGLE_VILLE);
		
	
	}
	
	public void verifNullTrimLength(String valeur, int length, int code ) {
		if(valeur==null || valeur.trim().isEmpty() || valeur.length() > length)
		{
			this.businessException.ajouterErreur(code);
		}
	}
	
	public Utilisateur selectUser(String id) {
		// TODO Auto-generated method stub
		
		Utilisateur utilisateur = new Utilisateur();
		
		try {
			utilisateur = this.utilisateurDAO.selectUser(id);
		} catch (BusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return utilisateur;
	}
	
	public void modifierUtilisateur(Utilisateur utilisateur) throws BusinessException {
		this.verifUtilisateur(utilisateur);
		boolean erreur = false;
		
		String pseudoDb = this.utilisateurDAO.getPseudoFromDb(utilisateur.getId());
		
		if(utilisateur.getPseudo() != pseudoDb){
			 erreur = this.utilisateurDAO.verifUnicitePseudoEmail(utilisateur); 
		}
		
		if(erreur) {
			this.businessException.ajouterErreur(CodesResultatBLL.SPEUDO_EMAIL_NON_UNIQUE);
		}
		
		
		if(!this.businessException.hasErreurs())
		{
			this.utilisateurDAO.modifierUtilisateur(utilisateur);
		}
		
		if(this.businessException.hasErreurs())
		{
			throw this.businessException;
		}
	}
}
