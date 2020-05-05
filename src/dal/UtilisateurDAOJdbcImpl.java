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

		private static final String INSERT_UTILISATEUR="INSERT INTO UTILISATEURS VALUES(?,?,?,?,?,?,?,?,?,?,?,?)";
		private static final String SELECT_ALL="SELECT * FROM UTILISATEURS";
		private static final String SELECT_BY_PSEUDO_PASSWORD="SELECT * FROM UTILISATEURS where pseudo = ? and mot_de_passe = ?";
		private static final String SELECT_SPEUDO_EMAIL_UNICITE="SELECT * FROM UTILISATEURS;";
		private static final String SELECT_BY_ID="SELECT * FROM UTILISATEURS where no_utilisateur = ?";
		//On ne supprime pas son compte, on désactive son compte. (Possiblité de revenir un jour si dieu le veut)
		private static final String UPDATE_UTILISATEUR_DESACTIVE="UPDATE UTILISATEURS SET isDelete = 1 WHERE no_utilisateur = ?";
		private static final String MODIFIER_UTILISATEUR="UPDATE UTILISATEURS SET pseudo = ?, nom = ?, prenom = ?, email = ?,"
				+ "telephone = ?, rue = ?, code_postal = ?, ville = ?,mot_de_passe = ? WHERE no_utilisateur = ?;";




		@Override
		public List<Utilisateur> selectAll() throws BusinessException {
			List<Utilisateur> userListe = new ArrayList<Utilisateur>();
			try(Connection cnx = ConnectionProvider.getConnection())
			{
				PreparedStatement pstmt = cnx.prepareStatement(SELECT_ALL);
				ResultSet rs = pstmt.executeQuery();
	
				while(rs.next())
				{
					userListe.add(new Utilisateur(rs.getInt("no_utilisateur"), rs.getString("pseudo"), rs.getString("nom"), rs.getString("prenom"), rs.getString("email"), rs.getString("telephone"), rs.getString("rue"), rs.getString("code_postal"), rs.getString("ville"), rs.getInt("credit")));
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
				businessException.ajouterErreur(CodesResultatDAL.SELECT_ALL_LISTE_ECHEC);
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
						if(rs.getBoolean("isDelete")) {
							BusinessException businessException = new BusinessException();
							businessException.ajouterErreur(CodesResultatDAL.COMPTE_DESACTIVE);
							throw businessException;
						}
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
				businessException.ajouterErreur(CodesResultatDAL.COMPTE_DESACTIVE);
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
						Integer isDelete = (!utilisateur.getIsDelete()) ? 0 : 1;
						pstmt.setInt(11, isDelete);
						pstmt.setInt(12, isDelete);
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
				}
				
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
			
			return erreur;
		}
		
		@Override
		public Utilisateur selectUser(Integer id) throws BusinessException {
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
				pstmt.setInt(1, id);
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


		@Override
		public void supprimerUtilisateur(Integer id) throws BusinessException {
			try(Connection cnx = ConnectionProvider.getConnection())
			{
				PreparedStatement pstmt = cnx.prepareStatement(UPDATE_UTILISATEUR_DESACTIVE);
				pstmt.setInt(1, id);
				pstmt.executeUpdate();
				cnx.commit();
			} catch (SQLException e) {
				e.printStackTrace();
				BusinessException businessException = new BusinessException();
				businessException.ajouterErreur(CodesResultatDAL.DESACTIVATION_UTILISATEUR);
				throw businessException;
			}
		}

		@Override
		public void modifierUtilisateur(Utilisateur utilisateur) throws BusinessException {
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
						pstmt = cnx.prepareStatement(MODIFIER_UTILISATEUR);
						pstmt.setString(1, utilisateur.getPseudo());
						pstmt.setString(2, utilisateur.getNom());
						pstmt.setString(3, utilisateur.getPrenom());
						pstmt.setString(4, utilisateur.getEmail());
						pstmt.setString(5, utilisateur.getTelephone());
						pstmt.setString(6, utilisateur.getRue());
						pstmt.setString(7, utilisateur.getCodePostal());
						pstmt.setString(8, utilisateur.getVille());
						pstmt.setString(9, utilisateur.getMotDePasse());
						pstmt.setInt(10, utilisateur.getId());
						pstmt.executeUpdate();
						pstmt.close();

					cnx.commit();
				}
				catch(Exception e)
				{
					e.printStackTrace();
					System.out.println("erreur modification utilisateur");
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
		public String getPseudoFromDb(Integer id) throws BusinessException {
			Utilisateur utilisateur = this.selectUser(id);
			return utilisateur.getPseudo();
		}
	}
