package servlet;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bll.ArticlesVendusManager;
import bo.ArticlesVendus;
import bo.Retraits;

/**
 * Servlet implementation class ServletEnchere
 */
@WebServlet("/ServletEnchere")
public class ServletEnchere extends HttpServlet {

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Integer id = Integer.parseInt(request.getParameter("id"));
		ArticlesVendusManager articleManager = new ArticlesVendusManager();
		List<ArticlesVendus> listeArticle =  new ArrayList<ArticlesVendus>();
		ArticlesVendus article  = null;
		HttpSession session =  request.getSession(false); 
		
		try {
			
			ArticlesVendus articleVendu = articleManager.selectArticleById(id);
			//Desactiver le bouton proposition si l'utilisateur connecté est le vendeur
			if(session.getAttribute("id") == article.getNoUtilisateur()) {
				request.setAttribute("noPropositon", "noPropositon");	
			}
			
			listeArticle.add(articleVendu);
			request.setAttribute("listeArticle", listeArticle);	
			rd = request.getRequestDispatcher("/detailsVente.jsp");
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
