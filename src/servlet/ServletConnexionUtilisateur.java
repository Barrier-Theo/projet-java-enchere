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
import javax.servlet.http.HttpSession;
import javax.websocket.Session;

import bll.UtilisateurManager;
import bo.Utilisateur;

/**
 * Servlet implementation class ServletListAccueil
 */
@WebServlet("/ServletConnexionUtilisateur")
public class ServletConnexionUtilisateur extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletConnexionUtilisateur() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = null;
		UtilisateurManager listeManager = new UtilisateurManager();
		
		try {
			List<Utilisateur> listes = listeManager.selectAll();
			request.setAttribute("listes", listes);
			rd = request.getRequestDispatcher("/connexion.jsp");
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
		// TODO Auto-generated method stub
		RequestDispatcher rd = null;
		HttpSession session = request.getSession();
		String pseudo = request.getParameter("pseudo");
		String password = request.getParameter("password");
		UtilisateurManager utilisateurManager = new UtilisateurManager();
		Integer idUtilisateur = null;
		try {
			idUtilisateur= utilisateurManager.findIdByPseudoPassword(pseudo, password);
			session.setAttribute("id", idUtilisateur);
			request.setAttribute("pseudo", pseudo);
			request.setAttribute("password", password);
			rd = request.getRequestDispatcher("/liste.jsp");
		}catch(BusinessException e) {
			e.printStackTrace();
			request.setAttribute("listeCodesErreur",e.getListeCodesErreur());
			rd = request.getRequestDispatcher("/connexion.jsp");

		}
		rd.forward(request, response);
	}

}
