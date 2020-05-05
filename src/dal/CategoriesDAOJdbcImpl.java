package dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import bo.Categories;
import bo.Utilisateur;
import servlet.BusinessException;

public class CategoriesDAOJdbcImpl implements CategoriesDAO {
	
	private static final String SELECT_ALL="SELECT * FROM CATEGORIES";
	private static final String SELECT_BY_ID_ARTICLE="SELECT * FROM CATEGORIES where no_categorie = ?";
	
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

	@Override
	public Categories selectCategorieById(Integer noCategorie) throws BusinessException {
		// TODO Auto-generated method stub
		if(noCategorie == null)
		{
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.SELECT_OBJET);
			throw businessException;
		}
		Categories categorie = null;
		
		try(Connection cnx = ConnectionProvider.getConnection())
		{
			try
			{
			PreparedStatement pstmt = cnx.prepareStatement(SELECT_BY_ID_ARTICLE);
			pstmt.setInt(1, noCategorie);
			ResultSet rs = pstmt.executeQuery();
				while (rs.next()){
					categorie = new Categories();
					categorie.setNoCategorie(rs.getInt("no_categorie"));
					categorie.setLibelle(rs.getString("libelle"));
					
				}
			rs.close();
			pstmt.close();
			return categorie;
			}
			catch(Exception e)
			{
				e.printStackTrace();
				System.out.println("erreur");
				throw e;
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.SELECT_OBJET);
			throw businessException;
		}
	}
}



