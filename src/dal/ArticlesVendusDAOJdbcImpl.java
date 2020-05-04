package dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import bo.ArticlesVendus;
import bo.Retraits;
import servlet.BusinessException;

public class ArticlesVendusDAOJdbcImpl implements ArticlesVendusDAO {

	private static final String INSERT_ARTICLES_VENDUS="INSERT INTO ARTICLES_VENDUS VALUES(?,?,?,?,?,?,?,?)";
	private static final String INSERT_RETRAIT="INSERT INTO RETRAITS VALUES(?,?,?,?)";
	private static final String SELECT_ALL="SELECT * FROM ARTICLES_VENDUS";
	
	@Override
	public List<ArticlesVendus> selectAll() throws BusinessException {
		List<ArticlesVendus> listeArticles = new ArrayList<ArticlesVendus>();
		try(Connection cnx = ConnectionProvider.getConnection())
		{
			PreparedStatement pstmt = cnx.prepareStatement(SELECT_ALL);
			ResultSet rs = pstmt.executeQuery();

			while(rs.next())
			{
				listeArticles.add(new ArticlesVendus(rs.getInt("no_article"), rs.getString("nom_article"), rs.getString("description"), rs.getDate("date_debut_encheres").toLocalDate(), rs.getDate("date_fin_encheres").toLocalDate(), rs.getInt("prix_initial"), rs.getInt("prix_vente"), rs.getInt("no_utilisateur"), rs.getInt("no_categorie")));
			}
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.SELECT_ALL_LISTE_ECHEC);
			throw businessException;
		}
		
		return listeArticles;
	}

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
