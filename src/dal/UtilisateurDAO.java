package dal;

import java.util.List;

import bo.Utilisateur;
import servlet.BusinessException;

public interface UtilisateurDAO {
		
	public List<Utilisateur> selectAll() throws BusinessException;
	
	public Integer findIdByPseudoPassword(String pseudo, String password) throws BusinessException;
	
	public void ajouterUtilisateur(Utilisateur utilisateur)  throws BusinessException;
	
}
