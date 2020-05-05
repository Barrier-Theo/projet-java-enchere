package dal;

import java.util.List;

import bo.Categories;
import bo.Utilisateur;
import servlet.BusinessException;

public interface CategoriesDAO {

	public List<Categories> selectAll() throws BusinessException;
 
	public Categories selectCategorieById(Integer noCategorie) throws BusinessException;

	
}
