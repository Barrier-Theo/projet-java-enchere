package dal;

import bo.ArticlesVendus;
import bo.Retraits;
import servlet.BusinessException;

public interface ArticlesVendusDAO {

	public void insert(ArticlesVendus unNouvelArticleAVendre, Retraits unRetrait) throws BusinessException;

}
