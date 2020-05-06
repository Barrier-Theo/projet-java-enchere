package dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import bo.ArticlesVendus;
import bo.Categories;
import bo.Encheres;
import servlet.BusinessException;

public class EncheresDAOJdbcImpl implements EncheresDAO {

private static final String SELECT_ALL="SELECT * FROM ENCHERES";
private static final String SELECT_MAX_MONTANT_ENCHERE_BY_ID="SELECT TOP 1 MAX(montant_enchere) AS montant, no_article, no_utilisateur, date_enchere FROM ENCHERES  where no_article = ? GROUP BY no_article, no_utilisateur,date_enchere";
private static final String UPDATE_ENCHERE="UPDATE ENCHERES SET no_utilisateur = ?, montant_enchere = ?  WHERE no_article = ? ";
private static final String UPDATE_CREDIT_UTILISATEUR_MOINS="UPDATE UTILISATEURS SET credit = credit - ? where no_utilisateur = ?";
private static final String UPDATE_CREDIT_UTILISATEUR_PLUS="UPDATE UTILISATEURS SET credit = credit + ? where no_utilisateur = ?";




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

	@Override
	public Encheres selectMeilleureOffreById(Integer noArticle) throws BusinessException {
			if(noArticle == null)
			{
				BusinessException businessException = new BusinessException();
				businessException.ajouterErreur(CodesResultatDAL.SELECT_OBJET);
				throw businessException;
			}
			Encheres enchere  = null;
			
			try(Connection cnx = ConnectionProvider.getConnection())
			{
				try
				{
				PreparedStatement pstmt = cnx.prepareStatement(SELECT_MAX_MONTANT_ENCHERE_BY_ID);
				pstmt.setInt(1, noArticle);
				ResultSet rs = pstmt.executeQuery();
	
				while (rs.next()){
					enchere = new Encheres();
					enchere.setNoArticle(rs.getInt("no_article"));
					enchere.setDateEnchere(rs.getDate("date_enchere").toLocalDate());
					enchere.setNoUtilisateur(rs.getInt("no_utilisateur"));
					enchere.setPrixVente(rs.getInt("montant"));
				}
				rs.close();
				pstmt.close();
			}
			catch(Exception e)
			{
				e.printStackTrace();
				System.out.println("erreur SELECT ARTICLE BY ID");
				throw e;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.SELECT_OBJET);
			throw businessException;
		}
		return enchere;	
	}

	@Override
	public void updateEnchereEtCreditUtilisateur(Encheres enchere) throws BusinessException {
		if(enchere==null)
		{
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.UPDATE_OBJET_NULL);
			throw businessException;
		}

		try(Connection cnx = ConnectionProvider.getConnection())
		{
			try
			{
				cnx.setAutoCommit(false);
				PreparedStatement pstmt;
				ResultSet rs;
					pstmt = cnx.prepareStatement(UPDATE_ENCHERE);
					pstmt.setInt(1, enchere.getNoUtilisateur());
					pstmt.setInt(2, enchere.getPrixVente());
					pstmt.setInt(3, enchere.getNoArticle());
					pstmt.executeUpdate();
					pstmt.close();

				cnx.commit();
			}
			catch(Exception e)
			{
				e.printStackTrace();
				System.out.println("erreur modification encheres");
				cnx.rollback();
				throw e;
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.UPDATE_OBJET_NULL);
			throw businessException;
		}
		try(Connection cnx = ConnectionProvider.getConnection())
		{
			try
			{
				cnx.setAutoCommit(false);
				PreparedStatement pstmt;
				ResultSet rs;
					pstmt = cnx.prepareStatement(UPDATE_CREDIT_UTILISATEUR_MOINS);
					pstmt.setInt(1, enchere.getPrixVente());
					pstmt.setInt(2, enchere.getNoUtilisateur());
					pstmt.executeUpdate();
					pstmt.close();

				cnx.commit();
			}
			catch(Exception e)
			{
				e.printStackTrace();
				System.out.println("erreur update credit utilisateur");
				cnx.rollback();
				throw e;
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.UPDATE_OBJET_NULL);
			throw businessException;
		}

		
	}

	@Override
	public void updateCreditAncienUtilisateur(Encheres enchere) throws BusinessException {
		
		try(Connection cnx = ConnectionProvider.getConnection())
		{
			try
			{
				cnx.setAutoCommit(false);
				PreparedStatement pstmt;
				ResultSet rs;
					pstmt = cnx.prepareStatement(UPDATE_CREDIT_UTILISATEUR_PLUS);
					pstmt.setInt(1, enchere.getPrixVente());
					pstmt.setInt(2, enchere.getNoUtilisateur());
					pstmt.executeUpdate();
					pstmt.close();

				cnx.commit();
			}
			catch(Exception e)
			{
				e.printStackTrace();
				System.out.println("erreur update credit utilisateur");
				cnx.rollback();
				throw e;
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.UPDATE_OBJET_NULL);
			throw businessException;
		}

	}

	@Override
	public void updateCreditAuVendeur(Integer idUtilisateurVendeur, Integer prixVente) throws BusinessException {

		try(Connection cnx = ConnectionProvider.getConnection())
		{
			try
			{
				cnx.setAutoCommit(false);
				PreparedStatement pstmt;
				ResultSet rs;
					pstmt = cnx.prepareStatement(UPDATE_CREDIT_UTILISATEUR_PLUS);
					pstmt.setInt(1, prixVente);
					pstmt.setInt(2, idUtilisateurVendeur);
					pstmt.executeUpdate();
					pstmt.close();

				cnx.commit();
			}
			catch(Exception e)
			{
				e.printStackTrace();
				System.out.println("erreur update credit utilisateur");
				cnx.rollback();
				throw e;
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.UPDATE_OBJET_NULL);
			throw businessException;
		}

		
	}

}
