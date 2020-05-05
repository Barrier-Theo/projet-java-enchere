package dal;

import java.util.List;

import bo.Encheres;
import servlet.BusinessException;

public interface EncheresDAO {

	public List<Encheres> selectAll() throws BusinessException;
	
}
