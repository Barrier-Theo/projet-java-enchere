package dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import bo.Retraits;
import bo.Utilisateur;
import servlet.BusinessException;

public class RetraitsDAOJdbcImpl implements RetraitsDAO {
	private static final String SELECT_BY_ID_ARTICLE="SELECT * FROM RETRAITS where no_article = ?";

	@Override
	public Retraits selectRetraitByIdArticle(Integer noArticle) throws BusinessException {
		if(noArticle == null)
		{
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.SELECT_OBJET);
			throw businessException;
		}
		Retraits retrait = null;
		
		try(Connection cnx = ConnectionProvider.getConnection())
		{
			try
			{
			PreparedStatement pstmt = cnx.prepareStatement(SELECT_BY_ID_ARTICLE);
			pstmt.setInt(1, noArticle);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()){
				retrait = new Retraits();
				retrait.setNoArticle(rs.getInt("no_article"));
				retrait.setRue(rs.getString("rue"));
				retrait.setCodePostal(rs.getString("code_postal"));
				retrait.setVille(rs.getString("ville"));
			}
			rs.close();
			pstmt.close();
			return retrait;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			System.out.println("erreur select retrait avec id article");
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
