package dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import bo.ArticlesVendus;
import bo.Retraits;
import servlet.BusinessException;

public class ArticlesVendusDAOJdbcImpl implements ArticlesVendusDAO {

	private static final String INSERT_ARTICLES_VENDUS="INSERT INTO ARTICLES_VENDUS VALUES(?,?,?,?,?,?,?,?)";
	private static final String INSERT_RETRAIT="INSERT INTO RETRAITS VALUES(?,?,?,?)";

	public void insert(ArticlesVendus unArticleVendu, Retraits unRetrait) throws BusinessException {
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
				ResultSet rs;
				
				pstmt = cnx.prepareStatement(INSERT_ARTICLES_VENDUS, PreparedStatement.RETURN_GENERATED_KEYS);
				pstmt.setString(1, unArticleVendu.getNomArticle());
				pstmt.setString(2, unArticleVendu.getDescription());
				pstmt.setString(3, unArticleVendu.getDateDebutEncheres().toString());
				pstmt.setString(4, unArticleVendu.getDateFinEncheres().toString());
				pstmt.setInt(5, Integer.parseInt(unArticleVendu.getMiseAPrix().toString()));
				pstmt.setInt(6, Integer.parseInt(unArticleVendu.getPrixVente().toString()));
				pstmt.setInt(7, unArticleVendu.getNoUtilisateur());
				pstmt.setInt(8, unArticleVendu.getNoCategorie());

				pstmt.executeUpdate();
				rs = pstmt.getGeneratedKeys();
				if(rs.next())
				{
					unRetrait.setNoArticle(rs.getInt(1));
				}
				
				rs.close();
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
			
			try
			{
				cnx.setAutoCommit(false);
				PreparedStatement pstmt;
				pstmt = cnx.prepareStatement(INSERT_RETRAIT);
				pstmt.setInt(1, unRetrait.getNoArticle());
				pstmt.setString(2, unRetrait.getRue());
				pstmt.setString(3, unRetrait.getCodePostal());
				pstmt.setString(4, unRetrait.getVille());

				pstmt.executeUpdate();
				pstmt.close();

				cnx.commit();
			}
			catch(Exception e)
			{
				e.printStackTrace();
				System.out.println("erreur insert retrait");
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
