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
		private static final String SELECT_BY_PSEUDO_PASSWORD="SELECT * FROM UTILISATEURS where pseudo = ? and mot_de_passe = ?";
		private static final String SELECT_BY_ID="SELECT * FROM UTILISATEURS where no_utilisateur = ?";
		
		

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
		public Utilisateur selectUser(String id) throws BusinessException {
			if(id == null)
			{
				BusinessException businessException = new BusinessException();
				businessException.ajouterErreur(CodesResultatDAL.INSERT_OBJET_NULL);
				throw businessException;
			}
			Utilisateur utilisateur = null;
			
			try(Connection cnx = ConnectionProvider.getConnection())
			{
				try
				{
				PreparedStatement pstmt = cnx.prepareStatement(SELECT_BY_ID);
				pstmt.setString(1, id);
				ResultSet rs = pstmt.executeQuery();

				while (rs.next()){
					utilisateur = new Utilisateur();
					utilisateur.setId(rs.getInt("no_utilisateur"));
					utilisateur.setPseudo(rs.getString("pseudo"));
					utilisateur.setPrenom(rs.getString("prenom"));
					utilisateur.setNom(rs.getString("nom"));
					utilisateur.setEmail(rs.getString("email"));
					utilisateur.setRue(rs.getString("rue"));
					utilisateur.setTelephone(rs.getString("telephone"));
					utilisateur.setCodePostal(rs.getString("code_postal"));
					utilisateur.setVille(rs.getString("ville"));
					utilisateur.setMotDePasse(rs.getString("mot_de_passe"));
				}
				rs.close();
				pstmt.close();
				return utilisateur;
				
			}
			catch(Exception e)
			{
				e.printStackTrace();
				System.out.println("erreur");
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
		

