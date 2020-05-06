package servlet;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.eclipse.jdt.internal.compiler.parser.ParserBasicInformation;

import bll.ArticlesVendusManager;
import bll.CategoriesManager;
import bll.CodesResultatBLL;
import bll.EncheresManager;
import bll.RetraitsManager;
import bll.UtilisateurManager;
import bo.ArticlesVendus;
import bo.Categories;
import bo.Encheres;
import bo.Retraits;
import bo.Utilisateur;

/**
 * Servlet implementation class ServletEnchere
 */
@WebServlet("/enchere")
public class ServletEnchere extends HttpServlet {

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		RequestDispatcher rd = null;
		List<ArticlesVendus> listeArticle =  new ArrayList<ArticlesVendus>();
		List<Retraits> listeRetrait =  new ArrayList<Retraits>();
		RetraitsManager retraitManager  = new RetraitsManager();
		ArticlesVendusManager articleManager = new ArticlesVendusManager();
		UtilisateurManager utilisateurManager = new UtilisateurManager();
		CategoriesManager categorieManager  = new CategoriesManager();
		EncheresManager enchereManager = new EncheresManager();
		HttpSession session =  request.getSession(false); 
		Integer id = Integer.parseInt(request.getParameter("id"));
		Integer idSession = (Integer) session.getAttribute("id");
		Integer meilleureOffre = 0;
		String pseudoMeilleureOffre = "";
		try {
			
			ArticlesVendus articleVendu = articleManager.selectArticleById(id);
			Retraits retrait = retraitManager.selectRetraitByIdArticle(articleVendu.getNoArticle());
			Utilisateur utilisateur = utilisateurManager.selectUser(articleVendu.getNoUtilisateur());
			Categories  categorie = categorieManager.selectCategorieById(articleVendu.getNoCategorie());
			Encheres meilleureEnchere = enchereManager.selectMeilleureOffreById(articleVendu.getNoArticle());
			//TODO faire recuperer enchere meilleure offre
			meilleureOffre =  meilleureEnchere.getPrixVente();
			
			Utilisateur utilisateurMeilleurOffre = utilisateurManager.selectUser(meilleureEnchere.getNoUtilisateur());
			
			if(utilisateurMeilleurOffre != null) {
				pseudoMeilleureOffre = utilisateurMeilleurOffre.getPseudo();

			}

			listeArticle.add(articleVendu);
			listeRetrait.add(retrait);
			request.setAttribute("listeArticle", listeArticle);	
			request.setAttribute("listeRetrait", listeRetrait);	
			request.setAttribute("nomCategorie", categorie.getLibelle());	
			request.setAttribute("pseudoVendeur", utilisateur.getPseudo());	
			request.setAttribute("meilleureOffre", meilleureOffre);
			request.setAttribute("speudoMeilleureOffre", pseudoMeilleureOffre);
			request.setAttribute("idSession", idSession);	
			request.setAttribute("no_utilisateur", articleVendu.getNoUtilisateur());
			request.setAttribute("minProposition", this.minProposition(meilleureOffre, articleVendu.getMiseAPrix()));
			request.setAttribute("credit", utilisateur.getCredit());
			rd = request.getRequestDispatcher("WEB-INF/detailsVente.jsp");
			rd.forward(request, response);
			
		}catch(BusinessException e) {
			e.printStackTrace();
			request.setAttribute("listeCodesErreur",e.getListeCodesErreur());
		}
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = null;
		EncheresManager articleManager = new EncheresManager();
		HttpSession session = request.getSession(false);
		Integer idUtilisateur = (Integer) session.getAttribute("id");
		Integer idArticle = Integer.parseInt(request.getParameter("id"));
		Integer montant = Integer.parseInt(request.getParameter("montant"));
		
		Encheres enchere = new Encheres( idUtilisateur , idArticle, montant);
		
		UtilisateurManager utilisateurManager = new UtilisateurManager();
		
		Utilisateur utilisateur = utilisateurManager.selectUser(idUtilisateur);
		BusinessException businessException = 	new BusinessException();

		
		try {
			if(utilisateur.getCredit() < montant){
				businessException.ajouterErreur(CodesResultatBLL.PAS_ASSEZ_CREDIT);
				throw businessException;
			}
			
			articleManager.updateEnchere(enchere);
			rd = request.getRequestDispatcher("/ServletRedirectForm");
			rd.forward(request, response);
		}catch(BusinessException e) {
			e.printStackTrace();
			request.setAttribute("listeCodesErreur",e.getListeCodesErreur());
			doGet(request,response);
		}
		

	}
	
	protected Integer minProposition(Integer meilleureOffre, Integer miseAPrix ) {
		if(meilleureOffre == 0) {
			 return (miseAPrix+1); 
		}else {
			return (meilleureOffre+1);
		}
	}

}
