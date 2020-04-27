package dal;

public abstract class DAOFactory {
	
	public static UtilisateurDAO getAvisDAO()
	{
		return new UtilisateurDAOJdbcImpl();
	}
}
