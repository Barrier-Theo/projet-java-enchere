package bll;

import java.time.LocalDate;

import bo.ArticlesVendus;
import dal.ArticlesVendusDAO;
import dal.DAOFactory;
import servlet.BusinessException;

public class ArticlesVendusManager {

	private BusinessException businessException = new BusinessException();
	private ArticlesVendusDAO articlesVendusDAO;

	public ArticlesVendusManager() {
		this.articlesVendusDAO= DAOFactory.getArticlesVendusDAO();

	}

	public void ajouterVente(String libelleArticle, String descriptionArticle, LocalDate dateDebutArticle,
			LocalDate dateFinArticle, Integer prixInitialArticle, Integer prixVenteArticle, Integer idUtilisateur, Integer idCategorie) throws BusinessException {

		ArticlesVendus unNouvelArticleAVendre = new ArticlesVendus(null, libelleArticle, descriptionArticle, dateDebutArticle, dateFinArticle,
				prixInitialArticle, prixVenteArticle, idUtilisateur, idCategorie);

		if(!businessException.hasErreurs())
		{
			this.articlesVendusDAO.insert(unNouvelArticleAVendre);
		}

		if(businessException.hasErreurs())
		{
			throw businessException;
		}
	}


}