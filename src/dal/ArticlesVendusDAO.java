package dal;

import bo.ArticlesVendus;
import servlet.BusinessException;

public interface ArticlesVendusDAO {

	public void insert(ArticlesVendus unNouvelArticleAVendre) throws BusinessException;

}
