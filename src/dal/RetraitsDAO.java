package dal;

import bo.Retraits;
import servlet.BusinessException;

public interface RetraitsDAO {
	public Retraits selectRetraitByIdArticle(Integer noArticle)  throws BusinessException;
}
