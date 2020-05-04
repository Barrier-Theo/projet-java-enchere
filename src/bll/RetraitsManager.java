package bll;

import dal.DAOFactory;
import dal.RetraitsDAO;
import servlet.BusinessException;

public class RetraitsManager {

	private BusinessException businessException = new BusinessException();

	private RetraitsDAO retraitsDAO;
	
	public RetraitsManager() {
		this.retraitsDAO=DAOFactory.getRetraitsDAO();
	}
}
