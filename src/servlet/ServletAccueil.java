package servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bll.ArticlesVendusManager;
import bll.CategoriesManager;
import bll.UtilisateurManager;
import bo.ArticlesVendus;
import bo.Categories;
import bo.Utilisateur;

/**
 * Servlet implementation class ServletAccueil
 */
@WebServlet("/ServletAccueil")
public class ServletAccueil extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletAccueil() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = null;
		ArticlesVendusManager listeArticleManager = new ArticlesVendusManager();
		CategoriesManager categoriesManager = new CategoriesManager();
		List<Categories> listeCategories = new ArrayList<>();
		
		UtilisateurManager utilisateurManager = new UtilisateurManager();
		List<Utilisateur> listeUtilisateur = new ArrayList<Utilisateur>();
		
		try {
			listeUtilisateur = utilisateurManager.selectAll();
			request.setAttribute("listeUtilisateur", listeUtilisateur);
			List<ArticlesVendus> listeArticles = listeArticleManager.selectAll();
			request.setAttribute("listeArticle", listeArticles);
			listeCategories = categoriesManager.selectAll();
			request.setAttribute("listeCategories", listeCategories);
			rd = request.getRequestDispatcher("/accueil.jsp");
		}catch(BusinessException e) {
			e.printStackTrace();
			request.setAttribute("listeCodesErreur",e.getListeCodesErreur());
			rd = request.getRequestDispatcher("/connexion.jsp");

		}
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}