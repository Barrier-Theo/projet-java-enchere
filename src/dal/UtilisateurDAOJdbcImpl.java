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

		private static final String INSERT_UTILISATEUR="INSERT INTO VALUES(?,?,?,?,?,?,?,?,?)";
		private static final String SELECT_ALL="SELECT * FROM UTILISATEURS";
		private static final String SELECT_BY_PSEUDO_PASSWORD="SELECT * FROM UTILISATEURS where pseudo = ? and mot_de_passe = ?";
		private static final String SELECT_SPEUDO_EMAIL_UNICITE="SELECT * FROM UTILISATEURS where pseudo = ? and mot_de_passe = ?";


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


		@Override
		public Integer findIdByPseudoPassword(String pseudo, String password) throws BusinessException{
			if(pseudo == null && password == null)
			{
				BusinessException businessException = new BusinessException();
				businessException.ajouterErreur(CodesResultatDAL.INSERT_OBJET_NULL);
				throw businessException;
			}
			
			try(Connection cnx = ConnectionProvider.getConnection())
			{
				Integer idUtilisateur = null;
				try
				{
					PreparedStatement pstmt = cnx.prepareStatement(SELECT_BY_PSEUDO_PASSWORD);
					pstmt.setString(1, pseudo);
					pstmt.setString(2, password);
					ResultSet rs = pstmt.executeQuery();
					while(rs.next())
					{	
						idUtilisateur = rs.getInt("no_utilisateur");
					}
					
				
					
					
					rs.close();
					pstmt.close();
					return idUtilisateur;
					
				}
				catch(Exception e)
				{
					e.printStackTrace();
					System.out.println("erreur authentification");
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
		public void ajouterUtilisateur(Utilisateur utilisateur) throws BusinessException {
			if(utilisateur==null)
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
						pstmt = cnx.prepareStatement(INSERT_UTILISATEUR);
						pstmt.setString(1, utilisateur.getPseudo());
						pstmt.setString(2, utilisateur.getNom());
						pstmt.setString(3, utilisateur.getPrenom());
						pstmt.setString(4, utilisateur.getEmail());
						pstmt.setString(5, utilisateur.getTelephone());
						pstmt.setString(6, utilisateur.getRue());
						pstmt.setString(7, utilisateur.getCodePostal());
						pstmt.setString(8, utilisateur.getVille());
						pstmt.setString(9, utilisateur.getMotDePasse());
						pstmt.setInt(10, utilisateur.getCredit());
						//Conversion boolean to int pour bdd
						Integer isAdmin = (!utilisateur.getIsAdmin()) ? 0 : 1;
						pstmt.setInt(11, isAdmin);
						pstmt.executeUpdate();
						pstmt.close();
					
					cnx.commit();
				}
				catch(Exception e)
				{
					e.printStackTrace();
					System.out.println("erreur insert utilisateur");
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
		public boolean verifUnicitePseudoEmail(Utilisateur utilisateur) throws BusinessException {
			List<Utilisateur> userListe = new ArrayList<Utilisateur>();
			boolean erreur= false;
			String pseudo = utilisateur.getPseudo();
			String email = utilisateur.getEmail();
			try(Connection cnx = ConnectionProvider.getConnection())
			{
				PreparedStatement pstmt = cnx.prepareStatement(SELECT_SPEUDO_EMAIL_UNICITE);
				ResultSet rs = pstmt.executeQuery();
	
				while(rs.next())
				{
					if(rs.getString("pseudo").equals(pseudo) || rs.getString("email").equals(email)) {
						erreur = true;
					}
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
			
			return erreur;
		}	
}
