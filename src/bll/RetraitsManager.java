package bll;

import bo.Retraits;
import dal.DAOFactory;
import dal.RetraitsDAO;
import servlet.BusinessException;

public class RetraitsManager {

	private BusinessException businessException = new BusinessException();

	private RetraitsDAO retraitsDAO;
	
	public RetraitsManager() {
		this.retraitsDAO=DAOFactory.getRetraitsDAO();
	}
	
	public Retraits selectRetraitByIdArticle(Integer noArticle) throws BusinessException {
		return 	this.retraitsDAO.selectRetraitByIdArticle(noArticle);
	}
}
