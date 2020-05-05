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
import bll.UtilisateurManager;
import bo.Categories;
import bo.Retraits;
import bo.Utilisateur;

@WebServlet("/ServletNouvelArticleVendu")
public class ServletNouvelArticleVendu extends HttpServlet{

	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ServletNouvelArticleVendu() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = null;

		HttpSession session = request.getSession();
		
		Integer idUtilisateur = (Integer) session.getAttribute("id");
		
		CategoriesManager categoriesManager = new CategoriesManager();
		List<Categories> listeCategories = new ArrayList<>();
		
		UtilisateurManager utilisateurManager = new UtilisateurManager();
		Utilisateur unUtilisateur = new Utilisateur();
		List<Utilisateur> listeUtilisateur = new ArrayList<>();
		
		try {
			unUtilisateur = utilisateurManager.selectUser(idUtilisateur);
			listeUtilisateur.add(unUtilisateur);
			request.setAttribute("unUtilisateur", listeUtilisateur);
			listeCategories = categoriesManager.selectAll();
			request.setAttribute("listeCategories", listeCategories);
			
		}catch(BusinessException e) {
			e.printStackTrace();
			request.setAttribute("listeCodesErreur",e.getListeCodesErreur());
		}
		
		rd = request.getRequestDispatcher("/nouvelleVente.jsp");
		rd.forward(request, response);


	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		RequestDispatcher rd = null;
		HttpSession session = request.getSession();

		ArticlesVendusManager articlesVendusManager = new ArticlesVendusManager();
		CategoriesManager categoriesManager = new CategoriesManager();
		List<Categories> listeCategories = new ArrayList<>();
		Integer idUtilisateur = (Integer)session.getAttribute("id");
		
		try {
			Retraits unRetrait = new Retraits(null, request.getParameter("rueArticle"), request.getParameter("codePostalArticle"), request.getParameter("villeArticle"));
			articlesVendusManager.ajouterVente(request.getParameter("libelleArticle"), request.getParameter("descriptionArticle"), LocalDate.parse(request.getParameter("dateDebutArticle")), LocalDate.parse(request.getParameter("dateFinArticle")), Integer.parseInt(request.getParameter("prixDepartArticle")), 0, idUtilisateur, Integer.parseInt(request.getParameter("idCategorie")), unRetrait);
			listeCategories = categoriesManager.selectAll();
			request.setAttribute("listeCategories", listeCategories);
			
		}catch(BusinessException e) {
			e.printStackTrace();
			request.setAttribute("listeCodesErreur",e.getListeCodesErreur());
		}
		
		if(session.getAttribute("id").toString() == null) {
			rd = request.getRequestDispatcher("/connexion.jsp");
		}else {
			rd = request.getRequestDispatcher("/ServletAccueil");
		}
		rd.forward(request, response);


	}


}
