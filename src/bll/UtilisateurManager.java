package bll;

import java.util.List;

import bo.Utilisateur;
import dal.DAOFactory;
import dal.UtilisateurDAO;
import bll.CodesResultatBLL;
import servlet.BusinessException;


public class UtilisateurManager {

	private UtilisateurDAO listeDAO;
	
	/**
	 * Le constructeur permet d'initialiser la variable membre avisDAO pour 
	 * permettre une communication avec la base de données. 
	 * @return 
	 */
	public UtilisateurManager() {
		this.listeDAO=DAOFactory.getAvisDAO();
	}
	/**
	 * @param description
	 * @param note
	 * @return un objet Avis en cas de succcès
	 * @throws BusinessException 
	 */
	public Utilisateur ajouterListeEtArticle(String nomListe, String nomArticle) throws BusinessException
	{
		BusinessException exception = new BusinessException();
		Utilisateur liste = new Utilisateur();
		this.verifNomListe(nomListe, exception);
		liste.setNom(nomListe);
		
		if(!exception.hasErreurs())
		{
			this.listeDAO.insert(liste);
		}
		
		if(exception.hasErreurs())
		{
			throw exception;
		}
		return liste;
	}
	
	public List<Utilisateur> selectAll() throws BusinessException{
		return this.listeDAO.selectAll();
	}
	
	public void verifNomListe(String nom, BusinessException businessException) {
		if(nom==null || nom.trim().isEmpty() || nom.length() > 50)
		{
			businessException.ajouterErreur(CodesResultatBLL.REGLE_NOM_LISTE);
		}
	}
	
}
