package dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import bo.Categories;
import servlet.BusinessException;

public class CategoriesDAOJdbcImpl implements CategoriesDAO {
	
	private static final String SELECT_ALL="SELECT * FROM CATEGORIES";
	
	@Override
	public List<Categories> selectAll() throws BusinessException {
		List<Categories> listeCategories = new ArrayList<Categories>();
		try(Connection cnx = ConnectionProvider.getConnection())
		{
			PreparedStatement pstmt = cnx.prepareStatement(SELECT_ALL);
			ResultSet rs = pstmt.executeQuery();

			while(rs.next())
			{
				listeCategories.add(new Categories(rs.getInt("no_categorie"), rs.getString("libelle")));
			}
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.SELECT_ALL_LISTE_ECHEC);
			throw businessException;
		}
		
		return listeCategories;
	}

}
