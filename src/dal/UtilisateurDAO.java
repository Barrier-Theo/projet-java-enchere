package dal;

import java.util.List;

import bo.Utilisateur;
import servlet.BusinessException;

public interface UtilisateurDAO {
	
	public void insert(Utilisateur utilisateur) throws BusinessException;
	
	public List<Utilisateur> selectAll() throws BusinessException;
	
	public Integer findIdByPseudoPassword(String pseudo, String password) throws BusinessException;
	
}
