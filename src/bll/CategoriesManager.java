package bll;

import java.util.List;

import bo.Categories;
import dal.CategoriesDAO;
import dal.DAOFactory;
import servlet.BusinessException;

public class CategoriesManager {

	private CategoriesDAO categoriesDAO;
	
	public CategoriesManager() {
		this.categoriesDAO=DAOFactory.getCategoriesDAO();
	}
	
	public List<Categories> selectAll() throws BusinessException{
		return this.categoriesDAO.selectAll();
	}

	public Categories selectCategorieById(Integer noCategorie)  throws BusinessException{
		return this.categoriesDAO.selectCategorieById(noCategorie);
	}
}
