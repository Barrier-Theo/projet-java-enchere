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
import bll.EncheresManager;
import bll.UtilisateurManager;
import bo.ArticlesVendus;
import bo.Categories;
import bo.Encheres;
import bo.Utilisateur;

/**
 * Servlet implementation class ServletFiltre
 */
@WebServlet("/ServletFiltre")
public class ServletFiltre extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletFiltre() {
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
		EncheresManager enchereManager = new EncheresManager();
		List<Encheres> listeEnchere = new ArrayList<>();
		List<Categories> listeCategories = new ArrayList<>();
		
		UtilisateurManager utilisateurManager = new UtilisateurManager();
		List<Utilisateur> listeUtilisateur = new ArrayList<Utilisateur>();
		
		try {
			listeEnchere = enchereManager.selectAll();
			request.setAttribute("listeEnchere", listeEnchere);
			listeUtilisateur = utilisateurManager.selectAll();
			request.setAttribute("listeUtilisateur", listeUtilisateur);
			List<ArticlesVendus> listeArticles = listeArticleManager.selectByCategorie(Integer.parseInt(request.getParameter("idCategorie")), request.getParameter("contient"));
			request.setAttribute("listeArticle", listeArticles);
			listeCategories = categoriesManager.selectAll();
			request.setAttribute("listeCategories", listeCategories);
			rd = request.getRequestDispatcher("WEB-INF/accueil.jsp");
		}catch(BusinessException e) {
			e.printStackTrace();
			request.setAttribute("listeCodesErreur",e.getListeCodesErreur());
			rd = request.getRequestDispatcher("WEB-INF/connexion.jsp");

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
