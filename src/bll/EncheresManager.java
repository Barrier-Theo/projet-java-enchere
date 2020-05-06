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

	public void updateEnchere(Encheres enchere)  throws BusinessException{
		this.encheresDAO.updateEnchere(enchere);
	}
	
}
