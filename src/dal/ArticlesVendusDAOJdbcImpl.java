package dal;

import java.sql.Connection;
import java.sql.PreparedStatement;

import bo.ArticlesVendus;
import servlet.BusinessException;

public class ArticlesVendusDAOJdbcImpl implements ArticlesVendusDAO {

	private static final String INSERT_ARTICLES_VENDUS="INSERT INTO ARTICLES_VENDUS VALUES(?,?,?,?,?,?,?,?)";

	public void insert(ArticlesVendus unArticleVendu) throws BusinessException {
		if(unArticleVendu==null)
		{
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.INSERT_OBJET_NULL);
			throw businessException;
		}

		try(Connection cnx = ConnectionProvider.getConnection())
		{
			try
			{
				cnx.setAutoCommit(false);
				PreparedStatement pstmt;

				pstmt = cnx.prepareStatement(INSERT_ARTICLES_VENDUS);
				pstmt.setString(1, unArticleVendu.getNomArticle());
				pstmt.setString(2, unArticleVendu.getDescription());
				pstmt.setString(3, unArticleVendu.getDateDebutEncheres().toString());
				pstmt.setString(4, unArticleVendu.getDateFinEncheres().toString());
				pstmt.setInt(5, Integer.parseInt(unArticleVendu.getMiseAPrix().toString()));
				pstmt.setInt(6, Integer.parseInt(unArticleVendu.getPrixVente().toString()));
				pstmt.setInt(7, unArticleVendu.getNoUtilisateur());
				pstmt.setInt(8, unArticleVendu.getNoCategorie());

				pstmt.executeUpdate();
				pstmt.close();

				cnx.commit();
			}
			catch(Exception e)
			{
				e.printStackTrace();
				System.out.println("erreur insert article vendu");
				cnx.rollback();
				throw e;
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.INSERT_OBJET_ECHEC);
			throw businessException;
		}

	}
}
