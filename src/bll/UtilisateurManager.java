package bll;

import java.util.List;

import bo.Utilisateur;
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
	/**
	 * @param description
	 * @param note
	 * @return un objet Avis en cas de succcès
	 * @throws BusinessException 
	 */
	public Utilisateur ajouterListeEtArticle(String nomListe, String nomArticle) throws BusinessException
	{

		Utilisateur liste = new Utilisateur();
		liste.setNom(nomListe);
		
		if(!businessException.hasErreurs())
		{
			this.utilisateurDAO.insert(liste);
		}
		
		if(businessException.hasErreurs())
		{
			throw businessException;
		}
		return liste;
	}
	
	public List<Utilisateur> selectAll() throws BusinessException{
		return this.utilisateurDAO.selectAll();
	}
	
	public Integer findIdByPseudoPassword(String pseudo, String password) throws BusinessException {
		Integer idUtilisateur = null;

		this.verifPeudoEtPassword(pseudo, password);
		if(!businessException.hasErreurs())
		{
			idUtilisateur = this.utilisateurDAO.findIdByPseudoPassword(pseudo, password);
		}
		
		if(businessException.hasErreurs())
		{
			throw businessException;
		}
		
		return idUtilisateur;
	}

	
	public void verifPeudoEtPassword(String pseudo, String password) {
		if(pseudo==null || pseudo.trim().isEmpty() || pseudo.length() > 30)
		{
			this.businessException.ajouterErreur(CodesResultatBLL.REGLE_PSEUDO);
		}
		
		if(password==null || password.trim().isEmpty() || password.length() > 30)
		{
			this.businessException.ajouterErreur(CodesResultatBLL.REGLE_MOTDEPASSE);
		}
	}
	
}
