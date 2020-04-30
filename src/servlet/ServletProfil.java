package servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bo.Utilisateur;
import bll.UtilisateurManager;
import dal.UtilisateurDAOJdbcImpl;

/**
 * Servlet implementation class ServletListAccueil
 */
@WebServlet("/ServletProfil")
public class ServletProfil extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletProfil() {
        super();
    }
    
    /**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = null;
		String id = request.getParameter("idUser");
		UtilisateurManager utilisateurManager;
		List<Utilisateur> listeUtilisateur = new ArrayList<>();
		
		utilisateurManager = new UtilisateurManager();
		Utilisateur utilisateur = utilisateurManager.selectUser(id);
		listeUtilisateur.add(utilisateur);
		request.setAttribute("listeUtilisateur", listeUtilisateur);
		
		if(id == null) {
			rd = request.getRequestDispatcher("/connexion.jsp");
		}else {
			rd = request.getRequestDispatcher("/accesProfil.jsp");
		}
		rd.forward(request, response);
	}

}
