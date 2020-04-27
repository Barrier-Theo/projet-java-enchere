package dal;

import java.util.List;

import bo.Utilisateur;
import servlet.BusinessException;

public interface UtilisateurDAO {
	
	public void insert(Utilisateur repas) throws BusinessException;
	
	public List<Utilisateur> selectAll() throws BusinessException;
	
}
