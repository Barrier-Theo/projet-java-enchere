package dal;

/**
 * Les codes disponibles sont entre 10000 et 19999
 */
public abstract class CodesResultatDAL {
	
	/**
	 * Echec général quand tentative d'ajouter un objet null
	 */
	public static final int SELECT_ALL_LISTE_ECHEC=10000;

	public static final int INSERT_OBJET_NULL=10001;
	
	public static final int INSERT_OBJET_ECHEC=10002;
	
	public static final int COMPTE_DESACTIVE=10003;
	
	public static final int SELECT_OBJET=10004;
	
	public static final int DESACTIVATION_UTILISATEUR=10005;
	
	public static final int UPDATE_OBJET_NULL=10006;

	public static final int SELECT_BY_CATEGORIE_LISTE_ECHEC = 10007;
	
	
	
	
}
