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

import bll.ArticlesVendusManager;
import bll.CategoriesManager;
import bll.RetraitsManager;
import bll.UtilisateurManager;
import bo.ArticlesVendus;
import bo.Categories;
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
		HttpSession session =  request.getSession(false); 
		Integer id = Integer.parseInt(request.getParameter("id"));
		Integer idSession = (Integer) session.getAttribute("id");

		try {
			
			ArticlesVendus articleVendu = articleManager.selectArticleById(id);
			Retraits retrait = retraitManager.selectRetraitByIdArticle(articleVendu.getNoArticle());
			Utilisateur utilisateur = utilisateurManager.selectUser(articleVendu.getNoUtilisateur());
			Categories  categorie = categorieManager.selectCategorieById(articleVendu.getNoCategorie());
			
			//TODO faire recuperer enchere meilleure offre
			//Desactiver le bouton proposition si l'utilisateur connecté est le vendeur
			if(idSession == articleVendu.getNoUtilisateur()) {
				request.setAttribute("noPropositon", "noPropositon");	
			}
			
			listeArticle.add(articleVendu);
			listeRetrait.add(retrait);
			request.setAttribute("listeArticle", listeArticle);	
			request.setAttribute("listeRetrait", listeRetrait);	
			request.setAttribute("nomCategorie", categorie.getLibelle());	
			request.setAttribute("pseudoVendeur", utilisateur.getPseudo());	


			rd = request.getRequestDispatcher("/WEB-INF/detailsVente.jsp");
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
		ArticlesVendusManager articleManager = new ArticlesVendusManager();
		try {
			ArticlesVendus articleVendu = new ArticlesVendus();
			
		}catch(BusinessException e) {
			e.printStackTrace();
			request.setAttribute("listeCodesErreur",e.getListeCodesErreur());
		}

	}

}
