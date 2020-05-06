package bll;

import java.util.List;

import bo.Encheres;
import dal.DAOFactory;
import dal.EncheresDAO;
import servlet.BusinessException;


public class EncheresManager {

	private BusinessException businessException = new BusinessException();
	private EncheresDAO encheresDAO;

	public EncheresManager() {
		this.encheresDAO= DAOFactory.getEncheresDAO();
	}

	public List<Encheres> selectAll() throws BusinessException{
		return this.encheresDAO.selectAll();
	}

	public Encheres selectEnchereById(Integer noArticle) throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}

	public Encheres selectMeilleureOffreById(Integer noArticle) throws BusinessException {
		return this.encheresDAO.selectMeilleureOffreById(noArticle);
	}

	public void updateEnchere(Encheres enchere, Integer idUtilisateurVendeur)  throws BusinessException{
		//credit au vendeur - Récupérer le vendeur 
		Encheres encheMeilleureOffre= this.encheresDAO.selectMeilleureOffreById(enchere.getNoArticle());

		//Meilleure Offre donc l'ancienne
		//SI prix vente = 0, echere initialisé donc pas de meilleure offr.
		if(encheMeilleureOffre.getPrixVente() != 0) {
			this.encheresDAO.updateCreditAuVendeur(idUtilisateurVendeur, enchere.getPrixVente() - encheMeilleureOffre.getPrixVente());
			this.encheresDAO.updateCreditAncienUtilisateur(encheMeilleureOffre);
			
		}else {
			this.encheresDAO.updateCreditAuVendeur(idUtilisateurVendeur, enchere.getPrixVente());

		}
		this.encheresDAO.updateEnchereEtCreditUtilisateur(enchere);
	}
	
}
