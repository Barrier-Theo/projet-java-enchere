package dal;

import java.util.List;

import bo.Encheres;
import servlet.BusinessException;

public interface EncheresDAO {

	public List<Encheres> selectAll() throws BusinessException;

	public Encheres selectMeilleureOffreById(Integer noArticle) throws BusinessException;

	public void updateEnchere(Encheres enchere) throws BusinessException;
	
}
