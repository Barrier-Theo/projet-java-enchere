package dal;

public abstract class DAOFactory {

	public static UtilisateurDAO getUtilisateurDAO()
	{
		return new UtilisateurDAOJdbcImpl();
	}

	public static ArticlesVendusDAO getArticlesVendusDAO() {
		return new ArticlesVendusDAOJdbcImpl();
	}

	public static CategoriesDAO getCategoriesDAO() {
		return new CategoriesDAOJdbcImpl();
	}

	public static RetraitsDAO getRetraitsDAO() {
		return new RetraitsDAOJdbcImpl();
	}
}
