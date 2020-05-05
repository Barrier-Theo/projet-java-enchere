package dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import bo.Categories;
import bo.Encheres;
import servlet.BusinessException;

public class EncheresDAOJdbcImpl implements EncheresDAO {

private static final String SELECT_ALL="SELECT * FROM ENCHERES";
	
	@Override
	public List<Encheres> selectAll() throws BusinessException {
		List<Encheres> listeEnchere = new ArrayList<Encheres>();
		try(Connection cnx = ConnectionProvider.getConnection())
		{
			PreparedStatement pstmt = cnx.prepareStatement(SELECT_ALL);
			ResultSet rs = pstmt.executeQuery();

			while(rs.next())
			{
				listeEnchere.add(new Encheres(rs.getInt("no_utilisateur"), rs.getInt("no_article"), rs.getDate("date_enchere").toLocalDate(), rs.getInt("montant_enchere")));
			}
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.SELECT_ALL_LISTE_ECHEC);
			throw businessException;
		}
		
		return listeEnchere;
	}

}
