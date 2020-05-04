package dal;

import java.util.List;

import bo.ArticlesVendus;
import bo.Retraits;
import servlet.BusinessException;

public interface ArticlesVendusDAO {

	public void insert(ArticlesVendus unNouvelArticleAVendre, Retraits unRetrait) throws BusinessException;

	public List<ArticlesVendus> selectAll() throws BusinessException;

}
