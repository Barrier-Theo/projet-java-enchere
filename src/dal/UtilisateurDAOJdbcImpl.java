package dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bo.Utilisateur;
import dal.CodesResultatDAL;
import dal.ConnectionProvider;
import servlet.BusinessException;

public class UtilisateurDAOJdbcImpl implements UtilisateurDAO {

		private static final String INSERT_UTILISATEUR="";
		private static final String SELECT_ALL="SELECT * FROM UTILISATEURS";
		

		public void insert(Utilisateur liste) throws BusinessException {
			if(liste==null)
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
					if(liste.getId()==0)
					{
						pstmt = cnx.prepareStatement(INSERT_UTILISATEUR, PreparedStatement.RETURN_GENERATED_KEYS);
						pstmt.setString(1, liste.getNom());
						pstmt.executeUpdate();
						rs = pstmt.getGeneratedKeys();
						if(rs.next())
						{
							liste.setId(rs.getInt(1));
						}
						rs.close();
						pstmt.close();
					}
					
					cnx.commit();
				}
				catch(Exception e)
				{
					e.printStackTrace();
					System.out.println("erreur insert liste");
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


		@Override
		public List<Utilisateur> selectAll() throws BusinessException {
			List<Utilisateur> userListe = new ArrayList<Utilisateur>();
			try(Connection cnx = ConnectionProvider.getConnection())
			{
				PreparedStatement pstmt = cnx.prepareStatement(SELECT_ALL);
				ResultSet rs = pstmt.executeQuery();
	
				while(rs.next())
				{
					userListe.add(new Utilisateur(rs.getInt("no_utilisateur"), rs.getString("nom")));
				}
				
			}
			catch(Exception e)
			{
				e.printStackTrace();
				BusinessException businessException = new BusinessException();
				businessException.ajouterErreur(CodesResultatDAL.SELECT_ALL_LISTE_ECHEC);
				throw businessException;
			}
			
			return userListe;
		}
}
