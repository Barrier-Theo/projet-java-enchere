package servlet;

import java.io.IOException;
import java.time.LocalDate;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bll.ArticlesVendusManager;

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
		Integer idUtilisateur = (Integer)session.getAttribute("id");
		
		try {
			articlesVendusManager.ajouterVente(request.getParameter("libelleArticle"), request.getParameter("descriptionArticle"), LocalDate.parse(request.getParameter("dateDebutArticle")), LocalDate.parse(request.getParameter("dateFinArticle")), Integer.parseInt(request.getParameter("prixDepartArticle")), 0, idUtilisateur, 1);


		}catch(BusinessException e) {
			e.printStackTrace();
			request.setAttribute("listeCodesErreur",e.getListeCodesErreur());
		}
		
		if(session.getAttribute("id").toString() == null) {
			rd = request.getRequestDispatcher("/connexion.jsp");
		}else {
			rd = request.getRequestDispatcher("/nouvelleVente.jsp");
		}
		rd.forward(request, response);


	}


}
